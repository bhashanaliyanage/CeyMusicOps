import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    // Connection parameters
    private static String url = "jdbc:mysql://localhost:3306/ceymusicops";
    private static String username = "root";
    private static String password = "admin";
    private static boolean querySuccess;

    public static Connection con;

    public static Connection utilizeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to CeyMusicOps via Java SQL...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static boolean addEntry(String ISRC, String amount) {
        PreparedStatement pst;
        int rs;
        double amountConverted = Double.parseDouble(amount);

        try {
            con = Database.utilizeConnection();
            pst = con.prepareStatement("INSERT INTO earnings (ISRC, EUR) VALUES (?, ?)");
            pst.setString(1, ISRC);
            pst.setDouble(2, amountConverted);
            rs = pst.executeUpdate();
            if (rs > 0) {
                querySuccess = true;
            } else {
                querySuccess = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccess;
    }
    
    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the connection.");
            e.printStackTrace();
        }
    }
}