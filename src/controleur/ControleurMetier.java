package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.Statut;
import metier.TypeHeure;
import metier.db.*;


public class ControleurMetier {

	private Requetes requetes;

	private List<TypeHeure>   typesHeures;
	private List<Intervenant> intervenants;
	private List<Module>      modules;
	private List<Statut>      statuts;
	private List<Heure>       heures;

	private Controleur ctrl;


	public ControleurMetier( Controleur ctrl ) {

		this.ctrl = ctrl;

		this.requetes = new Requetes();

		this.statuts      = new ArrayList<Statut>();
		this.typesHeures  = new ArrayList<TypeHeure>();
		this.intervenants = new ArrayList<Intervenant>();
		this.modules      = new ArrayList<Module>();

		try { this.init(); } catch (SQLException e) { e.printStackTrace(); }
	}


	/* Insert */
	public boolean ajouterIntervenant( Intervenant intervenant ) {
		try {

			this.requetes.insertIntervenant(intervenant);
			this.intervenants.add(intervenant);

			return true;
		} catch (SQLException e) { return false; }
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
			}

			for ( Heure heure : newModule.getHeures() ) {
				this.requetes.insertHeure(heure);
				this.requetes.insertHeureModule(heure, oldModule);
				for ( Intervenant intervenant : heure.getIntervenants() ) {
					this.requetes.insertIntervenantHeure(intervenant, heure);
					this.requetes.insertIntervenantModule(intervenant, oldModule);
				}
			}
			this.requetes.updateModule( newModule );
			this.modules.remove( this.getModuleById( oldModule.getIdModule() ) );
			this.modules.add( newModule );

			return true;
		} catch ( SQLException e ) { return false; }
	}

	public boolean ajouterStatut( Statut statut ) throws SQLException {

		this.requetes.insertStatut(statut);
		this.statuts.add(statut);

		return false;
	}

	public boolean ajouterTypeHeure( TypeHeure typeHeure ) throws SQLException {

		this.requetes.insertTypeHeure(typeHeure);
		this.typesHeures.add(typeHeure);

		return false;
	}



	/*  Delete */
	public boolean supprimerIntervenant( Intervenant intervenant ) throws SQLException {

		this.requetes.deleteIntervenant(intervenant);
		this.intervenants.remove(intervenant);

		return true;
	}

	public boolean supprimerModule( Module module) throws SQLException {

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

		return true;
	}

	public boolean supprimerStatut( Statut statut ) throws SQLException {

		this.requetes.deleteStatut(statut);
		this.statuts.remove(statut);

		return true;
	}

	public boolean supprimerTypeHeure( TypeHeure typeHeure ) throws SQLException {

		this.requetes.deleteTypeHeure(typeHeure);
		this.typesHeures.remove(typeHeure);

		return true;
	}


	public Heure getHeureById( int idHeure ) {

		for ( Heure heure : this.heures )
			if ( heure.getIdHeure() == idHeure )
				return heure;
	
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




	public void init() throws SQLException {

		this.heures = this.requetes.initHeures();
		Collections.sort( this.heures );
		
		this.statuts = this.requetes.getStatuts();

		this.intervenants = this.requetes.initIntervenants();
		Collections.sort( this.intervenants );

		this.modules      = this.requetes.initModules();
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
			this.getModuleById(idModule).ajouterHeure(this.getHeureById(idHeure));
			// previous version:	this.modules.get(idModule-1).ajouterHeure(this.heures.get(idHeure-1));
		}

		System.out.println("LIAISON HEURES-MODULES FAITE");



		Integer[][] tabIntervenantsHeure = this.requetes.getIntervenantsParHeure();

		idIntervenant = 0;
		idHeure = 0;
		for (int i=0; i < tabIntervenantsHeure.length; i++) {
			idIntervenant = tabIntervenantsHeure[i][0];
			idHeure = tabIntervenantsHeure[i][1];
			this.getHeureById(idHeure).ajouterIntervenant(this.getIntervenantById(idIntervenant));
			// previous version:	this.heures.get(idHeure-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
			this.getIntervenantById(idIntervenant).ajouterHeure(this.getHeureById(idHeure));
			// previous version:	this.intervenants.get(idIntervenant-1).ajouterHeure(this.heures.get(idHeure-1));
		}

		System.out.println("LIAISON INTERVENANTS-HEURES FAITE");



		Integer[][] mapIntervenantsModule = this.requetes.getIntervenantsParModule();

		idIntervenant = 0;
		idModule = 0;
		for (int i = 0; i < mapIntervenantsModule.length; i++) {
			idIntervenant = mapIntervenantsModule[i][0];
			idModule = mapIntervenantsModule[i][1];
			this.getModuleById(idModule).ajouterIntervenant(this.getIntervenantById(idIntervenant));
			// previous version:	this.modules.get(idModule-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
			this.getIntervenantById(idIntervenant).ajouterModule(this.getModuleById(idModule));
			// previous version:	this.intervenants.get(idIntervenant-1).ajouterModule(this.modules.get(idModule-1));
		}

		System.out.println("LIAISON INTERVENANTS-MODULES FAITE");



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


		System.out.println("--- FIN INITIALISATION ---");
	}

	public List<Statut>      getStatuts()      { return this.statuts;      }
	public List<TypeHeure>   getTypesHeures()  { return this.typesHeures;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }
	public List<Module>      getModules()      { return this.modules;      }

	public void majStatuts( List<Statut> statuts ) {

		try {
			for ( Statut statut : statuts ) {
				if ( this.requetes.existsStatut(statut.getNomStatut()) )
					this.requetes.updateStatut(statut);
				else
					this.requetes.insertStatut(statut);
			}

			// gérer le warning
			for ( Statut statut : this.requetes.getStatuts() ) {
				if ( !statuts.contains(statut) )
					System.out.println("statut non supprimable");
			}

		} catch (SQLException e ) { System.out.println("INSERT STATUT IMPOSSIBLE"); e.printStackTrace(); }

	}

	public void majTypesHeures( List<TypeHeure> typesHeures ) {

		try {
			for ( TypeHeure typeHeure : typesHeures ) {
				if ( this.requetes.existsTypeHeure(typeHeure.getIdTypeHeure()) )
					this.requetes.updateTypeHeure(typeHeure);
				else
					this.requetes.insertTypeHeure(typeHeure);
			}

			// gérer le warning
			for ( TypeHeure typeHeure : this.requetes.getTypesHeures() ) {
				if ( !typesHeures.contains(typeHeure) )
					System.out.println("type_heure non supprimable");
			}

		} catch (SQLException e) { System.out.println("INSERT TYPE_HEURE IMPOSSIBLE"); e.printStackTrace(); }

	}


}
