package metier;

import java.sql.SQLException;

import metier.db.*;

public class ControleurMetier {

    private Requetes requetes;

    public ControleurMetier() {
        this.requetes = new Requetes();
    }



    public boolean ajouterIntervenant(Intervenant intervenant) throws SQLException {
        this.requetes.insertIntervenant(intervenant);
        return false;
    }



}
