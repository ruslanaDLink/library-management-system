package project.library.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {
    private static DatabaseManager instance;
    private static Connection connection;
    private static final String URL = Credentials.getURL();
    private static final String USERNAME = Credentials.getUSERNAME();
    private static final String PASSWORD = Credentials.getPASSWORD();


    public Connection connectToDatabase() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
