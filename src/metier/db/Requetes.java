package metier.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import controleur.Infos;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.Statut;
import metier.TypeHeure;



public class Requetes {

	private DB db;
	private Connection connec;

	// Intervenant
	private PreparedStatement psSelectI;
	private PreparedStatement psInsertI;
	private PreparedStatement psDeleteI;
	private PreparedStatement psUpdateI;

	// Heure
	private PreparedStatement psSelectH;
	private PreparedStatement psInsertH;
	private PreparedStatement psDeleteH;
	private PreparedStatement psUpdateH;

	// TypeHeure
	private PreparedStatement psSelectTH;
	private PreparedStatement psInsertTH;
	private PreparedStatement psDeleteTH;
	private PreparedStatement psUpdateTH;

	// Module
	private PreparedStatement psSelectM;
	private PreparedStatement psInsertM;
	private PreparedStatement psDeleteM;
	private PreparedStatement psUpdateM;

	// Statut
	private PreparedStatement psSelectS;
	private PreparedStatement psInsertS;
	private PreparedStatement psDeleteS;
	private PreparedStatement psUpdateS;

	// Intervenant_Module
	private PreparedStatement psSelectIM;
	private PreparedStatement psInsertIM;
	private PreparedStatement psDeleteIM;
	private PreparedStatement psUpdateIM;

	// Intervenant_Heure
	private PreparedStatement psSelectIH;
	private PreparedStatement psInsertIH;
	private PreparedStatement psDeleteIH;
	private PreparedStatement psUpdateIH;

	// Heure_Module
	private PreparedStatement psSelectHM;
	private PreparedStatement psInsertHM;
	private PreparedStatement psDeleteHM;
	private PreparedStatement psUpdateHM;

	// TypeHeure_Module
	private PreparedStatement psSelectTHM;
	private PreparedStatement psInsertTHM;
	private PreparedStatement psDeleteTHM;
	// private PreparedStatement psUpdateTHM;

	private static List<Integer>     alRandom;

