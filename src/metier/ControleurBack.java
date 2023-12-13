package metier;

import java.sql.SQLException;

import metier.db.*;

public class ControleurBack {

    private Requetes requetes;

    public ControleurBack() {
        this.requetes = new Requetes();
    }



    public boolean ajouterIntervenant(Intervenant intervenant) throws SQLException {
        this.requetes.insertIntervenant(intervenant);
        return false;
    }





}
