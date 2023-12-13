package metier;

import java.util.ArrayList;
import java.util.List;

public class Intervenant {
	private int    idIntervenant;
	private String prenom;
	private String nom;
	private Statut statut;
	private float  nbEqTD;

	private List<Heure>  heures;
	private List<Module> modules;

	
	public static Intervenant creerIntervenant(int idIntervenant, String prenom, String nom, Statut statut, float nbEqTD ) {
		if ( prenom == null || nom == null || statut == null || nbEqTD < 0  )
			return null;

		return new Intervenant(idIntervenant, prenom, nom, statut, nbEqTD);
	}
	
	private Intervenant(int idIntervenant, String prenom, String nom, Statut statut, float nbEqTD ) {
		this.idIntervenant = idIntervenant;
		this.prenom        = prenom;
		this.nom           = nom;
		this.statut        = statut;
		this.nbEqTD        = nbEqTD;

		this.heures  = new ArrayList<Heure>();
		this.modules = new ArrayList<Module>(); 
	}

	public int           getIdIntervenant() { return this.idIntervenant; }
	public String        getPrenom()        { return this.prenom;        }
	public String        getNom()           { return this.nom;           }
	public Statut        getStatut()        { return this.statut;        }
	public float         getNbEqTD()        { return this.nbEqTD;        }
	public List<Heure>   getHeures()        { return this.heures;        }
	public List<Module>  getModules()       { return this.modules;       }

	public void setIdIntervenant(int idIntervenant) { this.idIntervenant = idIntervenant; }
	public void setPrenom( String prenom )          { this.prenom        = prenom;        }
	public void setNom(String nom)                  { this.nom           = nom;           }
	public void setStatut(Statut statut)            { this.statut        = statut;        }
	public void setNbEqTD( float nbEqTD )           { this.nbEqTD        = nbEqTD;        }

	public void ajouterHeure(Heure h) {
		this.heures.add(h);
	}

	public void supprimerHeure(Heure h) {
		this.heures.remove(h);
	}

	public void ajouterModule(Module m) {
		this.modules.add(m);
	}

	public void supprimerModule(Module m) {
		this.modules.remove(m);
	}

	public float getNbHeures() {
		float nbHeures = 0;
		for (Heure h : this.heures)
			nbHeures += h.getDuree() * h.getTypeHeure().getCoeff();
		
		return nbHeures;
	}

	public String toString() {
		return this.idIntervenant + " " + this.prenom + " " + this.nom + " (" + this.statut + ", " + this.nbEqTD + "eqTD)";
	}
}