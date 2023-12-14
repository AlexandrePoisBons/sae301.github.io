package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import metier.db.Requetes;

public class Module {
    private static int nbModules = Requetes.getNbModules();

    private int        idModule;
    private TypeModule typeModule;
    private String     semestre;
    private String     libelle;
    private String     libelleCourt;
    private String     code;
    private int        nbEtudiants;
    private int        nbGpTD;
    private int        nbGpTP;
    private int        nbSemaines;
    private int        nbHeures;

    private HashMap<String, Integer> heureParType;

    private List<Intervenant> intervenants;
    private List<Heure>       heures;

    public static Module creerModule( TypeModule typeModule, String semestre, String libelle, String libelleCourt, String code, int nbEtudiants, int nbGpTD, int nbGpTP, int nbSemaines, int nbHeures ) {
        if ( typeModule == null || semestre == null || semestre.isEmpty() || libelle == null || libelle.isEmpty() || libelleCourt == null || libelleCourt.isEmpty() || code == null || code.isEmpty() || nbEtudiants < 0 || nbGpTD < 0 || nbGpTP < 0 || nbSemaines < 0 || nbHeures < 0 )
            return null;

        return new Module( typeModule, semestre, libelle, libelleCourt, code, nbEtudiants, nbGpTD, nbGpTP, nbSemaines, nbHeures);
    }

    private Module( TypeModule typeModule, String semestre, String libelle, String libelleCourt, String code, int nbEtudiants, int nbGpTD, int nbGpTP, int nbSemaines, int nbHeures ) {
        this.idModule     = Module.nbModules++;
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

        this.heureParType = new HashMap<String, Integer>();
        initHash();
    }

    public int               getIdModule()     { return this.idModule;     }
    public TypeModule        getTypeModule()   { return this.typeModule;   }
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
    public void setTypeModule( TypeModule typeModule ) { this.typeModule   = typeModule;   }
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

    /**
     * Méthode permettant d'ajouter une heure à un module
     * @param h Heure à ajouter
     */
    public void ajouterHeure( Heure h ) {
        this.heures.add( h );
        
        for ( Intervenant i : h.getIntervenants() ) {   
            if ( !this.intervenants.contains( i ) ) {
                this.intervenants.add( i );
                i.ajouterModule( this );
            }
            i.ajouterHeure( h );
        }

        // Ajoute la durée de l'heure au total du type d'heure correspondant
        this.heureParType.put( h.getTypeHeure().getNomTypeHeure(), h.getDuree() );
    }

    /**
     * Méthode permettant de supprimer une heure à un module
     * @param h Heure à supprimer
     */
    public void supprimerHeure( Heure h ) {
        this.heures.remove( h );

        for ( Intervenant i : h.getIntervenants() )
            i.verificationModule( this );

        this.heureParType.put( h.getTypeHeure().getNomTypeHeure(), -h.getDuree() );
    }

    /**
     * Méthode permettant d'initialiser le HashMap heureParType en fonction du type de module
     * Cette HashMap permet de stocker le nombre d'heures affectées à un module par type d'heure
     */
    public void initHash() {
        switch ( this.typeModule.getLibelle() ) {
            case "CM" :
                this.heureParType.put( "h CM" , 0 );
                this.heureParType.put( "h TD" , 0 );
                this.heureParType.put( "h TP" , 0 );
                break;

            case "TD" :
                this.heureParType.put( "h TD" , 0 );
                this.heureParType.put( "h TP" , 0 );
                break;

            case "SAE" :
                this.heureParType.put( "h Sae", 0 );
                this.heureParType.put( "Tut"  , 0 );
                break;
        }
    }      
    /**
     *  Méthode permettant de récupérer le nombre d'heures affectées à un module au moment de l'appel
     * @return Retourne le nombre d'heures affectées en float
     */
    public float getNbHeuresAffecte() {
        float nbHeuresAffectees = 0;
        
        for ( Heure h : this.heures )
            nbHeuresAffectees += h.getDuree();
        
        return nbHeuresAffectees;
    }

    /**
     * Méthode permettant de vérifier si le nombre d'heures affectées à un module est égal au nombre d'heures prévues
     * @return boolean 
     */
    public boolean verification()
    {
        float heuresAffectees = 0;
        
        for (String s : this.heureParType.keySet() )
            heuresAffectees += this.heureParType.get(s);

        return ( heuresAffectees == this.nbHeures );      
    }

  
    public String toString() {
        return "Module [idModule=" + this.idModule + ", typeModule=" + this.typeModule + ", semestre=" + this.semestre + ", libelle=" + this.libelle + ", libelleCourt=" + this.libelleCourt + ", code=" + this.code + ", nbEtudiants=" + this.nbEtudiants + ", nbGpTD=" + this.nbGpTD + ", nbGpTP=" + this.nbGpTP + ", nbSemaines=" + this.nbSemaines + ", nbHeures=" + this.nbHeures + ", intervenants=" + this.intervenants + ", heures=" + this.heures +", commentaire="+  "]";
    }
}