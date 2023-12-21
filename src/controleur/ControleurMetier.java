package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.Statut;
import metier.TypeHeure;
import metier.db.*;


public class ControleurMetier {

	private Requetes requetes;

	private List<Statut>      statuts;
	private List<TypeHeure>   typesHeures;

	private List<Intervenant> intervenants;
	private List<Module>      modules;

	private List<Heure>       heures;

	private Controleur ctrl;


	public ControleurMetier(Controleur ctrl) {

		this.ctrl = ctrl;

		this.requetes = new Requetes();

		this.statuts      = new ArrayList<Statut>();
		this.typesHeures  = new ArrayList<TypeHeure>();
		this.intervenants = new ArrayList<Intervenant>();
		this.modules      = new ArrayList<Module>();

		try { this.init(); } catch (SQLException e) { e.printStackTrace(); }
	}


	/* Insert */
	public boolean ajouterIntervenant(Intervenant intervenant) {
		try{
			this.requetes.insertIntervenant(intervenant);
			this.intervenants.add(intervenant);
			return true;
		} catch (SQLException e) { return false; }
	}

	public boolean ajouterModule(Module module) {
		System.out.println("AJOUT MODULE");
		try {
			System.out.print("insert module: ");
			System.out.print(this.requetes.insertModule(module)+"\n");
			for (Heure heure : module.getHeures()) {
				this.requetes.insertHeure(heure);
				this.requetes.insertHeureModule(heure, module);
				for (Intervenant intervenant : heure.getIntervenants()) {
					this.requetes.insertIntervenantHeure(intervenant, heure);
					this.requetes.insertIntervenantModule(intervenant, module);
					System.out.println("tout au fond");
				}
			}
			this.modules.add(module);

			System.out.println("C'EST TERMINE GROS");
			return true;
		} catch ( SQLException e ) { return false; }
	}

	public boolean updateModule(Module oldModule, Module newModule) {
		System.out.println("UPDATE MODULE");
		for (int i = 0; i < oldModule.getHeures().size(); i++) {
			System.out.println(oldModule.getHeures().get(i).getIdHeure()+" donova stp fais que ca marche "+newModule.getHeures().get(i).getIdHeure());
		}
		try {
			// this.requetes.updateModule(newModule);
			for (Heure heure : oldModule.getHeures()) {
				System.out.println("flo :" + heure);
				// this.requetes.deleteHeure(heure);
				System.out.println("delete heure");
				this.requetes.deleteHeureModule(heure, oldModule);
				System.out.println("delete heure-module");
				for (Intervenant intervenant : heure.getIntervenants()) {
					this.requetes.deleteIntervenantHeure(intervenant, heure);
					System.out.println("delete intervenant-heure");
					//this.requetes.deleteHeure(heure);
					this.requetes.deleteIntervenantModule(intervenant, oldModule);
					System.out.println("delete intervenant-module");
				}
				this.requetes.deleteHeure(heure);
				System.out.println("delete heure: "+heure.getIdHeure());
			}

			System.out.println("ON INSERT");
			for (Heure heure : newModule.getHeures()) {
				this.requetes.insertHeure(heure);
				this.requetes.insertHeureModule(heure, oldModule);
				for (Intervenant intervenant : heure.getIntervenants()) {
					this.requetes.insertIntervenantHeure(intervenant, heure);
					this.requetes.insertIntervenantModule(intervenant, oldModule);
				}
			}
			this.requetes.updateModule(newModule);
			this.modules.remove(this.getModuleById(oldModule.getIdModule()));
			this.modules.add(newModule);

			System.out.println("C'EST FINI GARS");
			return true;
		} catch ( SQLException e ) { return false; }
	}

	public boolean ajouterStatut(Statut statut) throws SQLException {
		this.requetes.insertStatut(statut);
		this.statuts.add(statut);
		return false;
	}

	public boolean ajouterTypeHeure(TypeHeure typeHeure) throws SQLException {
		this.requetes.insertTypeHeure(typeHeure);
		this.typesHeures.add(typeHeure);
		return false;
	}




	/*  Delete */
	public boolean supprimerIntervenant(Intervenant intervenant) throws SQLException {
		this.requetes.deleteIntervenant(intervenant);
		this.intervenants.remove(intervenant);
		return false;
	}

