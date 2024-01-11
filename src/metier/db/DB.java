package metier.db;

import java.sql.*;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import controleur.Infos;

public class DB {

	private static DB dbInstance;

	private Connection connec;
	private Infos      infos;

	private DB() {

		this.infos = new Infos();

		try {
			Class.forName("org.postgresql.Driver");
			// System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch ( ClassNotFoundException e ) { e.printStackTrace(); }


		if(this.infos.getPasswordLdap().equals("")){
			try {
				String url = Infos.URL_DATABASE + this.infos.getDatabase();
				String login = this.infos.getLogin();
				String password = this.infos.getPassword();

				connec = DriverManager.getConnection(url,login,password);

				// System.out.println("CONNEXION A LA BADO: REUSSIE");
			} catch ( SQLException e ) { e.printStackTrace(); }
		}else{
			try{
				String dbHost = "woody.iut.univ-lehavre.fr";
				String sshHost = "c-corton.iut.univ-lehavre.fr";
				String login = this.infos.getLogin();
				String password = this.infos.getPassword();
				String passwordLdap = this.infos.getPasswordLdap();
				

				int sshPort = 4660; // SSH port number
				JSch jsch = new JSch();
				Session session = jsch.getSession(login, sshHost, sshPort);
				session.setPassword(passwordLdap);
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();

				// Set up port forwarding (SSH tunnel)
				int localPort = 5432; // Local port for tunneling
				int remotePort = 5432; // Remote PostgreSQL port
				session.setPortForwardingL(localPort, dbHost, remotePort);
				Class.forName("org.postgresql.Driver");

				// Connect to the PostgreSQL database through the SSH tunnel
				String jdbcUrl = "jdbc:postgresql://localhost:5432/" + login;
				this.connec = DriverManager.getConnection(jdbcUrl, login, password);

				// Perform database operations (e.g., execute queries)



				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// System.out.println("CONNEXION A LA BADO: REUSSIE");
			} 
			catch ( SQLException e ) { e.printStackTrace(); } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (JSchException e) {
				e.printStackTrace();
			}
		}
	}

	public static DB getInstance() throws ClassNotFoundException, JSchException {
		if ( dbInstance == null ) { dbInstance = new DB(); }
		return dbInstance;
	}

	public void close() throws SQLException { this.connec.close(); }

	public Connection getConnection() { return this.connec; }

}
