package back.db;

import java.sql.*;
import java.util.ArrayList;

import back.Heure;
import back.Intervenant;
import back.Statut;
import back.TypeHeure;

public class Requetes {

	private DB db;
	private Connection connec;

	private PreparedStatement psSelectI;
	private PreparedStatement psInsertI;
	private PreparedStatement psDeleteI;
	private PreparedStatement psUpdateI;
	
	private PreparedStatement psSelectH;
	private PreparedStatement psInsertH;
	private PreparedStatement psDeleteH;
	private PreparedStatement psUpdateH;

	private PreparedStatement psSelect;
	private PreparedStatement psInsert;
	private PreparedStatement psDelete;
	private PreparedStatement psUpdate;

	public Requetes() {
		this.db = DB.getInstance();
		this.connec = this.db.getConnection();

		try{
			this.psSelectI = this.connec.prepareStatement("SELECT * FROM Intervenant WHERE id_intervenant=?;");
			this.psInsertI = this.connec.prepareStatement("INSERT INTO Intervenant VALUES(?,?,?,?,?);");
			this.psDeleteI = this.connec.prepareStatement("DELETE * FROM Intervenant WHERE id_intervenant=?;");
			this.psUpdateI = this.connec.prepareStatement("UPDATE Intervenant SET nom=?, prenom=?, nb_equivalent_td=?, id_statut=? WHERE id_intervenant=?;");
			
			this.psSelectH = this.connec.prepareStatement("SELECT * FROM Heure WHERE id_heure=?;");
			this.psInsertH = this.connec.prepareStatement("INSERT INTO Heure VALUES(?,?,?,?,?);");
			this.psDeleteH = this.connec.prepareStatement("DELETE * FROM Heure WHERE id_heure=?;");
			this.psUpdateH = this.connec.prepareStatement("UPDATE Heure SET id_module=?, id_intervenant=?, id_type_heure=?, nb_heures=? WHERE id_heure=?;");


		} catch(SQLException e) { e.printStackTrace(); }


	}

