package back;

public class Statut {
    private String nomStatut;
    private int    nbHeureService;
    private int    nbMaxHeure;
    private float  coeffTP;

    public Statut(String nomStatut, int nbHeureService, int nbMaxHeure, float coeffTP) {
        this.nomStatut      = nomStatut;
        this.nbHeureService = nbHeureService;
        this.nbMaxHeure     = nbMaxHeure;
        this.coeffTP        = coeffTP;
    }

    public String getNomStatut()      { return this.nomStatut;      }
    public int    getNbHeureService() { return this.nbHeureService; }
    public int    getNbMaxHeure()     { return this.nbMaxHeure;     }
    public float  getCoeffTP()        { return this.coeffTP;        }

    public void setNomStatut(String nomStatut)         { this.nomStatut      = nomStatut;      }
    public void setNbHeureService(int nbHeureService)  { this.nbHeureService = nbHeureService; }
    public void setNbMaxHeure(int nbMaxHeure)          { this.nbMaxHeure     = nbMaxHeure;     }
    public void setCoeffTP( int coeffTP )              { this.coeffTP        = coeffTP;        }
}