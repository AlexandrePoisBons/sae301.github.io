package controleur;

import java.util.Scanner;

public class Infos {
	
	private String password;
	private String login;
	private String database;

	public static String NOM_FICHIER = "infos.txt";

	public Infos() {
		this.initValues();
	}

	private boolean initValues(){

		String ligne = null;

		try { // ouverture de la ressource vue comme flux de données
			Scanner sc = new Scanner(this.getClass().getResourceAsStream(Infos.NOM_FICHIER));
			//Scanner sc = new Scanner(new FileInputStream(nomFichier), Charset.forName("UTF-8"));
			// traitement
			while (sc.hasNextLine()) {
				ligne = sc.nextLine();
				this.traiteLigne(ligne);
			}
			sc.close();
		} catch(Exception exc) {
			System.out.println("Erreur fichier" + exc);
			return false;
		}

		return true;
	}

	private void traiteLigne(String ligne) {
		// public Tweet(String compte, String dateT, String langue, int nbAbonnes, String texteTweet, int nbRetweet, int nbFavoris)
		String[] decString = ligne.split(":");

		switch (decString[0]) {
			case "database" -> this.database = decString[1];
			case "login" -> this.login = decString[1];
			case "password" -> this.password = decString[1];
		}

	}

	public String getPassword() { return this.password; }
	public String getLogin()     { return this.login;    }
	public String getDatabase() { return this.database; }

}
