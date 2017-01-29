/**
 * Created by Alex on 1/14/2017.
 */
public class Main {

    public static void main(String[] args) {

        //TODO Write install instructions
        if(args.length != 0){
            switch(args[0]){
                case "--install":
                    Config.createAndUpdate();
                    break;
                default:
                    Server.start();
                    break;
            }
        }
        else {
            Server.start();
        }

    }
}
