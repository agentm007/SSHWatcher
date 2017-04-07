import java.util.ArrayList;

/**
 * Created by Alex on 1/31/2017.
 */
public class Commands {

    public static void getTopOffenders(){
        String query = "SELECT * FROM ips ORDER BY attempts DESC LIMIT ?;";
        ArrayList<Attempt> attempts = DatabaseFunctions.getTopResults(query, 10);
        printAttempts(attempts);
    }

    public static void getTopOffenders(int limit){
        String query = "SELECT * FROM ips ORDER BY attempts DESC LIMIT ?;";
        ArrayList<Attempt> attempts = DatabaseFunctions.getTopResults(query, limit);
        printAttempts(attempts);
    }

    public static void getMostRecentOffenders(){
        String query = "SELECT * FROM ips ORDER BY last_attempt DESC LIMIT ?;";
        ArrayList<Attempt> attempts = DatabaseFunctions.getTopResults(query, 10);
        printAttempts(attempts);
    }

    public static void getMostRecentOffenders(int limit){
        String query = "SELECT * FROM ips ORDER BY last_attempt DESC LIMIT ?;";
        ArrayList<Attempt> attempts = DatabaseFunctions.getTopResults(query, limit);
        printAttempts(attempts);
    }

    public static void getTodaysTopOffenders(){
        String query = "SELECT * FROM ips WHERE DATE(last_attempt) = CURDATE() ORDER BY attempts DESC LIMIT ?;";
        ArrayList<Attempt> attempts = DatabaseFunctions.getTopResults(query, 10);
        printAttempts(attempts);
    }

    public static void getTodaysTopOffenders(int limit){
        String query = "SELECT * FROM ips WHERE DATE(last_attempt) = CURDATE() ORDER BY attempts DESC LIMIT ?;";
        ArrayList<Attempt> attempts = DatabaseFunctions.getTopResults(query, limit);
        printAttempts(attempts);
    }

    public static int getRowCount(){
        return DatabaseFunctions.getRowCount();
    }

    public static int deleteLastAttempts(){
        String query = "DELETE FROM ips ORDER BY last_attempt LIMIT ?;";
        int rowsAffected = DatabaseFunctions.limitedDelete(query, 10);
        return rowsAffected;
    }

    public static int deleteLastAttempts(int limit){
        String query = "DELETE FROM ips ORDER BY last_attempt LIMIT ?;";
        int rowsAffected = DatabaseFunctions.limitedDelete(query, limit);
        return rowsAffected;
    }

    public static void printAttempts(ArrayList<Attempt> attempts){
        for(int i=0; i<attempts.size(); i++){
            System.out.println(attempts.get(i).toString());
        }
    }
}
