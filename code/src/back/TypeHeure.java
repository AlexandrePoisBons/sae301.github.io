package back;

public class TypeHeure {
    private int idTypeHeure;
    private float coeff;



    public TypeHeure(int idTypeHeure, float coeff) {
        this.idTypeHeure = idTypeHeure;
        this.coeff = coeff;
    }

    public int getIdTypeHeure() {
        return idTypeHeure;
    }

    public void setIdTypeHeure(int idTypeHeure) {
        this.idTypeHeure = idTypeHeure;
    }

    public float getCoeff() {
        return coeff;
    }

    public void setCoeff(float coeff) {
        this.coeff = coeff;
    }
}
