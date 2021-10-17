import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://121.0.0.1:8080/bdChunk?serverTimezone=UTC";
        String username = "billy";
        String password = "bibobubibulbis";

        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
