package metier;

import metier.db.Requetes;

/**
 * Classe TypeHeure désignant les différents types d'heures possibles ( CM, TD, TP, etc. )
 * @author Serlogic
 * @version 2.0
 */
public class TypeHeure {

	private static int nbTypeHeure = Requetes.getNbTypeHeures();

	private String nomTypeHeure;
	private float  coeff;
	private int    idTypeHeure;


	public static TypeHeure initTypeHeure( int idTypeHeure, String nomTypeHeure, float coeff ) {
		if ( idTypeHeure>=1 )
			return new TypeHeure(idTypeHeure, nomTypeHeure, coeff);

		return null;
	}

	private TypeHeure( String nomTypeHeure, float coeff ) {
		this.idTypeHeure  = ++nbTypeHeure;
		this.nomTypeHeure = nomTypeHeure;
		this.coeff        = coeff;
	}

	private TypeHeure( int idTypeHeure, String nomTypeHeure, float coeff ) {
		this.idTypeHeure  = idTypeHeure;
		this.nomTypeHeure = nomTypeHeure;
		this.coeff        = coeff;
	}

	// Getters
	public int    getIdTypeHeure()  { return this.idTypeHeure;  }
	public String getNomTypeHeure() { return this.nomTypeHeure; }
	public float  getCoeff()        { return this.coeff;        }

	// Setters
	public void setIdTypeHeure ( int    idTypeHeure  ) { this.idTypeHeure  = idTypeHeure;  }
	public void setNomTypeHeure( String nomTypeHeure ) { this.nomTypeHeure = nomTypeHeure; }
	public void setCoeff       ( float  coeff        ) { this.coeff        = coeff;        }

	/**
	 * Méthode toString de TypeHeure
	 * @return Une chaîne de caractères représentant le type d'heure
	 */
	public String toString() {
		return "TypeHeure [idTypeHeure="+this.idTypeHeure + ", nomTypeHeure=" + this.nomTypeHeure + ", coeff=" + this.coeff + "]"; 
	}

}