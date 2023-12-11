package back;

public class Module {
    private int    idModule;
    private String typeModule;
    private String semestre;
    private String libelle;
    private String libelleCourt;
    private String code;
    private int    nbEtudiants;
    private int    nbGpTD;
    private int    nbGpTP;
    private int    nbSemaines;
    private int    nbHeures;

    public Module( int idModule, String typeModule, String semestre, String libelle, String libelleCourt, String code, int nbEtudiants, int nbGpTD, int nbGpTP, int nbSemaines, int nbHeures ) {
        this.idModule     = idModule;
        this.typeModule   = typeModule;
        this.semestre     = semestre;
        this.libelle      = libelle;
        this.libelleCourt = libelleCourt;
        this.code         = code;
        this.nbEtudiants  = nbEtudiants;
        this.nbGpTD       = nbGpTD;
        this.nbGpTP       = nbGpTP;
        this.nbSemaines   = nbSemaines;
        this.nbHeures     = nbHeures;
    }

    public int    getIdModule()     { return this.idModule;     }
    public String getTypeModule()   { return this.typeModule;   }
    public String getSemestre()     { return this.semestre;     }
    public String getLibelle()      { return this.libelle;      }
    public String getLibelleCourt() { return this.libelleCourt; }
    public String getCode()         { return this.code;         }
    public int    getNbEtudiants()  { return this.nbEtudiants;  }
    public int    getNbGpTD()       { return this.nbGpTD;       }
    public int    getNbGpTP()       { return this.nbGpTP;       }
    public int    getNbSemaines()   { return this.nbSemaines;   }
    public int    getNbHeures()     { return this.nbHeures;     }


    public void setIdModule( int idModule )            { this.idModule     = idModule;     }
    public void setTypeModule( String typeModule )     { this.typeModule   = typeModule;   }
    public void setSemestre( String semestre )         { this.semestre     = semestre;     }
    public void setLibelle( String libelle )           { this.libelle      = libelle;      }
    public void setLibelleCourt( String libelleCourt ) { this.libelleCourt = libelleCourt; }
    public void setCode( String code )                 { this.code         = code;         }
    public void setNbEtudiants( int nbEtudiants )      { this.nbEtudiants  = nbEtudiants;  }
    public void setNbGpTD( int nbGpTD )                { this.nbGpTD       = nbGpTD;       }
    public void setNbGpTP( int nbGpTP )                { this.nbGpTP       = nbGpTP;       }
    public void setNbSemaines( int nbSemaines )        { this.nbSemaines   = nbSemaines;   }
}