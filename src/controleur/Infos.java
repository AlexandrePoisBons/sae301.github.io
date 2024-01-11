package controleur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Infos {

	private String password;
	private String login;
	private String database;

	public static final String NOM_FICHIER = "infos.txt";

	public static final String URL_DATABASE = "jdbc:postgresql://localhost:5432/";


	public Infos() {
		this.database = "";
		this.login    = "";
		this.password = "";

		this.initValues();
	}

	private boolean initValues() {

		String ligne = null;

		try {
			Scanner sc = new Scanner(this.getClass().getResourceAsStream(Infos.NOM_FICHIER));

			while ( sc.hasNextLine() ) {
				ligne = sc.nextLine();
				this.traiteLigne(ligne);
			}
			sc.close();
		} catch( Exception exc ) {
			// System.out.println("Erreur fichier " + exc);
			return false;
		}

		return true;
	}


	private void traiteLigne( String ligne ) {
		String[] decString = ligne.split(":");

		switch ( decString[0] ) {
			case "database" -> this.database = decString[1];
			case "login"    -> this.login    = decString[1];
			case "password" -> this.password = decString[1];
		}

	}

	public static void ecrire(String url, String login, String password) {
		String content = "";

		try {
			File file = new File("./bin/controleur/"+Infos.NOM_FICHIER);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
			content += "database:" + url      + "\n";
			content += "login:"    + login    + "\n";
			content += "password:" + password;

			writer.write(content);

			writer.close();
		} catch (IOException e) { /*e.printStackTrace();*/ }

	}

	public String getDatabase() { return this.database; }
	public String getLogin()    { return this.login;    }
	public String getPassword() { return this.password; }

}
