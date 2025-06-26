package DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {
    String path;
    public Connection getDbConnection() {
        if(dbConnection==null) {
            System.out.println("null db");
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Properties properties = new Properties();
            URL u = getClass().getResource("/org/example/slstoreprog/database.properties.properties");
            try {
                path = Paths.get(u.toURI()).toFile().getAbsolutePath();
                System.out.println("path 1 " + path);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            try (InputStream in = Files.newInputStream(Paths.get(path))) {
                properties.load(in);
                String url = properties.getProperty("url");
                dbConnection = DriverManager.getConnection(url);
                System.out.println("Соединение прошло отлично");
            } catch (IOException | SQLException e) {
                e.printStackTrace();

            }
        }

        return dbConnection;
    }

    public static Connection dbConnection;
}
