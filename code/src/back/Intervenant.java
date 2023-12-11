package back;

public class Intervenant {
    
    private int    idIntervenant;
    private String prenom;
    private String nom;
    private Statut statut;
    private int    nbEqTD;

    public Intervenant(int idIntervenant, String prenom, String nom, Statut statut, int nbEqTD ) {
        this.idIntervenant = idIntervenant;
        this.prenom        = prenom;
        this.nom           = nom;
        this.statut        = statut;
        this.nbEqTD        = nbEqTD;
    }

    public int    getIdIntervenant() { return this.idIntervenant; }
    public String getPrenom()        { return this.prenom;        }
    public String getNom()           { return this.nom;           }
    public Statut getStatut()        { return this.statut;        }
    public int    getNbEqTD()        { return this.nbEqTD;        }

    public void setIdIntervenant(int idIntervenant) { this.idIntervenant = idIntervenant; }
    public void setPrenom( String prenom )          { this.prenom        = prenom;        }
    public void setNom(String nom)                  { this.nom           = nom;           }
    public void setStatut(Statut statut)            { this.statut        = statut;        }
    public void setNbEqTD( int nbEqTD )             { this.nbEqTD        = nbEqTD;        }
}