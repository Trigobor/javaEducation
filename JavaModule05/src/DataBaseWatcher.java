import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseWatcher {
    private static Connection connection;
    private static DataBaseWatcher watcher;
    private static final Properties auth = new Properties();
    private static final String forName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5450/postgres";
    private DataBaseWatcher(String name, String password) throws SQLException {
        auth.put("user", name);
        auth.put("password", password);
        connection = DriverManager.getConnection(url, auth);
        if (connection == null)
            System.err.println("Нет соединения с БД!");
        else
            System.out.println("Cоединения с БД установлено!");
        connection.setAutoCommit(true);
    }
    static DataBaseWatcher getWatcher(String name, String password) throws SQLException {
        if (watcher == null)
            watcher = new DataBaseWatcher(name, password);
        if (!name.equals(auth.get("user")) || !password.equals(auth.get("password"))){
            connection.close();
            auth.put("user", name);
            auth.put("password", password);
            connection = DriverManager.getConnection(url, auth);
            connection.setAutoCommit(true);
        }
        return watcher;
    }

    public static Connection getConnection() {
        return connection;
    }
}

