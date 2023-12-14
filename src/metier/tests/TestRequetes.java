package metier.tests;

import metier.*;
import metier.db.*;
import metier.Module;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

public class TestRequetes {

	@Test
	public void testRequete1() {
		try{
			System.out.println("Hello, World!");

			Requetes req = new Requetes();

			// Intervenant
			Statut s = new Statut("vacataire", 120, 87, 2/3);
			System.out.println("\n--Intervenant--");
			req.insertStatut(s);
			req.insertIntervenant(Intervenant.creerIntervenant("Thomas", "Colignon", s, 40.3f));
			System.out.println("insert: " + req.getIntervenants());
			req.updateIntervenant(Intervenant.creerIntervenant("Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 150f));
			System.out.println("update: " + req.getIntervenants());
			req.deleteIntervenant(Intervenant.creerIntervenant("Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 150f));
			System.out.println("delete: " + req.getIntervenants() + "\n\n");

			// Module
			System.out.println("--Module--");
			req.insertModule(Module.creerModule("Stage/suivi", "S4", "Stage", "Stage", "S4.ST", 52, 2, 4, 52, 0  ));
			System.out.println("insert: " + req.getModules());
			req.updateModule(Module.creerModule("Stage/suivi", "S6", "Stage", "Stage", "S4.ST", 52, 4, 4, 52, 10 ));
			System.out.println("update: " + req.getModules());
			req.deleteModule(Module.creerModule("Stage/suivi", "S6", "Stage", "Stage", "S4.ST", 52, 4, 4, 52, 10 ));
			System.out.println("delete: " + req.getModules() + "\n\n");

			// TypeHeure
			System.out.println("--TypeHeure--");
			req.insertTypeHeure(new TypeHeure("sae", 2f));
			System.out.println("insert: " + req.getTypesHeures());
			req.updateTypeHeure(new TypeHeure("td", 1f));
			System.out.println("update: " + req.getTypesHeures());
			req.deleteTypeHeure(new TypeHeure("td", 1f));
			System.out.println("delete: " + req.getTypesHeures());


			// Heure
			System.out.println("\n--Heure--");
			Module module = Module.creerModule("Stage/suivi", "S4", "Stage", "Stage", "S4.ST", 52, 2, 4, 52, 0 );
			req.insertModule(module);
			System.out.println("module: "+req.getModules());
			TypeHeure typeHeure = new TypeHeure("sae", 2f);
			req.insertTypeHeure(typeHeure);
			System.out.println("typeHeure: "+req.getTypesHeures());
			Heure heure = Heure.creerHeure(Module.creerModule("Stage/suivi", "s4", "Stage", "Stage", "S4.ST", 52, 2, 4, 52, 0 ), typeHeure , 10, "relou");
			System.out.println("\n\n"+heure.toString()+"\n\n");
			req.insertHeure(heure);
			System.out.println("insert: " + req.getHeures());
			heure.setDuree(25);
			req.updateHeure(heure);
			System.out.println("update: " + req.getHeures());
			


			assertEquals(heure.toString(),req.getHeures().get(0).toString());
			// req.deleteHeure(heure);
			// System.out.println("delete: " + req.getHeures() + "\n\n");

			req.close();
		} catch ( SQLException e ) { e.printStackTrace(); }

	}



	public static void main(String[] args) throws Exception {


	}
}
