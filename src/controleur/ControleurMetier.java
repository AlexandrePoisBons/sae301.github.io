package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
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

		try { System.out.println(this.requetes.getModules().get(0)); System.out.println("\n\n\nCOUCOUUUU\n\n\n"); }
		catch ( SQLException e ) { e.printStackTrace(); System.out.println("ca clc le gros pc"); }

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

		this.heures = this.requetes.getHeures();
		System.out.println("insh'");
		this.statuts      = this.requetes.getStatuts();
		System.out.println("coucou toi");
		this.intervenants = this.requetes.getIntervenants();
		System.out.println("eh oh");
		this.modules      = this.requetes.getModules();

		this.requetes.get // l'erreur c'est pour que mon cerveau se rappelle des commentaires la mdr

		// LIER MODULES AVEC LES HEURES A PARTIR DE Heure_Module (requete deja créée)

		// LIER INTERVENANT AVEC LES HEURES A PARTIR DE Intervenant_Heure (requete a faire)

		// LIER INTERVENANT A MODULE A PARTIE DE Intervenant_Module (requete a faire)

		// apres ca: on pourra afficher les intervenants dans Intervenants (et avec toutes les valeurs)
		                                // les méthodes et l'implémentation est normalement deja faite

		System.out.println("bon bah"+this.modules);
		this.typesHeures  = this.requetes.getTypesHeures();
		System.out.println("nan mais");

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

		System.out.println("---FIN---");


	}

	public List<Statut>      getStatuts()      { return this.statuts;      }
	public List<TypeHeure>   getTypesHeures()  { return this.typesHeures;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }
	public List<Module>      getModules()      { System.out.println(this.modules+"donovaaaa");return this.modules;      }


}
