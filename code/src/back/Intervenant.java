package back;

public class Intervenant {
    private String nom;
    private String prenom;
    private Statut statut;

    public Intervenant(String nom, String prenom, Statut statut){
        this.nom    = nom;
        this.prenom = prenom;
        this.statut = statut;
    }

    public String getNom()    { return this.nom;    }
    public String getPrenom() { return this.prenom; }
    public Statut getStatut() { return this.statut; }

    public void setNom(String nom)       { this.nom    = nom;    }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setStatut(Statut statut) { this.statut = statut; }
}