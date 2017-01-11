package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;

    public static void initConnection(String driver, String username, String password, String host, String port, String dbName) throws SQLException {
        String url = "jdbc:" + driver + "://" + host + ":" + port + "/" + dbName;
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", username);
        connectionProperties.put("password", password);
        connection = DriverManager.getConnection(url, connectionProperties);
    }

    public static Connection  getConnection(){
        return connection;
    }
}
