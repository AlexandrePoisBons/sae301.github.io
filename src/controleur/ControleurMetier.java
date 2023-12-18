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
		try {
			this.requetes.insertModule(module);
			this.modules.add(module);
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
		// System.out.println("Modules: ");
		// for (Module module : this.modules) { System.out.println(module.getIdModule()); }


		HashMap<Integer,Integer> mapHeuresModule = this.requetes.getHeuresParModule();

		for (Integer idHeure : mapHeuresModule.keySet()) {
			System.out.println("ajout: heure "+idHeure+" au module "+mapHeuresModule.get(idHeure));
			this.modules.get(mapHeuresModule.get(idHeure)-1).ajouterHeure(this.heures.get(idHeure-1));
		}
		System.out.println("LIAISON HEURES-MODULES FAITE");


		HashMap<Integer,Integer> mapIntervenantsHeure = this.requetes.getIntervenantsParHeure();

		for (Integer idIntervenant : mapIntervenantsHeure.keySet()) {
			System.out.println();
			System.out.println("ajout: intervenant "+idIntervenant +" a l'heure "+mapIntervenantsHeure.get(idIntervenant));
			this.heures.get(mapIntervenantsHeure.get(idIntervenant)-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
			System.out.println("dono"+this.heures.get(mapIntervenantsHeure.get(this.intervenants.get(idIntervenant-1).getIdIntervenant())-1).getIntervenants());

			this.intervenants.get(idIntervenant-1).ajouterHeure(this.heures.get(mapIntervenantsHeure.get(idIntervenant)-1));
			System.out.println("va"+this.intervenants.get(idIntervenant-1).getHeures());

		}

		// for (Intervenant intervenant : this.intervenants)
		// 		if ( mapIntervenantsHeure.containsKey(intervenant.getIdIntervenant()) ) {
		// 			System.out.println("ajout de intervenant "+intervenant.getIdIntervenant()+ " a heure "+this.heures.get(intervenant.getIdIntervenant()-1).getIdHeure() );

		// 			this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1).ajouterIntervenant(intervenant);
		// 			System.out.println("dono"+this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1).getIntervenants());
		// 			intervenant.ajouterHeure(this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1));
		// 			System.out.println("va"+intervenant.getHeures());
		// 			System.out.println("\n\nzebiiii "+this.heures.get(mapIntervenantsHeure.get(intervenant.getIdIntervenant())-1).getIntervenants());
		// 	}

		System.out.println("\n\n INTERVENANTS PAR HEURES");
		for (Heure heure : this.heures) {
			System.out.println(heure.getIdHeure()+" HEUUUUUUREURUEURUERER "+heure.getIntervenants());
		}

		System.out.println("\n\n HEURES PAR INTERVENANTS");
		for (Intervenant intervenant : this.intervenants) {
			System.out.println(intervenant.getIdIntervenant()+" AHAHAHHAHAAAAAAAA "+intervenant.getHeures());
		}
		System.out.println();




		System.out.println("LIAISON INTERVENANTS-HEURES FAITE");




		Integer[][] mapIntervenantsModule = this.requetes.getIntervenantsParModule();

		System.out.println("intervenants par module");

		Integer idIntervenant;
		Integer idModule;
		for (int i = 0; i < mapIntervenantsModule.length; i++) {
			idIntervenant = mapIntervenantsModule[i][0];
			idModule = mapIntervenantsModule[i][1];
			System.out.println("ajout: intervenant "+idIntervenant+" au module "+idModule);
			this.modules.get(idModule-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
			this.intervenants.get(idIntervenant-1).ajouterModule(this.modules.get(idModule-1));
		}

		// for (Integer idIntervenant : mapIntervenantsModule.keySet()) {
		// 	System.out.println("ajout: intervenant "+idIntervenant+" au module "+mapIntervenantsModule.get(idIntervenant));
		// 	this.modules.get(mapIntervenantsModule.get(idIntervenant)-1).ajouterIntervenant(this.intervenants.get(idIntervenant-1));
		// 	System.out.println("done");
		// }

		System.out.println("kjlhlzbgoiéapjpcv nz");
		for (Module module : this.modules) {
			System.out.println("intervenants du module "+module.getIdModule()+" : "+module.getIntervenants());
		}
		System.out.println("LIAISON INTERVENANTS-MODULES FAITE");




		// apres ca: on pourra afficher les intervenants dans Intervenants (et avec toutes les valeurs)
		                                // les méthodes et l'implémentation est normalement deja faite

		
		this.typesHeures  = this.requetes.getTypesHeures();
		

		if ( this.typesHeures.size() == 0 ) {
			this.typesHeures.add( new TypeHeure("CM"    , (float) 1.5  ) );
			this.typesHeures.add( new TypeHeure("TD"    , (float) 1.0  ) );
			this.typesHeures.add( new TypeHeure("TP"    , (float) 1.0  ) );
			this.typesHeures.add( new TypeHeure("Tutoré", (float) 1.0  ) );
			this.typesHeures.add( new TypeHeure("Sae"   , (float) 1.0  ) );
			this.typesHeures.add( new TypeHeure("REH"   , (float) 1.0  ) );
			this.typesHeures.add( new TypeHeure("HP"    , (float) 1.0  ) );

			for ( TypeHeure typeHeure : this.typesHeures )
				this.requetes.insertTypeHeure(typeHeure);
		}


		System.out.println("--- FIN INITIALISATION ---");

	}

	public List<Statut>      getStatuts()      { return this.statuts;      }
	public List<TypeHeure>   getTypesHeures()  { return this.typesHeures;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }
	public List<Module>      getModules()      { System.out.println(this.modules+"donovaaaa"); return this.modules;      }


}
