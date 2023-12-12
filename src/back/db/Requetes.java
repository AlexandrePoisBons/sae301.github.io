package back.db;

import java.sql.*;
import java.util.ArrayList;

import back.Heure;
import back.Intervenant;
import back.Module;
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
	private PreparedStatement psInsertH; // commmentaire a rajouter
	private PreparedStatement psDeleteH;
	private PreparedStatement psUpdateH;

	private PreparedStatement psSelectTH;
	private PreparedStatement psInsertTH;
	private PreparedStatement psDeleteTH;
	private PreparedStatement psUpdateTH;

	private PreparedStatement psSelectM;
	private PreparedStatement psInsertM;
	private PreparedStatement psDeleteM;
	private PreparedStatement psUpdateM;

	private PreparedStatement psSelectS;
	private PreparedStatement psInsertS;
	private PreparedStatement psDeleteS;
	private PreparedStatement psUpdateS;

	private PreparedStatement psSelectIM;
	private PreparedStatement psInsertIM;
	private PreparedStatement psDeleteIM;
	private PreparedStatement psUpdateIM;

	private PreparedStatement psSelectIH;
	private PreparedStatement psInsertIH;
	private PreparedStatement psDeleteIH;
	private PreparedStatement psUpdateIH;

	public Requetes() {
		this.db = DB.getInstance();
		this.connec = this.db.getConnection();

		try{
			this.psSelectI = this.connec.prepareStatement("SELECT * FROM Intervenant WHERE id_intervenant=?;");
			this.psInsertI = this.connec.prepareStatement("INSERT INTO Intervenant VALUES(?,?,?,?,?);");
			this.psDeleteI = this.connec.prepareStatement("DELETE * FROM Intervenant WHERE id_intervenant=?;");
			this.psUpdateI = this.connec.prepareStatement("UPDATE Intervenant SET nom=?, prenom=?, nb_equivalent_td=?, id_statut=? WHERE id_intervenant=?;");

			this.psSelectH = this.connec.prepareStatement("SELECT * FROM Heure WHERE id_heure=?;");
			this.psInsertH = this.connec.prepareStatement("INSERT INTO Heure VALUES(?,?,?,?);");
			this.psDeleteH = this.connec.prepareStatement("DELETE * FROM Heure WHERE id_heure=?;");
			this.psUpdateH = this.connec.prepareStatement("UPDATE Heure SET id_module=?, id_type_heure=?, duree=? WHERE id_heure=?;");

			this.psSelectTH = this.connec.prepareStatement("SELECT * FROM Type_Heure WHERE id_type_heure=?;");
			this.psInsertTH = this.connec.prepareStatement("INSERT INTO Type_Heure VALUES(?,?);");
			this.psDeleteTH = this.connec.prepareStatement("DELETE * FROM Type_Heure WHERE id_type_heure=?;");
			this.psUpdateTH = this.connec.prepareStatement("UPDATE Type_Heure SET coeff=? WHERE id_type_heure=?;");

			this.psSelectM = this.connec.prepareStatement("SELECT * FROM Module WHERE id_module=?;");
			this.psInsertM = this.connec.prepareStatement("INSERT INTO Module VALUES(?,?,?,?,?,?,?,?,?,?);");
			this.psDeleteM = this.connec.prepareStatement("DELETE * FROM Module WHERE id_module=?;");
			this.psUpdateM = this.connec.prepareStatement("UPDATE Module SET type_module=?, libelle=?, libelle_court=?, code=?, nb_etudiants=?, nb_gp_td=?, nb_gp_tp=?, nb_semaines=?, nb_heures=?;");
			
			this.psSelectS = this.connec.prepareStatement("SELECT * FROM Statut WHERE nom_statut=?;");
			this.psInsertS = this.connec.prepareStatement("INSERT INTO Statut VALUES(?,?,?,?);");
			this.psDeleteS = this.connec.prepareStatement("DELETE * FROM Statut WHERE nom_Statut=?;");
			this.psUpdateS = this.connec.prepareStatement("UPDATE Statut SET nb_heure_mini=?, nb_heure_maxi=?, coeff_tp=? WHERE nom_statut=?;");

			this.psSelectIM = this.connec.prepareStatement("SELECT * FROM Intervenant_Module WHERE id_intervenant=? AND id_module=?;");
			this.psInsertIM = this.connec.prepareStatement("INSERT INTO Intervenant_Module VALUES(?,?);");
			this.psDeleteIM = this.connec.prepareStatement("DELETE * FROM Intervenant_Module WHERE id_intervenant=?, id_module=?;");
			this.psUpdateIM = this.connec.prepareStatement("UPDATE Intervenant_Module SET id_intervenant=?, id_module=? WHERE id_intervenant=? AND id_module=?;");

			this.psSelectIH = this.connec.prepareStatement("SELECT * FROM Intervenant_Heure WHERE id_intervenant=? AND id_heure=?;");
			this.psInsertIH = this.connec.prepareStatement("INSERT INTO Intervenant_Heure VALUES(?,?);");
			this.psDeleteIH = this.connec.prepareStatement("DELETE * FROM Intervenant_Heure WHERE id_intervenant=?, id_heure=?;");
			this.psUpdateIH = this.connec.prepareStatement("UPDATE Intervenant_Heure SET id_intervenant=?, id_heure=? WHERE id_intervenant=? AND id_heure=?;");

		} catch( SQLException e ) { e.printStackTrace(); }

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
			this.psInsertI.setFloat(4, intervenant.getNbEqTD());
			this.psInsertI.setString(5, intervenant.getStatut().getNomStatut());
			this.psInsertI.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant="+intervenant.getIdIntervenant()+" deja existant");
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
		} else{
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
			this.psUpdateI.setFloat(3, intervenant.getNbEqTD());
			this.psUpdateI.setString(4, intervenant.getStatut().getNomStatut());
			this.psUpdateI.setInt(5, intervenant.getIdIntervenant());
			this.psUpdateI.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant = "+intervenant.getIdIntervenant()+" inexistant");
		}
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
			this.psInsertH.setInt(3, heure.getTypeHeure().getIdTypeHeure());
			this.psInsertH.setInt(4, heure.getDuree());
			this.psInsertH.executeUpdate();
		} else {
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" deja existant");
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
		} else {
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
			this.psUpdateH.setInt(2, heure.getTypeHeure().getIdTypeHeure());
			this.psUpdateH.setInt(3, heure.getDuree());
			this.psUpdateH.executeUpdate();
		} else {
			System.out.println("Heure id_heure = "+heure.getIdHeure()+" inexistant");
		}
	}


	public void insertTypeHeure(TypeHeure typeHeure) throws SQLException {
		
		String request = "SELECT * FROM Type_Heure WHERE id_type_heure="+typeHeure.getIdTypeHeure()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig <= 0 ) {
			this.psInsertTH.setInt(1, typeHeure.getIdTypeHeure());
			this.psInsertTH.setFloat(2, typeHeure.getCoeff());
			this.psInsertTH.executeUpdate();
		} else {
			System.out.println("TypeHeure id_type_heure = "+typeHeure.getIdTypeHeure()+" deja existant");
		}
	}


	public void deleteTypeHeure(TypeHeure typeHeure) throws SQLException {
		String request = "SELECT * FROM Type_Heure WHERE id_type_heure="+typeHeure.getIdTypeHeure()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig > 0 ) {
			this.psDeleteTH.setInt(1, typeHeure.getIdTypeHeure());
			this.psDeleteTH.executeUpdate();
		} else {
			System.out.println("TypeHeure id_type_heure = "+typeHeure.getIdTypeHeure()+" inexistant");
		}
	}


	public void updateTypeHeure(TypeHeure typeHeure) throws SQLException {
		Statement stmt = this.connec.createStatement();

		String request = "SELECT * FROM Type_Heure WHERE id_type_heure="+typeHeure.getIdTypeHeure()+";";

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while (rs.next()) cptLig ++;

		if ( cptLig > 0 ) {
			this.psUpdateTH.setFloat(1, typeHeure.getCoeff());
			this.psUpdateTH.setInt(2, typeHeure.getIdTypeHeure());
			this.psUpdateTH.executeUpdate();
		} else {
			System.out.println("TypeHeure id_type_heure = "+typeHeure.getIdTypeHeure()+" inexistant");
		}
	}


	public void insertModule(Module module) throws SQLException {
		
		String request = "SELECT * FROM Module WHERE id_module="+module.getIdModule()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig <= 0 ) {
			this.psInsertM.setInt(1, module.getIdModule());
			this.psInsertM.setString(2, module.getTypeModule());
			this.psInsertM.setString(3, module.getLibelle());
			this.psInsertM.setString(4, module.getLibelleCourt());
			this.psInsertM.setString(5, module.getCode());
			this.psInsertM.setInt(6, module.getNbEtudiants());
			this.psInsertM.setInt(7, module.getNbGpTD());
			this.psInsertM.setInt(8, module.getNbGpTP());
			this.psInsertM.setInt(9, module.getNbSemaines());
			this.psInsertM.setInt(10, module.getNbHeures());
			this.psInsertM.executeUpdate();
		} else {
			System.out.println("Module id_module = "+module.getIdModule()+" deja existant");
		}
	}


	public void deleteModule(Module module) throws SQLException {
		String request = "SELECT * FROM Module WHERE id_module="+module.getIdModule()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig > 0 ) {
			this.psDeleteM.setInt(1, module.getIdModule());
			this.psDeleteM.executeUpdate();
		} else {
			System.out.println("Module id_module = "+module.getIdModule()+" inexistant");
		}
	}


	public void updateModule(Module module) throws SQLException {
		Statement stmt = this.connec.createStatement();

		String request = "SELECT * FROM Module WHERE id_module="+module.getIdModule()+";";

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while (rs.next()) cptLig ++;

		if ( cptLig > 0 ) {
			this.psUpdateM.setString(1, module.getTypeModule());
			this.psUpdateM.setString(2, module.getLibelle());
			this.psUpdateM.setString(3, module.getLibelleCourt());
			this.psUpdateM.setString(4, module.getCode());
			this.psUpdateM.setInt(5, module.getNbEtudiants());
			this.psUpdateM.setInt(6, module.getNbGpTD());
			this.psUpdateM.setInt(7, module.getNbGpTP());
			this.psUpdateM.setInt(8, module.getNbSemaines());
			this.psUpdateM.setInt(9, module.getNbHeures());
			this.psUpdateM.setInt(10, module.getIdModule());
			this.psUpdateM.executeUpdate();
		} else {
			System.out.println("Module id_module = "+module.getIdModule()+" inexistant");
		}
	}

	public void insertStatut(Statut statut) throws SQLException {

		String request = "SELECT * FROM Statut WHERE nom_statut="+statut.getNomStatut()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig <= 0 ) {
			this.psInsertS.setString(1, statut.getNomStatut());
			this.psInsertS.setInt(2, statut.getNbMinHeure());
			this.psInsertS.setInt(3, statut.getNbMaxHeure());
			this.psInsertS.setFloat(4, statut.getCoeffTP());
			this.psInsertTH.executeUpdate();
		} else {
			System.out.println("statut id_statut = "+statut.getNomStatut()+" deja existant");
		}
	}


	public void deleteStatut(Statut statut) throws SQLException {
		String request = "SELECT * FROM Statut WHERE nom_statut="+statut.getNomStatut()+";";

		Statement stmt = this.connec.createStatement();

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		if ( cptLig > 0 ) {
			this.psDeleteS.setString(1, statut.getNomStatut());
			this.psDeleteS.executeUpdate();
		} else {
			System.out.println("Statut nom_statut = "+statut.getNomStatut()+" inexistant");
		}
	}


	public void updateStatut(Statut statut) throws SQLException {
		Statement stmt = this.connec.createStatement();

		String request = "SELECT * FROM Statut WHERE nom_statut="+statut.getNomStatut()+";";

		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while (rs.next()) cptLig ++;

		if ( cptLig > 0 ) {
			this.psUpdateS.setInt(1, statut.getNbMinHeure());
			this.psUpdateS.setInt(2, statut.getNbMaxHeure());
			this.psUpdateS.setFloat(3, statut.getCoeffTP());
			this.psUpdateS.setString(4, statut.getNomStatut());
			this.psUpdateS.executeUpdate();
		} else {
			System.out.println("Statut nom_statut = "+statut.getNomStatut()+" inexistant");
		}
	}

	
	public boolean existsIntervenantModule(int idIntervenant, int idModule) throws SQLException {
		String request = "SELECT * FROM Intervenant_Module WHERE id_intervenant="+idIntervenant+" AND id_module="+idModule+";";

		Statement stmt = this.connec.createStatement();
		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertIntervenantModule(ArrayList<Intervenant> intervenants, Module module) throws SQLException {

		for (Intervenant intervenant : intervenants) {
			if ( !this.existsIntervenantModule(intervenant.getIdIntervenant(), module.getIdModule()) ) {
				this.psInsertIM.setInt(1, intervenant.getIdIntervenant());
				this.psInsertIM.setInt(2, module.getIdModule());
				this.psInsertIM.executeUpdate();
			} else {
				System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" deja existant");
			}
		}
	}

	public void insertIntervenantModule(Intervenant intervenant, Module module) throws SQLException {

		if ( !this.existsIntervenantModule(intervenant.getIdIntervenant(), module.getIdModule()) ) {
			this.psInsertIM.setInt(1, intervenant.getIdIntervenant());
			this.psInsertIM.setInt(2, module.getIdModule());
			this.psInsertIM.executeUpdate();
		} else {
			System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" deja existant");
		}
	}


	public void deleteIntervenantModule(Intervenant intervenant, Module module) throws SQLException {

		if ( !this.existsIntervenantModule(intervenant.getIdIntervenant(), module.getIdModule()) ) {
			this.psDeleteIM.setInt(1, intervenant.getIdIntervenant());
			this.psDeleteIM.setInt(2, module.getIdModule());
			this.psDeleteIM.executeUpdate();
		} else {
			System.out.println("IntervenantModule id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" inexistant");
		}
	}


	public void updateIntervenantModule(Intervenant intervenant, Module module) throws SQLException {

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



	public boolean existsIntervenantHeure(int idIntervenant, int idHeure) throws SQLException {
		String request = "SELECT * FROM Intervenant_Heure WHERE id_intervenant="+idIntervenant+" AND id_heure="+idHeure+";";

		Statement stmt = this.connec.createStatement();
		ResultSet rs = stmt.executeQuery(request);

		int cptLig = 0;
		while ( rs.next() ) cptLig ++;

		return cptLig > 0;
	}


	public void insertIntervenantHeure(ArrayList<Intervenant> intervenants, Heure heure) throws SQLException {

		for (Intervenant intervenant : intervenants) {
			if ( !this.existsIntervenantHeure(intervenant.getIdIntervenant(), heure.getIdHeure()) ) {
				this.psInsertIH.setInt(1, intervenant.getIdIntervenant());
				this.psInsertIH.setInt(2, heure.getIdHeure());
				this.psInsertIH.executeUpdate();
			} else {
				System.out.println("IntervenantHeure id_intervenant = "+intervenant.getIdIntervenant()+", id_heure = "+heure.getIdHeure()+" deja existant");
			}
		}
	}

	public void insertIntervenantHeure(Intervenant intervenant, Heure heure) throws SQLException {

		if ( !this.existsIntervenantModule(intervenant.getIdIntervenant(), heure.getIdHeure()) ) {
			this.psInsertIM.setInt(1, intervenant.getIdIntervenant());
			this.psInsertIM.setInt(2, heure.getIdHeure());
			this.psInsertIM.executeUpdate();
		} else {
			System.out.println("IntervenantHeure id_intervenant = "+intervenant.getIdIntervenant()+", id_heure = "+heure.getIdHeure()+" deja existant");
		}
	}


	public void deleteIntervenantHeure(Intervenant intervenant, Heure heure) throws SQLException {

		if ( !this.existsIntervenantModule(intervenant.getIdIntervenant(), heure.getIdHeure()) ) {
			this.psDeleteIM.setInt(1, intervenant.getIdIntervenant());
			this.psDeleteIM.setInt(2, heure.getIdHeure());
			this.psDeleteIM.executeUpdate();
		} else {
			System.out.println("Intervenant id_intervenant = "+intervenant.getIdIntervenant()+", id_module = "+module.getIdModule()+" inexistant");
		}
	}


	public void updateIntervenantModule(Intervenant intervenant, Heure module) throws SQLException {

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














	private ArrayList<Intervenant> getIntervenants(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Intervenant> listeI = new ArrayList<Intervenant>();
		
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Intervenant p = new Intervenant(rs.getInt("id_intervenant"),rs.getString("nom"), rs.getString("prenom"),
											new Statut(rs.getString("nom_statut"), rs.getInt("nb_heure_mini"), rs.getInt("nb_heure_maxi"), 
											           rs.getInt("coeff_tp")),rs.getInt("nb_equivalent_td")                             );
			listeI.add(p);
		  }
		rs.close();
		return listeI;
	}
	
	public ArrayList<Intervenant> getIntervenants() throws SQLException {
		return getIntervenants("SELECT * FROM Intervenant i JOIN Statut s ON s.id_statut = i.id_statut;");
	}


	private ArrayList<Statut> getStatuts(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Statut> listeI  = new ArrayList<Statut>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Statut p = new Statut(rs.getString("id_statut"  ), rs.getInt("nb_heure_mini" ), 
			                      rs.getInt   ("nb_heure_maxi"), rs.getInt("coeff_tp"     ));
			listeI.add(p);
		  }
		rs.close(); 
		return listeI;
	}

	public ArrayList<Statut> getStatuts() throws SQLException {
		return getStatuts("SELECT * FROM Statut;");
	}


	private ArrayList<TypeHeure> getTypesHeures(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<TypeHeure> listeI  = new ArrayList<TypeHeure>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			TypeHeure p = new TypeHeure(rs.getInt("id_type_heure"), rs.getString("nom_type_heure"), rs.getFloat("coeff"));
			listeI.add(p);
		  }
		rs.close(); 
		return listeI;
	}

	public ArrayList<TypeHeure> getTypesHeures() throws SQLException {
		return getTypesHeures("SELECT * FROM Type_Heure;");
	}



	private ArrayList<Heure> getHeures(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Heure> listeI = new ArrayList<Heure>();
			
		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			// Heure p = new Heure(rs.getInt("id_heure"),
			//                     new Module(rs.getInt("id_module"), rs.getString("type_module"), rs.getString("nb_s"), rs.getString("libele"), 
			// 					           rs.getString("libele_court"), rs.getString("code"), rs.getInt("nb_etudiant"), rs.getInt("nb_gp_td"), 
			// 							   rs.getInt("nb_gp_tp"), rs.getInt("nb_semaine"), rs.getInt("nb_heures")),

			// 					new TypeHeure(rs.getInt("id_type_heure"), rs.getString("nom_type_heure"), rs.getFloat("coeff")), 
			// 					rs.getInt("nb_heures"));


p = c			;()erueH wen = p erue
			listeI.add(p);
		  }

		rs.close(); 
		return listeI;
	}

	public ArrayList<Heure> getHeures() throws SQLException {
		return getHeures("SELECT * FROM Heure h JOIN Module m ON m.id_heure = h.id_heure JOIN Intervenant i ON i.id_intervenant = h.intervenant JOIN Type_Heure t ON t.id_type_heure = h.id_type_heure");
	}



	private ArrayList<Module> getModules(String req) throws SQLException {
		Statement selectNP = connec.createStatement();
		ArrayList<Module> listeI = new ArrayList<Module>();

		ResultSet rs = selectNP.executeQuery(req);
		while(rs.next()){
			Module p = new Module(rs.getInt    ("id_module"     ),
				                  rs.getString ("type_module"   ),
			                      rs.getString ("nb_s"          ),
								  rs.getString ("libele"        ),
								  rs.getString ("libele_court"  ),
								  rs.getString ("code"          ),
								  rs.getInt    ("nb_etudiant"   ),
								  rs.getInt    ("nb_gp_td"      ),
								  rs.getInt    ("nb_gp_tp"      ),
								  rs.getInt    ("nb_semaine"    ), 
								  rs.getInt    ("nb_heures"     ));
			listeI.add(p);
			
		  }
		rs.close(); 
		return listeI;
	}

	public ArrayList<Module> getModules() throws SQLException {
		return getModules("SELECT * FROM Module");
	}




}