	public boolean supprimerModule(Module module) throws SQLException {


		System.out.println("donova :"+module.getIntervenants().size());
		for (Intervenant intervenant : module.getIntervenants()) {
			this.requetes.deleteIntervenantModule(intervenant,module);
		}

		// this.requetes.deleteModule(module);

		for (Heure heure : module.getHeures()) {
			this.requetes.deleteHeure(heure);
			this.requetes.deleteHeureModule(heure,module);
			this.heures.remove(heure);
		}
		this.requetes.deleteModule(module);
		this.modules.remove(module);
		return false;
	}

	public boolean supprimerStatut(Statut statut) throws SQLException {
		this.requetes.deleteStatut(statut);
		this.statuts.remove(statut);
		return false;
	}

	public boolean supprimerTypeHeure(TypeHeure typeHeure) throws SQLException {
		this.requetes.deleteTypeHeure(typeHeure);
		this.typesHeures.remove(typeHeure);
		return false;
	}


	public Heure getHeureById( int idHeure ) {
		for (Heure heure : this.heures)
			if (heure.getIdHeure() == idHeure)
				return heure;
		return null;
	}

	public Intervenant getIntervenantById( int idIntervenant ) {
		for (Intervenant intervenant : this.intervenants)
			if (intervenant.getIdIntervenant() == idIntervenant)
				return intervenant;
		return null;
	}

	public Module getModuleById( int idModule ) {
		for (Module module : this.modules)
			if (module.getIdModule() == idModule)
				return module;
		return null;
	}