	public Requetes() {
		
		Requetes.alRandom = new ArrayList<Integer>();

		try {
			this.db     = DB.getInstance();
		} catch (ClassNotFoundException | JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// TODO Auto-generated catch block

		this.connec = this.db.getConnection();

		try {
			this.psSelectI = this.connec.prepareStatement("SELECT * FROM Intervenant WHERE id_intervenant=?;");
			this.psInsertI = this.connec.prepareStatement("INSERT INTO Intervenant VALUES(?,?,?,?,?);");
			this.psDeleteI = this.connec.prepareStatement("DELETE FROM Intervenant WHERE id_intervenant=?;");
			this.psUpdateI = this.connec.prepareStatement("UPDATE Intervenant SET nom=?, prenom=?, nb_equivalent_td=?, nom_statut=? WHERE id_intervenant=?;");

			this.psSelectH = this.connec.prepareStatement("SELECT * FROM Heure WHERE id_heure=?;");
			this.psInsertH = this.connec.prepareStatement("INSERT INTO Heure (id_heure, id_module, id_type_heure, nb_semaines, nb_gp_nb_h, duree, commentaire) VALUES(?,?,?,?,?,?,?);");
			this.psDeleteH = this.connec.prepareStatement("DELETE FROM Heure WHERE id_heure=?;");
			this.psUpdateH = this.connec.prepareStatement("UPDATE Heure SET id_module=?, id_type_heure=?, nb_semaines=?, nb_gp_nb_h=?, duree=?, commentaire=? WHERE id_heure=?;");

			this.psSelectTH = this.connec.prepareStatement("SELECT * FROM Type_Heure WHERE id_type_heure=?;");
			this.psInsertTH = this.connec.prepareStatement("INSERT INTO Type_Heure VALUES(?,?,?);");
			this.psDeleteTH = this.connec.prepareStatement("DELETE FROM Type_Heure WHERE id_type_heure=?;");
			this.psUpdateTH = this.connec.prepareStatement("UPDATE Type_Heure SET coeff=? WHERE id_type_heure=?;");

			this.psSelectM = this.connec.prepareStatement("SELECT * FROM Module WHERE id_module=?;");
			this.psInsertM = this.connec.prepareStatement("INSERT INTO Module VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
			this.psDeleteM = this.connec.prepareStatement("DELETE FROM Module WHERE id_module=?;");
			this.psUpdateM = this.connec.prepareStatement("UPDATE Module SET type_module=?, semestre=?, libelle=?, libelle_court=?, code=?, nb_etudiants=?, nb_gp_td=?, nb_gp_tp=?, nb_semaines=?, nb_heures=?, valide=? WHERE id_module=?;");

			this.psSelectS = this.connec.prepareStatement("SELECT * FROM Statut WHERE nom_statut=?;");
			this.psInsertS = this.connec.prepareStatement("INSERT INTO Statut VALUES(?,?,?,?);");
			this.psDeleteS = this.connec.prepareStatement("DELETE FROM Statut WHERE nom_Statut=?;");
			this.psUpdateS = this.connec.prepareStatement("UPDATE Statut SET nb_heures_service=?, nb_heures_maxi=?, coeff_tp=? WHERE nom_statut=?;");

			this.psSelectIM = this.connec.prepareStatement("SELECT * FROM Intervenant_Module WHERE id_intervenant=? AND id_module=?;");
			this.psInsertIM = this.connec.prepareStatement("INSERT INTO Intervenant_Module VALUES(?,?);");
			this.psDeleteIM = this.connec.prepareStatement("DELETE FROM Intervenant_Module WHERE id_intervenant=? AND id_module=?;");
			this.psUpdateIM = this.connec.prepareStatement("UPDATE Intervenant_Module SET id_intervenant=?, id_module=? WHERE id_intervenant=? AND id_module=?;");

			this.psSelectIH = this.connec.prepareStatement("SELECT * FROM Intervenant_Heure WHERE id_intervenant=? AND id_heure=?;");
			this.psInsertIH = this.connec.prepareStatement("INSERT INTO Intervenant_Heure VALUES(?,?);");
			this.psDeleteIH = this.connec.prepareStatement("DELETE FROM Intervenant_Heure WHERE id_intervenant=? AND id_heure=?;");
			this.psUpdateIH = this.connec.prepareStatement("UPDATE Intervenant_Heure SET id_intervenant=?, id_heure=? WHERE id_intervenant=? AND id_heure=?;");

			this.psSelectHM = this.connec.prepareStatement("SELECT * FROM Heure_Module WHERE id_heure=? AND id_module=?;");
			this.psInsertHM = this.connec.prepareStatement("INSERT INTO Heure_Module VALUES(?,?);");
			this.psDeleteHM = this.connec.prepareStatement("DELETE FROM Heure_Module WHERE id_heure=? AND id_module=?;");
			this.psUpdateHM = this.connec.prepareStatement("UPDATE Heure_Module SET id_heure=?, id_module=? WHERE id_heure=? AND id_module=?;");

			this.psSelectTHM = this.connec.prepareStatement("SELECT * FROM TypeHeure_Module WHERE id_module=?;");
			this.psInsertTHM = this.connec.prepareStatement("INSERT INTO TypeHeure_Module VALUES(?,?,?,?,?);");
			this.psDeleteTHM = this.connec.prepareStatement("DELETE FROM TypeHeure_Module WHERE id_module=?;");
			

		} catch( SQLException e ) { e.printStackTrace(); }

	}

	public void close() throws SQLException { this.db.close(); }

	public static Connection getConnect(){

		Infos infos = new Infos();
		Connection connect = null;

		if(infos.getPasswordLdap().equals("")){
			
			try {
				String url = Infos.URL_DATABASE + infos.getDatabase();
				String login = infos.getLogin();
				String password = infos.getPassword();

				connect = DriverManager.getConnection(url,login,password);

				// System.out.println("CONNEXION A LA BADO: REUSSIE");
			} catch ( SQLException e ) { e.printStackTrace(); }
		}else{
			try{
				String dbHost = "woody.iut.univ-lehavre.fr";
				String sshHost = "c-corton.iut.univ-lehavre.fr";
				String login = infos.getLogin();
				String password = infos.getPassword();
				String passwordLdap = infos.getPasswordLdap();
				

				int sshPort = 4660; // SSH port number
				JSch jsch = new JSch();
				Session session = jsch.getSession(login, sshHost, sshPort);
				session.setPassword(passwordLdap);
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();

				// Set up port forwarding (SSH tunnel)
				int randomNum = 5432;

				Requetes.alRandom.add(5432);


				while( randomNum < 1024 || randomNum > 65535 || Requetes.alRandom.contains(randomNum)){
					randomNum = (int)(Math.random() * 99999);
					
				}
				Requetes.alRandom.add(randomNum);
				
				int localPort = randomNum; // Local port for tunneling
				int remotePort = 5432; // Remote PostgreSQL port
				session.setPortForwardingL(localPort, dbHost, remotePort);
				Class.forName("org.postgresql.Driver");

				// Connect to the PostgreSQL database through the SSH tunnel
				String jdbcUrl = "jdbc:postgresql://localhost:5432/" + login;
				connect = DriverManager.getConnection(jdbcUrl, login, password);

			}
			catch ( SQLException | JSchException | ClassNotFoundException e ) { e.printStackTrace(); }
		
		}
		return connect;
	}


	public void deleteTypesHeuresParModule(int idModule) {
		try {
			this.psDeleteTHM.setInt(1,idModule);
			this.psDeleteTHM.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}

	public void insertTypesHeuresParModule(int idModule, int idTypeHeure, int pn, int nbSemaines, int nbHeures) {
		try {
			this.psInsertTHM.setInt(1, idModule);
			this.psInsertTHM.setInt(2, idTypeHeure);
			this.psInsertTHM.setInt(3, pn);
			this.psInsertTHM.setInt(4, nbSemaines);
			this.psInsertTHM.setInt(5, nbHeures);

			this.psInsertTHM.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}

	public boolean typesHeuresDansModuleRentrees(int idModule) throws SQLException{

			this.psSelectTHM.setInt(1, idModule);
			ResultSet rs = this.psSelectTHM.executeQuery();

			int cpt=0;
			while( rs.next() ) {
				cpt++;
			}
			rs.close();

			return cpt>0;
	}

	public HashMap<Integer,HashMap<String,Integer>> getTypesHeuresParModule(int idModule) throws SQLException {

		HashMap<Integer,HashMap<String,Integer>> map = new HashMap<>();
 
		this.psSelectHM.setInt(1, idModule);
		ResultSet rs = this.psSelectTHM.executeQuery();

		while( rs.next() ) {
			HashMap<String,Integer> temp = new HashMap<>();
			int idTypeHeure = rs.getInt("id_type_heure");
			temp.put("pn",rs.getInt("pn"));
			temp.put("nb_semaines", rs.getInt("nb_semaines"));
			temp.put("nb_heures", rs.getInt("nb_heures"));
			map.put(idTypeHeure,temp);
		}
		rs.close();

		return map;
	}





	/*----------------------------------------*/
	/*              Intervenant               */
	/*----------------------------------------*/

	public boolean existsIntervenant( int idIntervenant ) throws SQLException {

		this.psSelectI.setInt(1, idIntervenant);
		ResultSet rs = this.psSelectI.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertIntervenant( Intervenant intervenant ) throws SQLException {

		if ( !this.existsIntervenant(intervenant.getIdIntervenant()) ) {

			this.psInsertI.setInt(1, intervenant.getIdIntervenant());
			this.psInsertI.setString(2, intervenant.getNom());
			this.psInsertI.setString(3, intervenant.getPrenom());
			this.psInsertI.setFloat(4, intervenant.getNbEqTD());
			this.psInsertI.setString(5, intervenant.getStatut().getNomStatut());

			this.psInsertI.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant="+intervenant.getIdIntervenant()+" deja existant");
		}
	}

	public void deleteIntervenant( Intervenant intervenant ) throws SQLException {
		if ( this.existsIntervenant(intervenant.getIdIntervenant()) ) {
			this.psDeleteI.setInt(1, intervenant.getIdIntervenant());
			this.psDeleteI.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant="+intervenant.getIdIntervenant()+" inexistant");
		}
	}

	public void updateIntervenant( Intervenant intervenant ) throws SQLException {

		if ( this.existsIntervenant(intervenant.getIdIntervenant()) ) {

			this.psUpdateI.setString(1, intervenant.getNom());
			this.psUpdateI.setString(2, intervenant.getPrenom());
			this.psUpdateI.setFloat(3, intervenant.getNbEqTD());
			this.psUpdateI.setString(4, intervenant.getStatut().getNomStatut());
			this.psUpdateI.setInt(5, intervenant.getIdIntervenant());

			this.psUpdateI.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant = "+intervenant.getIdIntervenant()+" inexistant");
		}
	}

	public static int getNbIntervenants()
	{
		int nbModules = -1;


		try {
			Class.forName("org.postgresql.Driver");
			// System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch (ClassNotFoundException e) { e.printStackTrace(); }

		try {
				Connection connect = getConnect();
			// System.out.println("CONNEXION A LA BADO: REUSSIE");

			try {
				PreparedStatement psGetNbIntervenant =  connect.prepareStatement("SELECT COUNT(*) FROM Intervenant;");
				ResultSet rs = psGetNbIntervenant.executeQuery();
				while ( rs.next() ) nbModules = rs.getInt(1);

			} catch ( SQLException e ) { e.printStackTrace(); }
		
			connect.close();
		} catch ( SQLException e ) { e.printStackTrace(); }

		return nbModules;
	}


	/*----------------------------------------*/
	/*                Heure                   */
	/*----------------------------------------*/

	public boolean existsHeure( int idHeure ) throws SQLException {

		this.psSelectH.setInt(1, idHeure);

		ResultSet rs = this.psSelectH.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertHeure( Heure heure ) {

		try {
			if ( !this.existsHeure(heure.getIdHeure()) ) {

				this.psInsertH.setInt(1, heure.getIdHeure());
				this.psInsertH.setInt(2, heure.getModule().getIdModule());
				this.psInsertH.setInt(3, heure.getTypeHeure().getIdTypeHeure());
				this.psInsertH.setInt(4, heure.getNbSemaines());
				this.psInsertH.setInt(5, heure.getNbGpNbH());
				this.psInsertH.setFloat(6, heure.getDuree());
				this.psInsertH.setString(7,heure.getCommentaire());

				this.psInsertH.executeUpdate();
			} else {
				System.out.println("Heure id_heure = "+heure.getIdHeure()+" deja existant");
			}
		} catch ( SQLException e ) { e.printStackTrace(); }

	}

	public void deleteHeure( Heure heure ) throws SQLException {

		if ( this.existsHeure(heure.getIdHeure()) ) {

			this.psDeleteH.setInt(1, heure.getIdHeure());

			this.psDeleteH.executeUpdate();
		} else {
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}

	public void updateHeure( Heure heure ) throws SQLException {

		if ( this.existsHeure(heure.getIdHeure()) ) {

			this.psUpdateH.setInt(1, heure.getModule().getIdModule());
			this.psUpdateH.setInt(2, heure.getTypeHeure().getIdTypeHeure());
			this.psUpdateH.setInt(3, heure.getNbSemaines());
			this.psUpdateH.setInt(4, heure.getNbGpNbH());
			this.psUpdateH.setFloat(5, heure.getDuree());
			this.psUpdateH.setString(6,heure.getCommentaire());
			this.psUpdateH.setInt(7,heure.getIdHeure());

			this.psUpdateH.executeUpdate();
		} else {
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}

	public static int getNbHeures() {
		int nbHeures = -1;

		try {
			Class.forName("org.postgresql.Driver");
			// System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch ( ClassNotFoundException e ) { e.printStackTrace(); }

		try {
			Connection connect = getConnect();

			PreparedStatement psGetNbHeures =  connect.prepareStatement("SELECT COUNT(*) FROM Heure;");

			ResultSet rs = psGetNbHeures.executeQuery();

			while ( rs.next() ) nbHeures = rs.getInt(1);

			connect.close();
			
			return nbHeures;
		} catch (SQLException e) { 
			e.printStackTrace(); 
		}

		return nbHeures;
	}



	/*----------------------------------------*/
	/*              Type_Heure                */
	/*----------------------------------------*/

	public boolean existsTypeHeure( int idTypeHeure ) throws SQLException {

		this.psSelectTH.setInt(1, idTypeHeure);
		ResultSet rs = this.psSelectTH.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertTypeHeure( TypeHeure typeHeure ) throws SQLException {

		if ( !this.existsTypeHeure(typeHeure.getIdTypeHeure()) ) {

			this.psInsertTH.setInt(1, typeHeure.getIdTypeHeure());
			this.psInsertTH.setString(2, typeHeure.getNomTypeHeure());
			this.psInsertTH.setFloat(3, typeHeure.getCoeff());

			this.psInsertTH.executeUpdate();
		} else {
			System.out.println("TypeHeure id_type_heure = "+typeHeure.getIdTypeHeure()+" deja existant");
		}
	}


	public void deleteTypeHeure(TypeHeure typeHeure) throws SQLException {

		if ( this.existsTypeHeure(typeHeure.getIdTypeHeure()) ) {

			this.psDeleteTH.setInt(1, typeHeure.getIdTypeHeure());

			this.psDeleteTH.executeUpdate();
		} else {
			System.out.println("TypeHeure id_type_heure = "+typeHeure.getIdTypeHeure()+" inexistant");
		}
	}


	public void updateTypeHeure(TypeHeure typeHeure) throws SQLException {

		if ( this.existsTypeHeure(typeHeure.getIdTypeHeure()) ) {

			this.psUpdateTH.setFloat(1, typeHeure.getCoeff());
			this.psUpdateTH.setInt(2, typeHeure.getIdTypeHeure());

			this.psUpdateTH.executeUpdate();
		} else {
			System.out.println("TypeHeure id_type_heure = "+typeHeure.getIdTypeHeure()+" inexistant");
		}
	}

	public static int getNbTypeHeures() {

		int nbTypeHeures = -1;


		try {
			Class.forName("org.postgresql.Driver");
			// System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch ( ClassNotFoundException e ) { e.printStackTrace(); }

		
		Connection connect = getConnect();
		try {
			Statement sGetNbTypeHeures =  connect.createStatement();
			ResultSet rs = sGetNbTypeHeures.executeQuery("SELECT COUNT(*) FROM Type_Heure");
			while ( rs.next() ) nbTypeHeures = rs.getInt(1);
		} catch ( SQLException e ) { e.printStackTrace(); }
		try{
			connect.close();
		} catch ( SQLException e ) { e.printStackTrace(); }

		return nbTypeHeures;
	}




	/*----------------------------------------*/
	/*               Module                   */
	/*----------------------------------------*/

	public boolean existsModule( int idModule ) throws SQLException {

		this.psSelectM.setInt(1, idModule);
		ResultSet rs = this.psSelectM.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public boolean insertModule( Module module ) throws SQLException {

		if ( !this.existsModule(module.getIdModule()) ) {

			this.psInsertM.setInt    ( 1,  module.getIdModule()     );
			this.psInsertM.setString ( 2,  module.getTypeModule()   );
			this.psInsertM.setString ( 3,  module.getSemestre()     );
			this.psInsertM.setString ( 4,  module.getLibelle()      );
			this.psInsertM.setString ( 5,  module.getLibelleCourt() );
			this.psInsertM.setString ( 6,  module.getCode()         );
			this.psInsertM.setInt    ( 7,  module.getNbEtudiants()  );
			this.psInsertM.setInt    ( 8,  module.getNbGpTD()       );
			this.psInsertM.setInt    ( 9,  module.getNbGpTP()       );
			this.psInsertM.setInt    ( 10, module.getNbSemaines()   );
			this.psInsertM.setInt    ( 11, module.getNbHeures()     );
			this.psInsertM.setBoolean( 12, module.isValide()         );

			this.psInsertM.execute();

			return true;
		} else {
			System.out.println("Module id_module = "+module.getIdModule()+" deja existant");
			return false;
		}
	}


	public void deleteModule(Module module) throws SQLException {

		if ( this.existsModule(module.getIdModule()) ) {

			this.psDeleteM.setInt(1, module.getIdModule());

			this.psDeleteM.executeUpdate();
		} else {
			System.out.println("Module id_module = "+module.getIdModule()+" inexistant");
		}
	}


	public void updateModule( Module module ) throws SQLException {

		if ( this.existsModule(module.getIdModule()) ) {

			this.psUpdateM.setString(1, module.getTypeModule());
			this.psUpdateM.setString(2,module.getSemestre());
			this.psUpdateM.setString(3, module.getLibelle());
			this.psUpdateM.setString(4, module.getLibelleCourt());
			this.psUpdateM.setString(5, module.getCode());
			this.psUpdateM.setInt(6, module.getNbEtudiants());
			this.psUpdateM.setInt(7, module.getNbGpTD());
			this.psUpdateM.setInt(8, module.getNbGpTP());
			this.psUpdateM.setInt(9, module.getNbSemaines());
			this.psUpdateM.setInt(10, module.getNbHeures());
			this.psUpdateM.setBoolean(11, module.isValide());

			this.psUpdateM.setInt(12, module.getIdModule());

			this.psUpdateM.executeUpdate();
		} else {
			System.out.println("Module id_module = "+module.getIdModule()+" inexistant");
		}
	}

	public static int getNbModules()
	{
		int nbModules = -1;

		try {
			Class.forName("org.postgresql.Driver");
			// System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch (ClassNotFoundException e) { e.printStackTrace(); }

		try {
			Connection connect = getConnect();

			try {
				PreparedStatement  psGetNbModules =  connect.prepareStatement("SELECT COUNT(*) FROM Module;");
				ResultSet rs = psGetNbModules.executeQuery();
				while ( rs.next() ) nbModules = rs.getInt(1);
			} catch (SQLException e) { e.printStackTrace(); }
		
			connect.close();
		} catch (SQLException e) { e.printStackTrace(); }

		return nbModules;
	}


	/*----------------------------------------*/
	/*               Statut                   */
	/*----------------------------------------*/

	public boolean existsStatut( String nomStatut ) throws SQLException {

		this.psSelectS.setString(1, nomStatut);
		ResultSet rs = this.psSelectS.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}

	public void insertStatut( Statut statut ) throws SQLException {

		if ( !this.existsStatut(statut.getNomStatut()) ) {

			this.psInsertS.setString(1, statut.getNomStatut());
			this.psInsertS.setInt(2, statut.getNbHeureService());
			this.psInsertS.setInt(3, statut.getNbHeuresMax());
			this.psInsertS.setFloat(4, statut.getCoeffTP());

			this.psInsertS.executeUpdate();
		} else {
			System.out.println("statut id_statut = "+statut.getNomStatut()+" deja existant");
		}
	}


	public void deleteStatut( Statut statut ) throws SQLException {

		if ( this.existsStatut(statut.getNomStatut()) ) {

			this.psDeleteS.setString(1, statut.getNomStatut());

			this.psDeleteS.executeUpdate();
		} else {
			System.out.println("Statut nom_statut = "+statut.getNomStatut()+" inexistant");
		}
	}


	public void updateStatut( Statut statut ) throws SQLException {

		if ( this.existsStatut(statut.getNomStatut()) ) {

			this.psUpdateS.setInt(1, statut.getNbHeureService());
			this.psUpdateS.setInt(2, statut.getNbHeuresMax());
			this.psUpdateS.setFloat(3, statut.getCoeffTP());
			this.psUpdateS.setString(4, statut.getNomStatut());

			this.psUpdateS.executeUpdate();
		} else {
			System.out.println("Statut nom_statut = "+statut.getNomStatut()+" inexistant");
		}
	}


	/*----------------------------------------*/
	/*           Intervenant_Statut           */
	/*----------------------------------------*/


	public boolean existsIntervenantModule( int idIntervenant, int idModule ) throws SQLException {

		this.psSelectIM.setInt(1, idIntervenant);
		this.psSelectIM.setInt(2, idModule);

		ResultSet rs = this.psSelectIM.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertIntervenantModule( List<Intervenant> intervenants, Module module ) throws SQLException {
		for ( Intervenant intervenant : intervenants )
			this.insertIntervenantModule( intervenant, module );
	}

	public void insertIntervenantModule( Intervenant intervenant, Module module ) throws SQLException {

		if ( !this.existsIntervenantModule(intervenant.getIdIntervenant(), module.getIdModule()) ) {

			this.psInsertIM.setInt(1, intervenant.getIdIntervenant());
			this.psInsertIM.setInt(2, module.getIdModule());

			this.psInsertIM.executeUpdate();
		} else {
			System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" deja existant");
		}
	}


	public void deleteIntervenantModule( Intervenant intervenant, Module module ) throws SQLException {

		if ( this.existsIntervenantModule(intervenant.getIdIntervenant(), module.getIdModule()) ) {

			this.psDeleteIM.setInt(1, intervenant.getIdIntervenant());
			this.psDeleteIM.setInt(2, module.getIdModule());

			this.psDeleteIM.executeUpdate();
		} else {
			System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" inexistant");
		}
	}


	public void updateIntervenantModule( Intervenant intervenant, Module module ) throws SQLException {

		if ( this.existsIntervenantModule(intervenant.getIdIntervenant(), module.getIdModule()) ) {

			this.psUpdateIM.setInt(1, intervenant.getIdIntervenant());
			this.psUpdateIM.setInt(2, module.getIdModule());
			this.psUpdateIM.setInt(1, intervenant.getIdIntervenant());
			this.psUpdateIM.setInt(2, module.getIdModule());

			this.psUpdateIM.executeUpdate();
		} else {
			System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" inexistant");
		}
	}



	/*----------------------------------------*/
	/*           Intervenant_Heure            */
	/*----------------------------------------*/

	public boolean existsIntervenantHeure( int idIntervenant, int idHeure ) throws SQLException {

		this.psSelectIH.setInt(1, idIntervenant);
		this.psSelectIH.setInt(2, idHeure);

		ResultSet rs = this.psSelectIH.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertIntervenantHeure( List<Intervenant> intervenants, Heure heure ) throws SQLException {
		for ( Intervenant intervenant : intervenants )
			this.insertIntervenantHeure( intervenant, heure );
	}

	public void insertIntervenantHeure( Intervenant intervenant, Heure heure ) throws SQLException {

		if ( !this.existsIntervenantHeure(intervenant.getIdIntervenant(), heure.getIdHeure()) ) {

			this.psInsertIH.setInt(1, intervenant.getIdIntervenant());
			this.psInsertIH.setInt(2, heure.getIdHeure());

			this.psInsertIH.executeUpdate();
		} else {
			System.out.println("IntervenantHeure id_intervenant = "+intervenant.getIdIntervenant()+", id_heure = "+heure.getIdHeure()+" deja existant");
		}
	}

	public void deleteIntervenantHeure( Intervenant intervenant, Heure heure ) throws SQLException {

		if ( this.existsIntervenantHeure(intervenant.getIdIntervenant(), heure.getIdHeure()) ) {

			this.psDeleteIH.setInt(1, intervenant.getIdIntervenant());
			this.psDeleteIH.setInt(2, heure.getIdHeure());

			this.psDeleteIH.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant = "+intervenant.getIdIntervenant()+", id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}

	public void updateIntervenantHeure( Intervenant intervenant, Heure heure ) throws SQLException {

		if ( this.existsIntervenantHeure(intervenant.getIdIntervenant(), heure.getIdHeure()) ) {

			this.psUpdateIH.setInt(1, intervenant.getIdIntervenant());
			this.psUpdateIH.setInt(2, heure.getIdHeure());
			this.psUpdateIH.setInt(1, intervenant.getIdIntervenant());
			this.psUpdateIH.setInt(2, heure.getIdHeure());

			this.psUpdateIH.executeUpdate();
		} else {
			System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}

	
	/*----------------------------------------*/
	/*             Heure_Module               */
	/*----------------------------------------*/

	public boolean existsHeureModule( int idHeure, int idModule ) throws SQLException {

		this.psSelectHM.setInt(1, idHeure);
		this.psSelectHM.setInt(2,idModule);

		ResultSet rs = this.psSelectHM.executeQuery();

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}

	public void insertHeureModule( List<Heure> heures, Module module ) throws SQLException {
		for ( Heure heure : heures )
			this.insertHeureModule( heure, module );
	}

	public void insertHeureModule( Heure heure, Module module ) throws SQLException {

		if ( !this.existsHeureModule(heure.getIdHeure(), module.getIdModule()) ) {

			this.psInsertHM.setInt(1, heure.getIdHeure());
			this.psInsertHM.setInt(2, module.getIdModule());

			this.psInsertHM.executeUpdate();
		} else {
			System.out.println("HeureModule id_heure = "+heure.getIdHeure()+", id_module = "+module.getIdModule()+" deja existant");
		}
	}


	public void deleteHeureModule( Heure heure, Module module ) throws SQLException {

		if ( this.existsHeureModule(heure.getIdHeure(), module.getIdModule()) ) {

			this.psDeleteHM.setInt(1, heure.getIdHeure());
			this.psDeleteHM.setInt(2, module.getIdModule());

			this.psDeleteHM.executeUpdate();
		} else {
			System.out.println("HeureModule id_heure = "+heure.getIdHeure()+", id_module = "+module.getIdModule()+" inexistant");
		}
	}


	public void updateHeureModule( Heure heure, Module module ) throws SQLException {

		if ( this.existsHeureModule(heure.getIdHeure(), module.getIdModule()) ) {

			this.psUpdateHM.setInt(1, heure.getIdHeure());
			this.psUpdateHM.setInt(2, module.getIdModule());
			this.psUpdateHM.setInt(1, heure.getIdHeure());
			this.psUpdateHM.setInt(2, module.getIdModule());

			this.psUpdateHM.executeUpdate();
		} else {
			System.out.println("HeureModule id_heure = "+heure.getIdHeure()+", id_module = "+module.getIdModule()+" inexistant");
		}
	}



	/***********
	 * GETTERS *
	 ***********/

	private ArrayList<Intervenant> getIntervenants( String req ) throws SQLException {

		Statement selectI = connec.createStatement();
		ArrayList<Intervenant> listeI = new ArrayList<Intervenant>();

		ResultSet rs = selectI.executeQuery(req);

		while( rs.next() ) {
			Intervenant i = Intervenant.creerIntervenant( rs.getString("prenom"),
			                                              rs.getString("nom"),
			                                              new Statut( rs.getString("nom_statut"),
			                                                          rs.getInt("nb_heures_service"),
			                                                          rs.getInt("nb_heures_maxi"),
			                                                          rs.getInt("coeff_tp") ),
			                                              rs.getFloat("nb_equivalent_td") );
			listeI.add(i);
		}
		rs.close();

		return listeI;
	}

	public ArrayList<Intervenant> getIntervenants() throws SQLException {
		return getIntervenants("SELECT * FROM Intervenant i JOIN Statut s ON s.nom_statut = i.nom_statut;");
	}


	private ArrayList<Statut> getStatuts( String req ) throws SQLException {

		Statement selectS = connec.createStatement();
		ArrayList<Statut> listeS  = new ArrayList<Statut>();

		ResultSet rs = selectS.executeQuery(req);

		while( rs.next() ) {
			Statut s = new Statut( rs.getString("nom_statut"),
			                       rs.getInt("nb_heures_service"),
			                       rs.getInt("nb_heures_maxi"),
			                       rs.getFloat("coeff_tp") );
			listeS.add(s);
		}
		rs.close();

		return listeS;
	}

	public ArrayList<Statut> getStatuts() throws SQLException {
		return getStatuts("SELECT * FROM Statut;");
	}


	private ArrayList<TypeHeure> getTypesHeures( String req ) throws SQLException {

		Statement selectTH = connec.createStatement();
		ArrayList<TypeHeure> listeTH  = new ArrayList<TypeHeure>();

		ResultSet rs = selectTH.executeQuery(req);

		while( rs.next() ) {
			TypeHeure th = TypeHeure.initTypeHeure( rs.getInt("id_type_heure"),
			                                        rs.getString("nom_type_heure"),
			                                        rs.getFloat("coeff") );
			listeTH.add(th);
		}
		rs.close();

		return listeTH;
	}

	public ArrayList<TypeHeure> getTypesHeures() throws SQLException {
		return getTypesHeures("SELECT * FROM Type_Heure;");
	}


	private ArrayList<Heure> getHeures( String req ) throws SQLException {

		Statement selectH = connec.createStatement();
		ArrayList<Heure> listeH = new ArrayList<Heure>();

		ResultSet rs = selectH.executeQuery(req);

		while( rs.next() ) {
			Heure h = Heure.creerHeure( Module.initModule( rs.getInt("id_module"),
			                                               rs.getString("type_module"),
			                                               rs.getString("semestre"),
			                                               rs.getString("libelle"),
			                                               rs.getString("libelle_court"),
			                                               rs.getString("code"),
			                                               rs.getInt("nb_etudiants"),
			                                               rs.getInt("nb_gp_td"), 
			                                               rs.getInt("nb_gp_tp"),
			                                               rs.getInt("nb_semaines"),
			                                               rs.getInt("nb_heures"),
			                                               rs.getBoolean("valide") ),
			                            TypeHeure.initTypeHeure( rs.getInt("id_type_heure"),
			                                                     rs.getString("nom_type_heure"),
			                                                     rs.getFloat("coeff") ),
			                            rs.getInt("nb_semaines"),
			                            rs.getInt("nb_gp_nb_h"),
			                            rs.getInt("duree"),
			                            rs.getString("commentaire") );
			listeH.add(h);
		}
		rs.close();

		return listeH;
	}

	public ArrayList<Heure> getHeures() throws SQLException {
		return getHeures("SELECT * FROM Heure h JOIN Module m ON m.id_module = h.id_module JOIN Type_Heure t ON t.id_type_heure = h.id_type_heure;");
	}



	private ArrayList<Module> getModules( String req ) throws SQLException {

		Statement selectNP = connec.createStatement();
		ArrayList<Module> listeI = new ArrayList<Module>();

		ResultSet rs = selectNP.executeQuery(req);

		while( rs.next() ) {
			Module m = Module.creerModule( rs.getString ("type_module"   ),
			                               rs.getString ("semestre"      ),
			                               rs.getString ("libelle"       ),
			                               rs.getString ("libelle_court" ),
			                               rs.getString ("code"          ),
			                               rs.getInt    ("nb_etudiants"  ),
			                               rs.getInt    ("nb_gp_td"      ),
			                               rs.getInt    ("nb_gp_tp"      ),
			                               rs.getInt    ("nb_semaines"   ),
			                               rs.getInt    ("nb_heures"     ),
			                               rs.getBoolean("valide") );
			listeI.add(m);
		}
		rs.close();
		return listeI;
	}

	public ArrayList<Module> getModules() throws SQLException {
		return this.getModules("SELECT * FROM Module;");
	}


	private Integer[][] getHeuresParModule( String req ) throws SQLException {
		Integer idHeure;
		Integer idModule;
		int size;
		int cpt;

		Statement selectHM = connec.createStatement();
		ResultSet s = selectHM.executeQuery("SELECT * FROM Heure_Module;");

		size=0;
		while ( s.next() ) size++;

		Integer[][] map = new Integer[size][2];

		ResultSet rs = selectHM.executeQuery(req);

		cpt=0;
		while( rs.next() ) {
			idHeure  = rs.getInt(1);
			idModule = rs.getInt(2);
			
			map[cpt][0] = idHeure;
			map[cpt][1] = idModule;

			cpt++;
		}
		rs.close();

		return map;
	}


	public Integer[][] getHeuresParModule() throws SQLException {
		return this.getHeuresParModule("SELECT * FROM Heure_Module;");
	}


	private Integer[][] getIntervenantsParHeure( String req ) throws SQLException {
		Integer idIntervenant;
		Integer idHeure;
		int size;
		int cpt;

		Statement selectIH = connec.createStatement();
		ResultSet s = selectIH.executeQuery("SELECT * FROM Intervenant_Heure;");

		size=0;
		while ( s.next() ) size++;

		Integer[][] map = new Integer[size][2];

		ResultSet rs = selectIH.executeQuery(req);

		cpt=0;
		while( rs.next() ) {
			idIntervenant = rs.getInt(1);
			idHeure = rs.getInt(2);
			
			map[cpt][0] = idIntervenant;
			map[cpt][1] = idHeure;

			cpt++;
		}
		rs.close();

		return map;
	}


	public Integer[][] getIntervenantsParHeure() throws SQLException {
		return this.getIntervenantsParHeure("SELECT * FROM Intervenant_Heure;");
	}


	private Integer[][] getIntervenantsParModule(String req) throws SQLException {
		Integer idIntervenant;
		Integer idModule;
		int size;
		int cpt;

		Statement selectHM = connec.createStatement();
		ResultSet s = selectHM.executeQuery("SELECT * FROM Intervenant_Module;");

		size=0;
		while ( s.next() ) size++;

		Integer[][] map = new Integer[size][2];

		ResultSet rs = selectHM.executeQuery(req);

		cpt=0;
		while( rs.next() ) {
			idIntervenant = rs.getInt(1);
			idModule = rs.getInt(2);
			
			map[cpt][0] = idIntervenant;
			map[cpt][1] = idModule;

			cpt++;
		}
		rs.close();

		return map;
	}


	public Integer[][] getIntervenantsParModule() throws SQLException {
		return this.getIntervenantsParModule("SELECT * FROM Intervenant_Module;");
	}




	/************
	 *   INIT   *
	 ************/

	public ArrayList<Heure> initHeures() throws SQLException {

		Statement selectH = connec.createStatement();
		ArrayList<Heure> listeH = new ArrayList<Heure>();

		String req = "SELECT * FROM Heure h JOIN Module m ON m.id_module = h.id_module JOIN Type_Heure t ON t.id_type_heure = h.id_type_heure;";
		ResultSet rs = selectH.executeQuery(req);

		while( rs.next() ) {
			Heure h = Heure.initHeure( rs.getInt("id_heure"),
			                           Module.initModule( rs.getInt("id_module"),
			                                              rs.getString("type_module"),
			                                              rs.getString("semestre"),
			                                              rs.getString("libelle"),
			                                              rs.getString("libelle_court"),
			                                              rs.getString("code"),
			                                              rs.getInt("nb_etudiants"),
			                                              rs.getInt("nb_gp_td"), 
			                                              rs.getInt("nb_gp_tp"),
			                                              rs.getInt("nb_semaines"),
			                                              rs.getInt("nb_heures"),
			                                              rs.getBoolean("valide")),
			                           TypeHeure.initTypeHeure( rs.getInt("id_type_heure"),
			                                                    rs.getString("nom_type_heure"),
			                                                    rs.getFloat("coeff") ),
			                           rs.getInt("nb_semaines"),
			                           rs.getInt("nb_gp_nb_h"),
			                           rs.getInt("duree"),
			                           rs.getString("commentaire") );
			listeH.add(h);
		}
		rs.close();

		return listeH;
	}

	public ArrayList<Intervenant> initIntervenants() throws SQLException {

		Statement selectI = connec.createStatement();
		ArrayList<Intervenant> listeI = new ArrayList<Intervenant>();

		String req = "SELECT * FROM Intervenant i JOIN Statut s ON s.nom_statut = i.nom_statut;";
		ResultSet rs = selectI.executeQuery(req);

		while( rs.next() ) {
			Intervenant i = Intervenant.initIntervenant( rs.getInt("id_intervenant"),
			                                             rs.getString("prenom"),
			                                             rs.getString("nom"),
			                                             new Statut( rs.getString("nom_statut"),
			                                                         rs.getInt("nb_heures_service"),
			                                                         rs.getInt("nb_heures_maxi"),
			                                                         rs.getInt("coeff_tp") ),
			                                             rs.getFloat("nb_equivalent_td") );
			listeI.add(i);
		}
		rs.close();

		return listeI;
	}

	public ArrayList<Module> initModules() throws SQLException {

		Statement selectNP = connec.createStatement();
		ArrayList<Module> listeI = new ArrayList<Module>();

		String req = "SELECT * FROM Module";
		ResultSet rs = selectNP.executeQuery(req);

		while(rs.next()){
			Module m = Module.initModule( rs.getInt("id_module"),
			                              rs.getString ("type_module"   ),
			                              rs.getString ("semestre"      ),
			                              rs.getString ("libelle"       ),
			                              rs.getString ("libelle_court" ),
			                              rs.getString ("code"          ),
			                              rs.getInt    ("nb_etudiants"  ),
			                              rs.getInt    ("nb_gp_td"      ),
			                              rs.getInt    ("nb_gp_tp"      ),
			                              rs.getInt    ("nb_semaines"   ),
			                              rs.getInt    ("nb_heures"     ),
			                              rs.getBoolean("valide") );
			listeI.add(m);
		}
		rs.close();

		return listeI;
	}


}

