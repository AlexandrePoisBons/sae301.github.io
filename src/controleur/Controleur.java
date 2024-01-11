package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.jcraft.jsch.JSchException;

import ihm.accueil.FrameAccueil;
import ihm.accueil.FrameConnexion;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.Statut;
import metier.TypeHeure;
import metier.db.*;


public class Controleur {

	private Infos infos;
	private FrameAccueil frame;
	private Requetes requetes;

	private List<TypeHeure>   typesHeures;
	private List<Intervenant> intervenants;
	private List<Module>      modules;
	private List<Statut>      statuts;
	private List<Heure>       heures;
	private HashMap<Module, HashMap<TypeHeure, HashMap<String, Integer>>> heuresParModule;

	public static void main(String[] args) { new FrameConnexion(); }

	public Controleur() {

		this.infos = new Infos();

		System.out.println(infos.getDatabase()+"="+infos.getLogin()+":"+infos.getPassword());

		this.frame    = new FrameAccueil(this);
		this.requetes = new Requetes();

		this.statuts      = new ArrayList<Statut>();
		this.typesHeures  = new ArrayList<TypeHeure>();
		this.intervenants = new ArrayList<Intervenant>();
		this.modules      = new ArrayList<Module>();

		try { this.init(); } catch (SQLException e) { /*e.printStackTrace();*/ }

	}


	public void init() throws SQLException {

		this.heures = this.requetes.initHeures();
		Collections.sort( this.heures );

		this.statuts = this.requetes.getStatuts();

		this.intervenants = this.requetes.initIntervenants();
		Collections.sort( this.intervenants );

		this.modules = this.requetes.initModules();
		Collections.sort( this.modules );


		Integer idHeure;
		Integer idModule;
		Integer idIntervenant;

		Integer[][] tabHeuresModule = this.requetes.getHeuresParModule();

		idHeure = 0;
		idModule = 0;
		for (int i=0; i< tabHeuresModule.length;i++) {
			idHeure  = tabHeuresModule[i][0];
			idModule = tabHeuresModule[i][1];
			this.getModuleById(idModule).ajouterHeure(this.getHeureById(idHeure));
		}

		// System.out.println("LIAISON HEURES-MODULES FAITE");



		Integer[][] tabIntervenantsHeure = this.requetes.getIntervenantsParHeure();

		idIntervenant = 0;
		idHeure = 0;
		for (int i=0; i < tabIntervenantsHeure.length; i++) {
			idIntervenant = tabIntervenantsHeure[i][0];
			idHeure = tabIntervenantsHeure[i][1];
			this.getHeureById(idHeure).ajouterIntervenant(this.getIntervenantById(idIntervenant));
			this.getIntervenantById(idIntervenant).ajouterHeure(this.getHeureById(idHeure));
		}

		// System.out.println("LIAISON INTERVENANTS-HEURES FAITE");



		Integer[][] mapIntervenantsModule = this.requetes.getIntervenantsParModule();

		idIntervenant = 0;
		idModule = 0;
		for (int i = 0; i < mapIntervenantsModule.length; i++) {
			idIntervenant = mapIntervenantsModule[i][0];
			idModule = mapIntervenantsModule[i][1];
			this.getModuleById(idModule).ajouterIntervenant(this.getIntervenantById(idIntervenant));
			this.getIntervenantById(idIntervenant).ajouterModule(this.getModuleById(idModule));
		}

		// System.out.println("LIAISON INTERVENANTS-MODULES FAITE");



		this.typesHeures  = this.requetes.getTypesHeures();

		if ( this.typesHeures.size() == 0 ) {
			this.typesHeures.add( TypeHeure.initTypeHeure( 1, "TD",  (float) 1.0  ) );
			this.typesHeures.add( TypeHeure.initTypeHeure( 2, "TP",  (float) 0.66 ) );
			this.typesHeures.add( TypeHeure.initTypeHeure( 3, "CM",  (float) 1.5  ) );
			this.typesHeures.add( TypeHeure.initTypeHeure( 4, "TUT", (float) 1.0  ) );
			this.typesHeures.add( TypeHeure.initTypeHeure( 6, "REH", (float) 1.0  ) );
			this.typesHeures.add( TypeHeure.initTypeHeure( 5, "Sae", (float) 1.0  ) );
			this.typesHeures.add( TypeHeure.initTypeHeure( 7, "HP",  (float) 1.0  ) );
		}


		this.heuresParModule = new HashMap<>();

		for (Module module : this.modules) {
			if ( this.requetes.typesHeuresDansModuleRentrees(module.getIdModule()) ) {
				HashMap<TypeHeure, HashMap<String,Integer>> map = new HashMap<>();
				HashMap<Integer, HashMap<String,Integer>> temp = this.requetes.getTypesHeuresParModule(module.getIdModule());
				for (Integer idTypeHeure : temp.keySet()) {
					map.put(this.getTypeHeureById(idTypeHeure), temp.get(idTypeHeure));
				}
				this.heuresParModule.put(module, map);
			}
		}


		// System.out.println("--- FIN INITIALISATION ---");

	}

