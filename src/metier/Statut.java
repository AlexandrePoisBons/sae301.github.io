package metier;

/**
 * Classe représentant un statut d'enseignant par exemple (vacataire, enseignant chercheur, etc)
 * @author Serlogic
 * @version 1.0
 */

public class Statut {

	private String nomStatut;
	private float  coeffTP;
	private int    nbHeureService;
	private int    nbHeuresMax;

	/**
	 * Constructeur par défaut
	 * @param nomStatut       Nom du statut
	 * @param nbHeuresService Nombre d'heures de service
	 * @param nbHeuresMax     Nombre d'heures maximum possible
	 * @param coeffTP         Coefficient de TP
	 */
	public Statut( String nomStatut, int nbHeuresService, int nbHeuresMax, float coeffTP ) {
		this.nomStatut      = nomStatut;
		this.nbHeureService = nbHeuresService;
		this.nbHeuresMax    = nbHeuresMax;
		this.coeffTP        = coeffTP;
	}

	// Getters
	public String getNomStatut()      { return this.nomStatut;      }
	public float  getCoeffTP()        { return this.coeffTP;        }
	public int    getNbHeureService() { return this.nbHeureService; }
	public int    getNbHeuresMax()    { return this.nbHeuresMax;    }

	// Setters
	public void setNomStatut      ( String nomStatut      ) { this.nomStatut      = nomStatut;      }
	public void setNbHeureService ( int    nbHeureService ) { this.nbHeureService = nbHeureService; }
	public void setNbHeuresMax    ( int    nbMaxHeures    ) { this.nbHeuresMax    = nbMaxHeures;    }
	public void setCoeffTP        ( float  coeffTP        ) { this.coeffTP        = coeffTP;        }

	/**
	 * Retourne une chaîne de caractères représentant le statut
	 * @return Chaîne de caractères représentant le statut
	 */
	public String toString() {
		return "Statut [nomStatut="+ this.nomStatut + ", nbHeuresService=" + this.nbHeureService + "h, nbHeuresMax=" + this.nbHeuresMax + "h, coeffTP=" + this.coeffTP + "]"; 
	}

}
