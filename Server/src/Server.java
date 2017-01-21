import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 1/14/2017.
 */
public class Server {

    public static void start() {
        Config.load();
        try {
            ServerSocket serverSocket = new ServerSocket(Config.getPort(), 10);
            while (true) {
                Socket client = serverSocket.accept();
                String ipAddress = client.getInetAddress().toString().substring(1);
                client.close();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(new Date());

                Attempt attempt = DatabaseFunctions.getRowByIP(ipAddress);
                if(attempt == null)
                    DatabaseFunctions.insertNewIP(ipAddress, 1, date, date );
                else
                    DatabaseFunctions.incrementAttemptAndUpdateLastAttempt(ipAddress, date);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
