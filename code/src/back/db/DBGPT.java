package back.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;

public class DBGPT {
    private PGSimpleDataSource dataSource;

    public DB2() {
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setServerName("localhost");
        this.dataSource.setPort(5432);
        this.dataSource.setDatabaseName("astre");
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("coucou");
    }

    public static void main(String[] args) {
        DBGPT db = new DBGPT();
        db.testConnexion(); // Appel pour tester la connexion
    }

    public void testConnexion() {
        try (Connection connection = this.dataSource.getConnection()) {
            System.out.println("Connexion à la base de données réussie.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
