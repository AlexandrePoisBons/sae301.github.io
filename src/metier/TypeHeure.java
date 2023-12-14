package metier;

import metier.db.Requetes;

/**
 * Classe TypeHeure désignant les différents types d'heures possibles ( CM, TD, TP, etc. )
 * @author Alexandre Pois--Bons - Florian Janot
 * @version 1.0
 */
public class TypeHeure {
    private static int    nbTypeHeure = Requetes.getNbTypeHeures();

    private int    idTypeHeure;
    private String nomTypeHeure;
    private float  coeff;

    /**
     * Constructeur de TypeHeure
     * @param nomTypeHeure Le nom du type d'heure
     * @param coeff        Le coefficient du type d'heure
     * @return Un objet de type TypeHeure
     */
    public TypeHeure( String nomTypeHeure, float coeff ) {
        this.idTypeHeure  = nbTypeHeure++;
        this.nomTypeHeure = nomTypeHeure;
        this.coeff        = coeff;
    }

    // Getters
    public int    getIdTypeHeure()  { return this.idTypeHeure;  }
    public String getNomTypeHeure() { return this.nomTypeHeure; }
    public float  getCoeff()        { return this.coeff;        }

    // Setters
    public void setIdTypeHeure( int idTypeHeure )     { this.idTypeHeure  = idTypeHeure;  }
    public void setNomTypeHeure(String nomTypeHeure ) { this.nomTypeHeure = nomTypeHeure; }
    public void setCoeff( float coeff )               { this.coeff        = coeff;        }

    /**
     * Méthode toString de TypeHeure
     * @return Une chaîne de caractères représentant le type d'heure
     */
    public String toString() {
        return this.idTypeHeure + " " + this.nomTypeHeure + " (coeff=" + this.coeff + ")"; 
    }
}