	public void insertIntervenant( Intervenant intervenant ) throws SQLException {
		String request = "SELECT * FROM Intervenant WHERE id_intervenant="+intervenant.getIdIntervenant()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig <= 0 ) {
			this.psInsertI.setInt(1, intervenant.getIdIntervenant());
			this.psInsertI.setString(2, intervenant.getNom());
			this.psInsertI.setString(3, intervenant.getPrenom());
			this.psInsertI.setInt(4, intervenant.getNbEqTD());
			this.psInsertI.setString(5, intervenant.getStatut().getNomStatut());
			this.psInsertI.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant="+intervenant.getIdIntervenant()+" existant");
		}
	}

	public void deleteIntervenant(Intervenant intervenant) throws SQLException {
		String request = "SELECT * FROM Intervenant WHERE id_intervenant="+intervenant.getIdIntervenant()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig > 0 ) {
			this.psDeleteI.setInt(1, intervenant.getIdIntervenant());
			this.psDeleteI.executeUpdate();
		}
		else{
			System.out.println("Intervenant id_intervenant="+intervenant.getIdIntervenant()+" inexistant");
		}
	}

	public void updateIntervenant(Intervenant intervenant) throws SQLException {
		Statement stmt = this.connec.createStatement();

		String request = "SELECT * FROM Intervenant WHERE id_intervenant="+intervenant.getIdIntervenant()+";";

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while (rs.next()) cptLig ++;

		if ( cptLig > 0 ) {
			this.psUpdateI.setString(1, intervenant.getNom());
			this.psUpdateI.setString(2, intervenant.getPrenom());
			this.psUpdateI.setInt(3, intervenant.getNbEqTD());
			this.psUpdateI.setString(4, intervenant.getStatut().getNomStatut());
			this.psUpdateI.setInt(5, intervenant.getIdIntervenant());
			this.psUpdateI.executeUpdate();
		}
		else
			System.out.println("Intervenant id_intervenant = "+intervenant.getIdIntervenant()+" inexistant");
	}


	public void insertHeure(Heure heure) throws SQLException {
		
		String request = "SELECT * FROM Heure WHERE id_heure="+heure.getIdHeure()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig <= 0 ) {
			this.psInsertH.setInt(1, heure.getIdHeure());
			this.psInsertH.setInt(2, heure.getModule().getIdModule());
			this.psInsertH.setInt(3, heure.getIntervenant().getIdIntervenant());
			this.psInsertH.setInt(4, heure.getTypeHeure().getIdTypeHeure());
			this.psInsertH.setInt(5, heure.getNbHeures());
			this.psInsertH.executeUpdate();
		} else {
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}

	public void deleteHeure(Heure heure) throws SQLException {
		String request = "SELECT * FROM heure WHERE id_heure="+heure.getIdHeure()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig > 0 ) {
			this.psDeleteH.setInt(1, heure.getIdHeure());
			this.psDeleteH.executeUpdate();
		}
		else{
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}

	public void updateHeure(Heure heure) throws SQLException {
		Statement stmt = this.connec.createStatement();

		String request = "SELECT * FROM Heure WHERE id_heure="+heure.getIdHeure()+";";

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while (rs.next()) cptLig ++;

		if ( cptLig > 0 ) {
			this.psUpdateH.setInt(1, heure.getModule().getIdModule());
			this.psUpdateH.setInt(2, heure.getIntervenant().getIdIntervenant());
			this.psUpdateH.setInt(3, heure.getTypeHeure().getIdTypeHeure());
			this.psUpdateH.setInt(4, heure.getNbHeures());
			this.psUpdateH.executeUpdate();
		}
		else
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" inexistant");
	}




	private ArrayList<Intervenant> getIntervenants(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Intervenant> listeI  =new ArrayList<Intervenant>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Intervenant p = new Intervenant(rs.getInt("id_intervenant"),rs.getString("nom"), rs.getString("prenom"),
											new Statut(rs.getString("id_statut"), rs.getInt("nbHeureMini"), rs.getInt("nbHeureMaxi"), rs.getInt("coeffTP")), rs.getInt("nb_equivalent_td"));
			listeI.add(p);
		  }
		rs.close(); 
		return listeI;
	}
	public ArrayList<Intervenant> getIntervenants() throws SQLException {
		return getIntervenants("SELECT * FROM Intervenant i JOIN Statut s ON s.id_statut = i.id_statut");
	}
	
	
	private ArrayList<Statut> getStatut(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Statut> listeI  = new ArrayList<Statut>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Statut p = new Statut(rs.getString("id_statut"), rs.getInt("nbHeureMini"), 
			                      rs.getInt("nbHeureMaxi"), rs.getInt("coeffTP"));
			listeI.add(p);
		  }
		rs.close(); 
		return listeI;
	}
	public ArrayList<Statut> getStatut() throws SQLException {
		return getStatut("SELECT * FROM Statut");
	}


	private ArrayList<TypeHeure> getTypeHeure(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<TypeHeure> listeI  = new ArrayList<TypeHeure>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			TypeHeure p = new TypeHeure(rs.getString("id_type_heure"), rs.getFloat("coeff"));
			listeI.add(p);
		  }
		rs.close(); 
		return listeI;
	}
	public ArrayList<TypeHeure> getTypeHeure() throws SQLException {
		return getTypeHeure("SELECT * FROM TypeHeure");
	}



	private ArrayList<Heure> getHeures(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Heure> listeI = new ArrayList<Heure>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Heure p = new Heure(rs.getInt("id_heure"),
			                    new Module(rs.getString("type_module"), rs.getString("nb_s"), rs.getString("libele"), rs.getString("libele_court"), 
								           rs.getString("code"), rs.getInt("nb_etudiant"), rs.getInt("nb_gp_td"), rs.getInt("nb_gp_tp"), rs.getInt("nb_semaine")),
			                    new Intervenant(rs.getInt("id_intervenant"),rs.getString("nom"), rs.getString("prenom"), 
								                new Statut(rs.getString("id_statut"), rs.getInt("nbHeureMini"), rs.getInt("nbHeureMaxi"), rs.getInt("coeffTP")), rs.getInt("nb_equivalent_td")),
								new TypeHeure(rs.getString("id_type_heure"), rs.getFloat("coeff")), 
								rs.getInt("nbHeures"));
			listeI.add(p);
		  }

		
		rs.close(); 
		return listeI;
	}
	public ArrayList<Heure> getHeures() throws SQLException {
		return getHeures("SELECT * FROM Heure");
	}



	private ArrayList<Module> getModules(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Module> listeI = new ArrayList<Module>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Module p = new Module(rs.getInt("id_module"      ),
				                  rs.getString("type_module" ),
			                      rs.getString("nb_s"        ),
								  rs.getString("libele"      ),
								  rs.getString("libele_court"),
								  rs.getString("code"        ),
								  rs.getInt("nb_etudiant"    ),
								  rs.getInt("nb_gp_td"       ),
								  rs.getInt("nb_gp_tp"       ),
								  rs.getInt("nb_semaine"     ), 
								  rs.getInt("nb_heures"     ));
			listeI.add(p);

			
		  }

		  
		rs.close(); 
		return listeI;
	}
	public ArrayList<Module> getModules() throws SQLException {
		return getModules("SELECT * FROM Module");
	}




}
