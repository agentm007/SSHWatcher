/**
 * Created by Alex on 1/14/2017.
 */
public class Main {

    public static void main(String[] args) {

        //TODO Write install instructions
        if(args[0].equals("--install")){
            Config.createAndUpdate();
        }
        else {
            Server.start();
        }

    }
}
