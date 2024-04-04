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


    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public static Connection connectToDatabase() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
