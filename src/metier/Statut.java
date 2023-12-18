package metier;

/**
 * Classe représentant un statut d'enseignant par exemple (vacataire, enseignant chercheur, etc)
 * @author Alexandre Pois--Bons - Florian Janot
 * @version 1.0
 */
public class Statut {
	private String nomStatut;
	private int    nbHeureService;
	private int    nbHeuresMax;
	private float  coeffTP;

	/**
	 * Constructeur par défaut
	 * @param nomStatut       Nom du statut
	 * @param nbHeuresService Nombre d'heures de service
	 * @param nbHeuresMax     Nombre d'heures maximum possible
	 * @param coeffTP         Coefficient de TP
	 */
	public Statut(String nomStatut, int nbHeuresService, int nbHeuresMax, float coeffTP) {
		this.nomStatut      = nomStatut;
		this.nbHeureService = nbHeuresService;
		this.nbHeuresMax    = nbHeuresMax;
		this.coeffTP        = coeffTP;
	}

	// Getters
	public String getNomStatut()      { return this.nomStatut;      }
	public int    getNbHeureService() { return this.nbHeureService; }
	public int    getNbHeuresMax()    { return this.nbHeuresMax;    }
	public float  getCoeffTP()        { return this.coeffTP;        }

	// Setters
	public void setNomStatut(String nomStatut)         { this.nomStatut      = nomStatut;      }
	public void setNbHeureService(int nbHeureService)  { this.nbHeureService = nbHeureService; }
	public void setNbHeuresMax(int nbMaxHeures)        { this.nbHeuresMax    = nbMaxHeures;    }
	public void setCoeffTP( float coeffTP )            { this.coeffTP        = coeffTP;        }

	/**
	 * Retourne une chaîne de caractères représentant le statut
	 * @return Chaîne de caractères représentant le statut
	 */
	public String toString() {
		return this.nomStatut + " (" + this.nbHeureService + "h, " + this.nbHeuresMax + "h max, coeffTP=" + this.coeffTP + ")"; 
	}
}
