package back;

public class TypeHeure {
    private int    idTypeHeure;
    private String nomTypeHeure;
    private float  coeff;

    public TypeHeure( int idTypeHeure, String nomTypeHeure, float coeff ) {
        this.idTypeHeure  = idTypeHeure;
        this.nomTypeHeure = nomTypeHeure;
        this.coeff        = coeff;
    }

    public int    getIdTypeHeure()  { return this.idTypeHeure;  }
    public String getNomTypeHeure() { return this.nomTypeHeure; }
    public float  getCoeff()        { return this.coeff;        }

    public void setIdTypeHeure( int idTypeHeure )     { this.idTypeHeure  = idTypeHeure;  }
    public void setNomTypeHeure(String nomTypeHeure ) { this.nomTypeHeure = nomTypeHeure; }
    public void setCoeff( float coeff )               { this.coeff        = coeff;        }
}
