package metier.db;

import java.sql.*;

import controleur.Infos;
import metier.*;

public class DB {

	private static DB dbInstance;

	private Connection connec;
	private Infos      infos;

	private DB() {

		this.infos = new Infos();

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch ( ClassNotFoundException e ) { e.printStackTrace(); }

		try {
			String url = "jdbc:postgresql://localhost:5432/" + this.infos.getDatabase();
			String login = this.infos.getLogin();
			String password = this.infos.getPassword();

			connec = DriverManager.getConnection(url,login,password);

			System.out.println("CONNEXION A LA BADO: REUSSIE");
		} catch ( SQLException e ) { e.printStackTrace(); }
	}

	public static DB getInstance() {
		if ( dbInstance == null ) { dbInstance = new DB(); }
		return dbInstance;
	}

	public void close() throws SQLException { this.connec.close(); }

	public Connection getConnection() { return this.connec; }

}
