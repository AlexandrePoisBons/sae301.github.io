package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.db.*;

public class ControleurMetier {

    private Requetes requetes;
    private List<Statut>    statuts;
    private List<TypeHeure> typesHeures;


    public ControleurMetier() {
        this.requetes = new Requetes();

        this.statuts     = new ArrayList<Statut>();
        this.typesHeures = new ArrayList<TypeHeure>();
    }


    public boolean ajouterIntervenant(Intervenant intervenant) throws SQLException {
        this.requetes.insertIntervenant(intervenant);
        return false;
    }
}