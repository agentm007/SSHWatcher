/**
 * Created by Alex on 1/14/2017.
 */
public class Main {

    public static void main(String[] args) {
        //Config.create();
        Config.load();

        System.out.println(Config.getPort());
    }
}
