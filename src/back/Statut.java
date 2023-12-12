package back;

public class Statut {
    private String nomStatut;
    private int    nbMinHeure;
    private int    nbMaxHeure;
    private float  coeffTP;


    public Statut(String nomStatut, int nbMinHeure, int nbMaxHeure, float coeffTP) {
        this.nomStatut  = nomStatut;
        this.nbMinHeure = nbMinHeure;
        this.nbMaxHeure = nbMaxHeure;
        this.coeffTP    = coeffTP;
    }

    public String getNomStatut() { return this.nomStatut;  }
    public int getNbMinHeure()   { return this.nbMinHeure; }
    public int getNbMaxHeure()   { return this.nbMaxHeure; }
    public float getCoeffTP()    { return this.coeffTP;    }

    public void setNomStatut(String nomStatut) { this.nomStatut  = nomStatut;  }
    public void setNbMinHeure(int nbMinHeure)  { this.nbMinHeure = nbMinHeure; }
    public void setNbMaxHeure(int nbMaxHeure)  { this.nbMaxHeure = nbMaxHeure; }
    public void setCoeffTP( int coeffTP )      { this.coeffTP    = coeffTP;    }
}