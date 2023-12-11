package back.db;

import java.sql.*;
import java.util.ArrayList;

public class DB {
	private Connection connec;
	private static DB dbInstance;
	private PreparedStatement psSelectP;
	private PreparedStatement psInsertP;
	private PreparedStatement psDeleteP;
	private PreparedStatement psUpdateP;

	public static void main(String[] args) { DB db = DB.getInstance(); }

	private DB() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println ("CHARGEMENT DU PILOTE OK");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connec = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","coucou");
			
			connec.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DB getInstance() {
		if(dbInstance==null){dbInstance=new DB();}
		return dbInstance;
	}

	public Produit getProduit(int np) throws SQLException{
		Produit p = null;
		psSelectP.setInt(1,np);
		ResultSet rsProd=psSelectP.executeQuery();
		if(rsProd.next())
			p = new Produit(rsProd.getInt("np"),rsProd.getString("lib"),rsProd.getString("coul"),rsProd.getInt("qs"));  
		rsProd.close();
		return p;
	}

	// //Methode a n'utiliser que dans la classe DB
	// //Le parametre req doit correspondre a une requete de la forme "SELECT * FROM produit WHERE ..."
	private ArrayList<Produit> getProduits(String req) throws SQLException {
		Statement selectNP=connec.createStatement();
		ArrayList<Produit> listeP=new ArrayList<Produit>();
			
		ResultSet rsP=selectNP.executeQuery(req);
		while(rsP.next()){
			Produit p = new Produit(rsP.getInt("np"),rsP.getString("lib"),rsP.getString("coul"),rsP.getInt("qs"));
			listeP.add(p);
		  }
		rsP.close(); 
		return listeP;
	}

	public ArrayList<Produit> getProduits() throws SQLException {
		return getProduits("SELECT * FROM Produit");
	}

	// public void insertProduit(Produit p) throws SQLException {
		
	// 	String request = "SELECT * FROM Produit WHERE np="+p.getNp()+";";

	// 	Statement stmt = this.connec.createStatement();

	// 	ResultSet rs = stmt.executeQuery(request);

	// 	int cptLig = 0;
	// 	while ( rs.next() ) cptLig ++;


	// 	if ( cptLig <= 0 ) {
	// 		this.psInsertP.setInt(1, p.getNp());
	// 		this.psInsertP.setString(2, p.getLib());
	// 		this.psInsertP.setString(3, p.getCoul());
	// 		this.psInsertP.setInt(4, p.getQs());
	// 		this.psInsertP.executeUpdate();
	// 	}
	// 	else {
	// 		System.out.println("Produit np="+p.getNp()+" existant");
	// 	}

	// }

	// public void updateProduit(int np, String coul) throws SQLException {
	
	// 	Statement stmt = this.connec.createStatement();

	// 	String request = "SELECT * FROM Produit WHERE np ="+np+";";

	// 	this.psUpdateP.setInt(1, np);
	// 	this.psUpdateP.setString(2, coul);

	// 	ResultSet rs = stmt.executeQuery(request);

	// 	int cptLig = 0;
	// 	while (rs.next()) cptLig ++;

	// 	if ( cptLig > 0 ) {
	// 		this.psUpdateP.setInt(1, np);
	// 		this.psUpdateP.setString(2, coul);
	// 		this.psUpdateP.executeUpdate();
	// 	}
	// 	else
	// 		System.out.println("Ce numero de produit n'existe pas");

	// }

	// public void deleteProduit(int np) throws SQLException {

	// 		String request = "SELECT np FROM Produit WHERE np="+np+";";

	// 		Statement stmt = this.connec.createStatement();

	// 		ResultSet rs = stmt.executeQuery(request);

	// 		int cptLig = 0;
	// 		while ( rs.next() ) cptLig ++;

	// 		if ( cptLig > 0 ) {
	// 			this.psDeleteP.setInt(1, np);
	// 			this.psDeleteP.executeUpdate();
	// 		}
	// 		else
	// 			System.out.println("np="+np+" existe pas");

	// }

	// public int getNbProduits() throws SQLException {
	// 	return this.getProduits().size();
	// }
	
} //fin classe DB