	public HashMap<TypeHeure, HashMap<String,Integer>> getTypesHeuresParModule(Module module) { return this.heuresParModule.get(module); }

	public List<Statut>      getStatuts()      { return this.statuts;      }
	public List<TypeHeure>   getTypesHeures()  { return this.typesHeures;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }
	public List<Module>      getModules()      { return this.modules;      }


	/* Insert */
	public boolean ajouterIntervenant( Intervenant intervenant ) {
		try {

			this.requetes.insertIntervenant(intervenant);
			this.intervenants.add(intervenant);

			return true;
		} catch (SQLException e) { return false; }
	}


	public void majTypesHeuresModule(Module module, HashMap<TypeHeure, HashMap<String,Integer>> map) {

		if ( this.heuresParModule.containsKey(module) ) {
			this.heuresParModule.remove(module);
		}
		this.heuresParModule.put(module, map);

		this.requetes.deleteTypesHeuresParModule(module.getIdModule());
		int pn, nbSemaines, nbHeures;
		for (TypeHeure typeHeure : map.keySet()) {
			pn = 0;
			nbSemaines = 0;
			nbHeures = 0;
			for (String data : map.get(typeHeure).keySet()) {
				switch(data) {
					case "pn" -> {
						if ( map.containsKey(typeHeure) ) {
							try{
								pn = map.get(typeHeure).get(data);
							} catch (NullPointerException e) { }
						}
					} case "nb_semaines" -> {
						if ( map.containsKey(typeHeure) )
							nbSemaines = map.get(typeHeure).get(data);
					} case "nb_heures" -> {
						if ( map.containsKey(typeHeure) )
							nbHeures = map.get(typeHeure).get(data);
					}
				}
			}
			this.requetes.insertTypesHeuresParModule(module.getIdModule(), typeHeure.getIdTypeHeure(), pn, nbSemaines, nbHeures);
		}

	}


	public boolean ajouterModule( Module module ) {
		try {
			this.requetes.insertModule(module);

			for (Heure heure : module.getHeures()) {
				this.requetes.insertHeure(heure);
				this.requetes.insertHeureModule(heure, module);
				for (Intervenant intervenant : heure.getIntervenants()) {
					this.requetes.insertIntervenantHeure(intervenant, heure);
					this.requetes.insertIntervenantModule(intervenant, module);
				}
			}
			this.modules.add(module);

			return true;
		} catch ( SQLException e ) { return false; }
	}

	public boolean updateModule( Module oldModule, Module newModule ) {

		try {
			for ( Heure heure : oldModule.getHeures() ) {
				this.requetes.deleteHeureModule(heure, oldModule);
				for (Intervenant intervenant : heure.getIntervenants()) {
					this.requetes.deleteIntervenantHeure(intervenant, heure);
					this.requetes.deleteIntervenantModule(intervenant, oldModule);
				}
				this.requetes.deleteHeure(heure);
				this.heures.remove(heure);
			}

			for ( Heure heure : newModule.getHeures() ) {
				this.requetes.insertHeure(heure);
				this.requetes.insertHeureModule(heure, oldModule);
				for ( Intervenant intervenant : heure.getIntervenants() ) {
					this.requetes.insertIntervenantHeure(intervenant, heure);
					this.requetes.insertIntervenantModule(intervenant, oldModule);
				}
				this.heures.add(heure);
			}
			this.requetes.updateModule( newModule );
			this.modules.remove( this.getModuleById( oldModule.getIdModule() ) );
			this.modules.add( newModule );

			return true;
		} catch ( SQLException e ) { return false; }
	}

