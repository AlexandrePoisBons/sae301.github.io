package back.db;

import java.sql.*;
import org.postgresql.ds.common.PGSimpleDataSource;

public class DB2
{
	private PGSimpleDataSource dataSource;

	public DB2()
	{
		this.dataSource = new PGSimpleDataSource();
		this.dataSource.setServerName("localhost");
		this.dataSource.setPort(5432);
		this.dataSource.setDatabaseName("astre"); // A changer
		this.dataSource.setUser("postgres");         // A changer
		this.dataSource.setPassword("coucou");       // A changer
	}

	public static void main(String[] args){ DB2 db = new DB2(); db.testPilote(); }

	public void testPilote()
	{
		try{

			Class.forName ("org.postgresql.Driver");
			System.out.println ("CHARGEMENT DU PILOTE OK");

			} catch (ClassNotFoundException e) { System.out.println(e); }
	}


	public Connection getConnection() throws SQLException { return this.dataSource.getConnection(); }

	public void insereProduit(int np, String lib, String coul, int qs )
	{
		if ( np < 0 || lib == null || qs < 0 )
			System.out.println("Insertion impossible, paramètre invalide");;

		try {
			Connection connec = this.getConnection();
			System.out.println("Connection OK" + connec );

			PreparedStatement ps = connec.prepareStatement("INSERT INTO Produit VALUES (?, ?, ?, ?)");
			ps.setInt(1, np);
			ps.setString(2, lib);
			ps.setString(3, coul);
			ps.setInt(4, qs);

			ps.executeUpdate();
		}catch(SQLException er){ System.out.println("Echec connection"); er.printStackTrace(); }
		
		System.out.println("Insertion effectué");;
	}




	// public static void main(String[] args) 
	// {
	// 	P3 db = new P3();

	// 	db.testPilote();
	
	// 	try {
	// 		Connection connec = db.getConnection();
	// 		System.out.println("Connection OK" + connec );
   
	// 		Statement st = connec.createStatement();
	// 		ResultSet rs = st.executeQuery("SELECT lib, qs FROM Produit");
			
	// 		System.out.println("\t lib \t\t | \t qs");
			
	// 		while(rs.next())
	// 		{
	// 			String lib = rs.getString("lib");
	// 			int qs = rs.getInt("qs");
	// 			System.out.println("\t" + lib + "\t\t | \t" + qs);
	// 		}
			
	// 		rs.close();
	// 		connec.close();
	// 	} catch(SQLException er){ System.out.println("Echec connection"); er.printStackTrace(); }

	// 	db.insereProduit(100, "test", "rouge", 10);
	// }


}
