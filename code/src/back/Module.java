package back;

public class Module {
    private int    idModule;
    private String typeModule;
    private String semestre;
    private String libele;
    private String libeleCourt;
    private String code;
    private int    nbEtudiants;
    private int    nbGpTD;
    private int    nbGpTP;
    private int    nbSemaine;
    private int    nbHeure;

    public Module( int idModule, String typeModule, String semestre, String libele, String libeleCourt, String code, int nbEtudiants, int nbGpTD, int nbGpTP, int nbSemaine, int nbHeures ) {
        this.idModule    = idModule;
        this.typeModule  = typeModule;
        this.semestre    = semestre;
        this.libele      = libele;
        this.libeleCourt = libeleCourt;
        this.code        = code;
        this.nbEtudiants = nbEtudiants;
        this.nbGpTD      = nbGpTD;
        this.nbGpTP      = nbGpTP;
        this.nbSemaine   = nbSemaine;
        this.nbHeure     = nbHeures;
    }

    public int    getIdModule()    { return this.idModule;    }
    public String getTypeModule()  { return this.typeModule;  }
    public String getSemestre()    { return this.semestre;    }
    public String getLibele()      { return this.libele;      }
    public String getLibeleCourt() { return this.libeleCourt; }
    public String getCode()        { return this.code;        }
    public int    getNbEtudiants() { return this.nbEtudiants; }
    public int    getNbGpTD()      { return this.nbGpTD;      }
    public int    getNbGpTP()      { return this.nbGpTP;      }
    public int    getNbSemaine()   { return this.nbSemaine;   }
    public int    getNbHeure()     { return this.nbHeure;     }


    public void setIdModule( int idModule )          { this.idModule    = idModule;    }
    public void setTypeModule( String typeModule )   { this.typeModule  = typeModule;  }
    public void setSemestre( String semestre )       { this.semestre    = semestre;    }
    public void setLibele( String libele )           { this.libele      = libele;      }
    public void setLibeleCourt( String libeleCourt ) { this.libeleCourt = libeleCourt; }
    public void setCode( String code )               { this.code        = code;        }
    public void setNbEtudiants( int nbEtudiants )    { this.nbEtudiants = nbEtudiants; }
    public void setNbGpTD( int nbGpTD )              { this.nbGpTD      = nbGpTD;      }
    public void setNbGpTP( int nbGpTP )              { this.nbGpTP      = nbGpTP;      }
    public void setNbSemaine( int nbSemaine )        { this.nbSemaine   = nbSemaine;   }
}