	public void ajouterStatut( Statut statut ) throws SQLException {

		this.requetes.insertStatut(statut);
		this.statuts.add(statut);

	}

	public void ajouterTypeHeure( TypeHeure typeHeure ) throws SQLException {

		this.requetes.insertTypeHeure(typeHeure);
		this.typesHeures.add(typeHeure);

	}



	/*  Delete */
	public void supprimerIntervenant( Intervenant intervenant ) throws SQLException {

		this.requetes.deleteIntervenant(intervenant);
		this.intervenants.remove(intervenant);

	}

	public void supprimerModule( Module module) throws SQLException {

		for (Intervenant intervenant : module.getIntervenants()) {
			this.requetes.deleteIntervenantModule(intervenant,module);
		}

		for ( Heure heure : module.getHeures() ) {
			for (Intervenant intervenant : heure.getIntervenants()){
				this.requetes.deleteIntervenantHeure(intervenant, heure);
			}
			this.requetes.deleteHeureModule(heure,module);
			this.requetes.deleteHeure(heure);
			this.heures.remove(heure);
		}
		this.requetes.deleteModule(module);
		this.modules.remove(module);

	}

	public void supprimerStatut( Statut statut ) throws SQLException {

		this.requetes.deleteStatut(statut);
		this.statuts.remove(statut);

	}

	public void supprimerTypeHeure( TypeHeure typeHeure ) throws SQLException {

		this.requetes.deleteTypeHeure(typeHeure);
		this.typesHeures.remove(typeHeure);

	}


	public Heure getHeureById( int idHeure ) {

		for ( Heure heure : this.heures )
			if ( heure.getIdHeure() == idHeure )
				return heure;
	
		return null;
	}

	public TypeHeure getTypeHeureById( int idTypeHeure ) {
		for (TypeHeure typeHeure : this.typesHeures) {
			if ( typeHeure.getIdTypeHeure() == idTypeHeure )
				return typeHeure;
		}
		return null;
	}

	public TypeHeure getTypeHeureByNom(String nom) {
		for (TypeHeure typeHeure : this.typesHeures)
			if ( typeHeure.getNomTypeHeure().equals(nom) )
				return typeHeure;
			
		return null;
	}

	public Intervenant getIntervenantById( int idIntervenant ) {

		for ( Intervenant intervenant : this.intervenants )
			if ( intervenant.getIdIntervenant() == idIntervenant )
				return intervenant;

		return null;
	}

	public Module getModuleById( int idModule ) {

		for ( Module module : this.modules )
			if ( module.getIdModule() == idModule )
				return module;

		return null;
	}


	public void majStatuts( List<Statut> statuts ) {

		try {
			for ( Statut statut : statuts ) {
				if ( this.requetes.existsStatut(statut.getNomStatut()) ) {
					this.requetes.updateStatut(statut);
				}
				else {
					this.requetes.insertStatut(statut);
					this.statuts.add(statut);
				}
			}

		} catch (SQLException e ) { System.out.println("INSERT STATUT IMPOSSIBLE"); e.printStackTrace(); }

	}

	public void majTypesHeures( List<TypeHeure> typesHeures ) {

		try {
			for ( TypeHeure typeHeure : typesHeures ) {
				if ( this.requetes.existsTypeHeure(typeHeure.getIdTypeHeure()) ){
					this.requetes.updateTypeHeure(typeHeure);
				}
				else {
					this.requetes.insertTypeHeure(typeHeure);
					this.typesHeures.add(typeHeure);
				}
			}

		} catch (SQLException e) { System.out.println("INSERT TYPE_HEURE IMPOSSIBLE"); e.printStackTrace(); }

	}


}
