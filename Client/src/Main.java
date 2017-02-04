import java.util.ArrayList;

/**
 * Created by Alex on 1/29/2017.
 */
public class Main {

    public static  void main(String[] args){

        DatabaseFunctions.createTable();
        /*ArrayList<Attempt>  attempts = DatabaseFunctions.selectTodays();
        for(int i=0; i<attempts.size(); i++){
            System.out.println(attempts.get(i).toString());
        }*/
    }
}