	public void init() throws SQLException {

		this.heures = this.requetes.initHeures();
		// System.out.println("nb heures "+this.heures.size() + " : " + (this.heures.get(this.heures.size()-1).getIdHeure() <= this.heures.size()));
		Collections.sort( this.heures );
		// System.out.println("Heures: ");
		// for (Heure heure : this.heures) { System.out.println(heure.getIdHeure()); }

		this.statuts = this.requetes.getStatuts();
		// System.out.println("nb statuts "+this.statuts.size());
		// System.out.println("Statuts: ");
		// for (Statut statut : this.statuts) { System.out.println(statut.getNomStatut()); }

		this.intervenants = this.requetes.initIntervenants();
		// System.out.println("nb intervenants "+this.intervenants.size() + " : " + (this.intervenants.get(this.intervenants.size()-1).getIdIntervenant() <= this.intervenants.size()));
		Collections.sort( this.intervenants );
		// System.out.println("Intervenants: ");
		// for (Intervenant intervenant : this.intervenants) { System.out.println(intervenant.getIdIntervenant()); }

		this.modules      = this.requetes.initModules();
		//System.out.println("nb modules "+this.modules.size() + " : " + (this.modules.get(this.modules.size()-1).getIdModule() <= this.modules.size()));
		Collections.sort( this.modules );


		Integer idHeure;
		Integer idModule;
		Integer idIntervenant;


		Integer[][] tabHeuresModule = this.requetes.getHeuresParModule();

		idHeure = 0;
		idModule = 0;
		for (int i=0; i< tabHeuresModule.length;i++) {
			idHeure = tabHeuresModule[i][0];
			idModule = tabHeuresModule[i][1];
			//System.out.println("ajout: heure "+idHeure+" au module "+idModule);
			this.modules.get(idModule-1).ajouterHeure(this.heures.get(idHeure-1));
		}
		System.out.println("LIAISON HEURES-MODULES FAITE");



		Integer[][] tabIntervenantsHeure = this.requetes.getIntervenantsParHeure();

		idIntervenant = 0;
		idHeure = 0;
		for (int i=0; i < tabIntervenantsHeure.length; i++) {
			idIntervenant = tabIntervenantsHeure[i][0];
			idHeure = tabIntervenantsHeure[i][1];
			//System.out.println("ajout: intervenant "+idIntervenant +" a l'heure "+idHeure);
			this.heures.get(idHeure-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
			//System.out.println("dono"+this.heures.get(idHeure-1).getIntervenants());
			this.intervenants.get(idIntervenant-1).ajouterHeure(this.heures.get(idHeure-1));
			//System.out.println("va"+this.intervenants.get(idIntervenant-1).getHeures());
		}

		System.out.println("LIAISON INTERVENANTS-HEURES FAITE");



		// for (Intervenant intervenant : this.intervenants)
		// 		if ( mapIntervenantsHeure.containsKey(intervenant.getIdIntervenant()) ) {
		// 			System.out.println("ajout de intervenant "+intervenant.getIdIntervenant()+ " a heure "+this.heures.get(intervenant.getIdIntervenant()-1).getIdHeure() );

		// 			this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1).ajouterIntervenant(intervenant);
		// 			System.out.println("dono"+this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1).getIntervenants());
		// 			intervenant.ajouterHeure(this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1));
		// 			System.out.println("va"+intervenant.getHeures());
		// 			System.out.println("\n\nzebiiii "+this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1).getIntervenants());
		// 	}

		// System.out.println("\n\n INTERVENANTS PAR HEURES");
		// for (Heure heure : this.heures) {
		// 	System.out.println(heure.getIdHeure()+" HEUUUUUUREURUEURUERER "+heure.getIntervenants());
		// }

		// System.out.println("\n\n HEURES PAR INTERVENANTS");
		// for (Intervenant intervenant : this.intervenants) {
		// 	System.out.println(intervenant.getIdIntervenant()+" AHAHAHHAHAAAAAAAA "+intervenant.getHeures());
		// }


		Integer[][] mapIntervenantsModule = this.requetes.getIntervenantsParModule();

		idIntervenant = 0;
		idModule = 0;
		for (int i = 0; i < mapIntervenantsModule.length; i++) {
			idIntervenant = mapIntervenantsModule[i][0];
			idModule = mapIntervenantsModule[i][1];
			//System.out.println("ajout: intervenant "+idIntervenant+" au module "+idModule);
			this.modules.get(idModule-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
			this.intervenants.get(idIntervenant-1).ajouterModule(this.modules.get(idModule-1));
		}

		System.out.println("LIAISON INTERVENANTS-MODULES FAITE");



		this.typesHeures  = this.requetes.getTypesHeures();

		// if ( this.typesHeures.size() == 0 ) {
		// 	this.typesHeures.add( TypeHeure.init("CM"    , (float) 1.5  ) );
		// 	this.typesHeures.add( TypeHeure.init("TD"    , (float) 1.0  ) );
		// 	this.typesHeures.add( TypeHeure.init("TP"    , (float) 1.0  ) );
		// 	this.typesHeures.add( TypeHeure.init("Tutoré", (float) 1.0  ) );
		// 	this.typesHeures.add( TypeHeure.init("Sae"   , (float) 1.0  ) );
		// 	this.typesHeures.add( TypeHeure.init("REH"   , (float) 1.0  ) );
		// 	this.typesHeures.add( TypeHeure.init("HP"    , (float) 1.0  ) );
		// }
		// else {
		// 	for ( TypeHeure typeHeure : this.typesHeures )
		// 		this.requetes.insertTypeHeure(typeHeure);
		// }


		System.out.println("--- FIN INITIALISATION ---");
	}

	public List<Statut>      getStatuts()      { return this.statuts;      }
	public List<TypeHeure>   getTypesHeures()  { return this.typesHeures;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }
	public List<Module>      getModules()      { return this.modules; }

	public void majStatuts(List<Statut> statuts) {
		try {
			for (Statut statut : statuts){
				if (this.requetes.existsStatut(statut.getNomStatut()))
					this.requetes.updateStatut(statut);
				else 
					this.requetes.insertStatut(statut);
			}

			// gérer le warning
			for (Statut statut : this.requetes.getStatuts()) {
				if (!statuts.contains(statut))
					System.out.println("statut non supprimable");
			}

		} catch (SQLException e ) { System.out.println("INSERT STATUT IMPOSSIBLE"); e.printStackTrace(); }

	}

	public void majTypesHeures(List<TypeHeure> typesHeures) {
		try{
			for (TypeHeure typeHeure : typesHeures) {
				if ( this.requetes.existsTypeHeure(typeHeure.getIdTypeHeure()) )
					this.requetes.updateTypeHeure(typeHeure);
				else
					this.requetes.insertTypeHeure(typeHeure);
				
			}

			// gérer le warning
			for (TypeHeure typeHeure : this.requetes.getTypesHeures()) {
				if (!typesHeures.contains(typeHeure))
					System.out.println("type_heure non supprimable");
			}

		} catch (SQLException e) { System.out.println("INSERT TYPE_HEURE IMPOSSIBLE"); e.printStackTrace(); }

	}


}
