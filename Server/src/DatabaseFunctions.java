import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Alex on 1/14/2017.
 */
public class DatabaseFunctions {
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static void createTable(){
        Config.load();
        String query = "CREATE TABLE ips( ip VARCHAR(15) NOT NULL," +
                "attempts INT," +
                "first_attempt DATETIME," +
                "last_attempt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
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

    public static void insertNewIP(String ip, int attempts, String firstAttempt){
        Config.load();
        String query = "INSERT INTO ips (ip, attempts, first_attempt)" +
                "VALUES(?, ?, ?);";
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ip);
            stmt.setInt(2, attempts);
            stmt.setString(3, firstAttempt);
            int rowsAffected = stmt.executeUpdate();
            conn.commit();

        }
        catch(Exception e){
            System.out.println("Something Went Wrong.");
            e.printStackTrace();
        }

    }

    public static void incrementAttemptAndUpdateLastAttempt(String ip){
        Config.load();
        String query = "UPDATE ips SET attempts=attempts+1 WHERE ip=?";
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ip);
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

    public static ArrayList<Attempt> getTopResults(String query, int top){
        Config.load();
        ArrayList<Attempt> attempts = new ArrayList<Attempt>();
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, top);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                attempts.add(new Attempt(rs.getString("ip"), rs.getInt("attempts"), rs.getString("first_attempt"), rs.getString("last_attempt")));
            }
            return attempts;
        }
        catch (Exception e){
            e.printStackTrace();
            return attempts;
        }
    }

    public static int getRowCount(){
        Config.load();
        String query = "SELECT COUNT(*) FROM ips";
        int rows = 0;
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            rows = rs.getInt(1);
            return rows;
        }
        catch (Exception e){
            e.printStackTrace();
            return rows;
        }
    }

    public static int limitedDelete(String query, int limit){
        Config.load();
        int rowsAffected = 0;
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Config.getConnectionString(), Config.getUsername(), Config.getPassword());
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, limit);
            rowsAffected = stmt.executeUpdate();
            conn.commit();
            return rowsAffected;
        }
        catch (Exception e){
            e.printStackTrace();
            return rowsAffected;
        }
    }

    public static void closeResources(PreparedStatement stmt, Connection conn) throws SQLException{
        if( stmt != null)
            stmt.close();
        if( conn != null)
            conn.close();
    }
}
