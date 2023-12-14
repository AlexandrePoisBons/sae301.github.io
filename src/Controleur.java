
import ihm.accueil.*;
import metier.*;
import metier.db.*;


public class Controleur {

    private ControleurMetier metier;

    public Controleur() {
        this.metier = new ControleurMetier();
    }

    public static void main(String[] args) throws Exception {
        new Controleur();
    }

}
