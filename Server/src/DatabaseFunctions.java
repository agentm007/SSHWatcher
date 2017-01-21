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
            System.out.println("Failed to Create Table");
            e.printStackTrace();
        }
    }

    public static void insertNewIP(String ip, int attempts, String firstAttempt, String lastAttempt){
        Config.load();
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
            e.printStackTrace();
        }

    }

    public static void incrementAttemptAndUpdateLastAttempt(String ip, String lastAttempt){
        Config.load();
        String query = "UPDATE ips SET attempts=attempts+1, last_attempt=? WHERE ip=?";
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, lastAttempt);
            stmt.setString(2, ip);
            int rowsAffected = stmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e){
            System.out.println("Something Went Wrong.");
            e.printStackTrace();
        }

    }

    public static Attempt getRowByIP(String ip){
        Config.load();
        String query = "SELECT * FROM ips WHERE ip = ?;";
        Attempt attempt = null;
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ip);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                attempt = new Attempt(rs.getString("ip"), rs.getInt("attempts"), rs.getString("first_attempt"), rs.getString("last_attempt"));
            return attempt;
        }
        catch (Exception e){
            e.printStackTrace();
            return attempt;
        }
    }
    
}