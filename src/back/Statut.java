package back;

public class Statut {
    private String nomStatut;
    private int    nbHeureService;
    private int    nbHeuresMax;
    private float  coeffTP;

    public Statut(String nomStatut, int nbHeuresService, int nbHeuresMax, float coeffTP) {
        this.nomStatut      = nomStatut;
        this.nbHeureService = nbHeuresService;
        this.nbHeuresMax    = nbHeuresMax;
        this.coeffTP        = coeffTP;
    }

    public String getNomStatut()      { return this.nomStatut;      }
    public int    getNbHeureService() { return this.nbHeureService; }
    public int    getNbHeuresMax()    { return this.nbHeuresMax;    }
    public float  getCoeffTP()        { return this.coeffTP;        }

    public void setNomStatut(String nomStatut)         { this.nomStatut      = nomStatut;      }
    public void setNbHeureService(int nbHeureService)  { this.nbHeureService = nbHeureService; }
    public void setNbHeuresMax(int nbMaxHeures)        { this.nbHeuresMax    = nbMaxHeures;    }
    public void setCoeffTP( int coeffTP )              { this.coeffTP        = coeffTP;        }

    public String toString() {
        return this.nomStatut + " (" + this.nbHeureService + "h, " + this.nbHeuresMax + "h max, coeffTP=" + this.coeffTP + ")"; 
    }
}
