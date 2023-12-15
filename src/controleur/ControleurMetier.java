package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	private Controleur ctrl;

	public static void main(String[] args) { new ControleurMetier(null); }

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
			System.out.println("dono");
			this.modules.add(module);
			System.out.println("donova");
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
		this.statuts	  = this.requetes.getStatuts();
		this.intervenants = this.requetes.getIntervenants();
		this.modules      = this.requetes.getModules();
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


	}

	public List<Statut>      getStatuts()      { return this.statuts;      }
	public List<TypeHeure>   getTypesHeures()  { return this.typesHeures;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }
	public List<Module>      getModules()      { return this.modules;      }
}
