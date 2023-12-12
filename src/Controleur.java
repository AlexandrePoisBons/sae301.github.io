import back.db.*;
import back.*;
import back.Module;

public class Controleur {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Requetes req = new Requetes();

        req.insertIntervenant(Intervenant.creerIntervenant("Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 40.3f));
        System.out.println("insert: " + req.getIntervenants());
        req.updateIntervenant(Intervenant.creerIntervenant("Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 150f));
        System.out.println("update: " + req.getIntervenants());
        req.deleteIntervenant(Intervenant.creerIntervenant("Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 150f));
        System.out.println("delete: " + req.getIntervenants());

        req.insertModule(Module.creerModule("Stage/suivi", "s4", "Stage", "Stage", "S4.ST", 52, 2, 4, 52, 0));
        System.out.println("insert: " + req.getModules());
        req.updateModule(Module.creerModule("Stage/suivi", "s6", "Stage", "Stage", "S4.ST", 52, 4, 4, 52, 10));
        System.out.println("update: " + req.getModules());
        req.deleteModule(Module.creerModule("Stage/suivi", "s6", "Stage", "Stage", "S4.ST", 52, 4, 4, 52, 10));
        System.out.println("delete: " + req.getModules());
    }
}
