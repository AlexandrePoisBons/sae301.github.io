package metier;

import java.util.ArrayList;
import java.util.List;

import metier.db.Requetes;

/**
 * Classe Intervenant représentant les personnes du corps enseignants intervenant dans les modules
 * @author Alexandre Pois--Bons - Florian Janot
 * @version 1.0
 */
public class Intervenant {
	private static int nbIntervenant = Requetes.getNbIntervenants();

	private int    idIntervenant;
	private String prenom;
	private String nom;
	private Statut statut;
	private float  nbEqTD;

	private List<Heure>  heures;
	private List<Module> modules;

	/**
	 * Factory de la classe Intervenant
	 * @param prenom Prénom de l'intervenant
	 * @param nom    Nom de l'intervenant
	 * @param statut Statut de l'intervenant
	 * @param nbEqTD Nombre d'heure équivalent TD de l'intervenant
	 * @return       L'objet Intervenant créé via le constructeur privé ou null en cas de paramètre invalide
	 */
	public static Intervenant creerIntervenant( String prenom, String nom, Statut statut, float nbEqTD ) {
		if ( prenom == null || nom == null || statut == null || nbEqTD < 0  )
			return null;

		return new Intervenant( prenom, nom, statut, nbEqTD);
	}
	
	/**
	 * Constructeur privé de la classe Intervenant
	 * @param prenom Prénom de l'intervenant
	 * @param nom    Nom de l'intervenant
	 * @param statut Statut de l'intervenant
	 * @param nbEqTD Nombre d'heure équivalent TD de l'intervenant
	 * @return       L'objet Intervenant précédement validé via la factory
	 */
	private Intervenant( String prenom, String nom, Statut statut, float nbEqTD ) {
		this.idIntervenant = Intervenant.nbIntervenant++;
		this.prenom        = prenom;
		this.nom           = nom;
		this.statut        = statut;
		this.nbEqTD        = nbEqTD;

		this.heures  = new ArrayList<Heure>();
		this.modules = new ArrayList<Module>(); 
	}

	// Getters
	public int           getIdIntervenant() { return this.idIntervenant; }
	public String        getPrenom()        { return this.prenom;        }
	public String        getNom()           { return this.nom;           }
	public Statut        getStatut()        { return this.statut;        }
	public float         getNbEqTD()        { return this.nbEqTD;        }
	public List<Heure>   getHeures()        { return this.heures;        }
	public List<Module>  getModules()       { return this.modules;       }

	// Setters
	public void setIdIntervenant(int idIntervenant) { this.idIntervenant = idIntervenant; }
	public void setPrenom( String prenom )          { this.prenom        = prenom;        }
	public void setNom(String nom)                  { this.nom           = nom;           }
	public void setStatut(Statut statut)            { this.statut        = statut;        }
	public void setNbEqTD( float nbEqTD )           { this.nbEqTD        = nbEqTD;        }

	// Ajout et suppression d'heure
	public void ajouterHeure(Heure h)   { this.heures.add(h);    }
	public void supprimerHeure(Heure h) { this.heures.remove(h); }

	// Ajout et suppression de module
	public void ajouterModule(Module m)   { this.modules.add(m);    }
	public void supprimerModule(Module m) { this.modules.remove(m); }

	// Calcul du nombre d'heures équivalent TD de l'intervenant
	public float getNbHeures() {
		float nbHeures = 0;
		for (Heure h : this.heures)
			nbHeures += h.getDuree() * h.getTypeHeure().getCoeff();
		
		return nbHeures;
	}

	// Vérification des modules de l'intervenant
	public void verificationModule( Module m )
	{
		for ( Heure h : this.heures )
			if ( ! (h.getModule() == m) )
				this.modules.remove(m);
	}

	/**
	 * Méthode toString de la classe Intervenant
	 * @return L'intervenant sous forme de chaîne de caractère
	 */
	public String toString() {
		return this.idIntervenant + " " + this.prenom + " " + this.nom + " (" + this.statut + ", " + this.nbEqTD + "eqTD)";
	}
}