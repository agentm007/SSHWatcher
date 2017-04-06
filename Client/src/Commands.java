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


    public static void printAttempts(ArrayList<Attempt> attempts){
        for(int i=0; i<attempts.size(); i++){
            System.out.println(attempts.get(i).toString());
        }
    }
}
