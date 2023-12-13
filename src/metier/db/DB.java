package metier.db;

import java.sql.*;

import metier.*;

public class DB {
	private Connection connec;
	private static DB dbInstance;

	public static void main(String[] args) { DB db = DB.getInstance(); }

	private DB() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch (ClassNotFoundException e) { e.printStackTrace(); }

		try {
			connec = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","coucou");
			System.out.println("CONNEXION A LA BADO: REUSSIE");
		} catch (SQLException e) { e.printStackTrace(); }
	}

	public static DB getInstance() {
		if ( dbInstance == null ) { dbInstance = new DB(); }
		return dbInstance;
	}

	public void close() throws SQLException { this.connec.close(); }

	public Connection getConnection() { return this.connec; }
}