import java.io.*;
import java.util.Properties;

/**
 * Created by Alex on 1/20/2017.
 */
public class Config {

    public static int port = 22;
    public static String connectionString = "";
    public static String username = "testing";
    public static String password = "test";
    public static String fileName = "config.xml";
    public static String fileLocation = "/etc/SSHWatcher/";

    public static void load(){
        determineOS();
        try {
            File file = new File(fileLocation + fileName);
            Properties prop = new Properties();
            InputStream inputStream = new FileInputStream(file);
            prop.loadFromXML(inputStream);
            port = Integer.parseInt(prop.getProperty("Port"));
            connectionString = prop.getProperty("Connection String");
            username = prop.getProperty("Username");
            password = prop.getProperty("Password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createAndUpdate(){
        determineOS();
        try {
            Properties props = setProperties();
            File xmlFile = new File(fileLocation + fileName);
            File location = new File(fileLocation);

            if(!location.exists())
                location.mkdir();

            OutputStream out = new FileOutputStream(xmlFile);
            props.storeToXML(out,"Configuration XML");

        } catch (IOException e) {
            System.out.println("Unable to create XML configuration file. " + e.getStackTrace());
        }

    }

    public static Properties setProperties(){
        Properties props = new Properties();
        props.setProperty("Port", Integer.toString(port));
        props.setProperty("Connection String", connectionString);
        props.setProperty("Username", username);
        props.setProperty("Password", password);
        return props;
    }

    public static void determineOS(){
        if(System.getProperty("os.name").contains("Windows"))
            fileLocation = "C:/Program Files/SSHWatcher/";
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Config.port = port;
    }

    public static String getConnectionString() {
        return connectionString;
    }

    public static void setConnectionString(String connectionString) {
        Config.connectionString = connectionString;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Config.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Config.password = password;
    }
}
