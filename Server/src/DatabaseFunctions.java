import java.sql.*;

/**
 * Created by Alex on 1/14/2017.
 */
public class DatabaseFunctions {
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static void createTable(){
        Config.load();
        String query = "CREATE TABLE ips( ip VARCHAR(15) NOT NULL," +
                "attempts INT," +
                "first_attempt DATE," +
                "last_attempt DATE," +
                "PRIMARY KEY (ip))";
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.execute(query);
            conn.commit();
        }
        catch(Exception e){
            System.out.println("Didn't work");
        }
    }

    public static void insertNewIP(String ip, int attempts, String firstAttempt, String lastAttempt){
        String query = "INSERT INTO ips (ip, attempts, first_attempt, last_attempt)" +
                "VALUES(?, ?, ?, ?);";
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ip);
            stmt.setInt(2, attempts);
            stmt.setString(3, firstAttempt);
            stmt.setString(4, lastAttempt);
            int rowsAffected = stmt.executeUpdate();
            conn.commit();

        }
        catch(Exception e){
            System.out.println("Something Went Wrong.");
        }

    }

    public static void incrimentAttempt(){
        String query = "";

    }

    public static void updateLastDate(){
        String query = "";

    }
    
}
