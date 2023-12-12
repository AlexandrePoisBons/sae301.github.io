package back;

import java.util.ArrayList;
import java.util.List;

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
    
    private List<Intervenant> intervenants;
    private List<Heure>       heures;

    public static Module creerModule( int idModule, String typeModule, String semestre, String libelle, String libelleCourt, String code, int nbEtudiants, int nbGpTD, int nbGpTP, int nbSemaines, int nbHeures ) {
        if ( typeModule == null || typeModule.isEmpty() || semestre == null || semestre.isEmpty() || libelle == null || libelle.isEmpty() || libelleCourt == null || libelleCourt.isEmpty() || code == null || code.isEmpty() || nbEtudiants < 0 || nbGpTD < 0 || nbGpTP < 0 || nbSemaines < 0 || nbHeures < 0 )
            return null;

        return new Module( idModule, typeModule, semestre, libelle, libelleCourt, code, nbEtudiants, nbGpTD, nbGpTP, nbSemaines, nbHeures );
    }



    private Module( int idModule, String typeModule, String semestre, String libelle, String libelleCourt, String code, int nbEtudiants, int nbGpTD, int nbGpTP, int nbSemaines, int nbHeures ) {
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

        this.intervenants = new ArrayList<Intervenant>();
        this.heures       = new ArrayList<Heure>();
    }

    public int               getIdModule()     { return this.idModule;     }
    public String            getTypeModule()   { return this.typeModule;   }
    public String            getSemestre()     { return this.semestre;     }
    public String            getLibelle()      { return this.libelle;      }
    public String            getLibelleCourt() { return this.libelleCourt; }
    public String            getCode()         { return this.code;         }
    public int               getNbEtudiants()  { return this.nbEtudiants;  }
    public int               getNbGpTD()       { return this.nbGpTD;       }
    public int               getNbGpTP()       { return this.nbGpTP;       }
    public int               getNbSemaines()   { return this.nbSemaines;   }
    public int               getNbHeures()     { return this.nbHeures;     }
    public List<Intervenant> getIntervenants() { return this.intervenants; }
    public List<Heure>       getHeures()       { return this.heures;       }


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
    public void setHeures ( List<Heure> heures )       { this.heures       = heures;       }
    public void setIntervenant(List<Intervenant> i )   { this.intervenants = i;            }


    public float getNbHeuresAffecte() {
        float nbHeuresAffectees = 0;
        for ( Heure h : this.heures )
            nbHeuresAffectees += h.getDuree();
        return nbHeuresAffectees;
    }

    public String toString() {
        return "Module [idModule=" + this.idModule + ", typeModule=" + this.typeModule + ", semestre=" + this.semestre + ", libelle=" + this.libelle + ", libelleCourt=" + this.libelleCourt + ", code=" + this.code + ", nbEtudiants=" + this.nbEtudiants + ", nbGpTD=" + this.nbGpTD + ", nbGpTP=" + this.nbGpTP + ", nbSemaines=" + this.nbSemaines + ", nbHeures=" + this.nbHeures + ", intervenants=" + this.intervenants + ", heures=" + this.heures + "]";
    }
}