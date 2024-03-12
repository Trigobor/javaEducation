import java.sql.*;

import org.postgresql.Driver;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        DataBaseWatcher dataBaseWatcher;
        try {
            dataBaseWatcher = DataBaseWatcher.getWatcher("test", "test");
        } catch (SQLException e) {
            System.err.println("sukabluad' : " + e.getMessage());
        }
        try {
            Statement statement = DataBaseWatcher.getConnection().createStatement();
            statement.executeUpdate("drop table users_roles");
            statement.executeUpdate("CREATE TABLE users_roles " +
                    "(" +
                    "users_id numeric REFERENCES users(id), " +
                    "roles_id numeric REFERENCES roles(id), " +
                    "CONSTRAINT users_roles_pk PRIMARY KEY (users_id, roles_id)" +
                    ");");

            statement.executeUpdate("insert into users_roles values ('1', '1')");
            statement.executeUpdate("insert into users_roles values ('1', '5')");
            statement.executeUpdate("insert into users_roles values ('1', '6')");
            statement.executeUpdate("insert into users_roles values ('1', '7')");

            statement.executeUpdate("insert into users_roles values ('2', '1')");
            statement.executeUpdate("insert into users_roles values ('2', '5')");
            statement.executeUpdate("insert into users_roles values ('2', '6')");
            statement.executeUpdate("insert into users_roles values ('2', '7')");

            statement.executeUpdate("insert into users_roles values ('3', '1')");
            statement.executeUpdate("insert into users_roles values ('3', '5')");
            statement.executeUpdate("insert into users_roles values ('3', '6')");
            statement.executeUpdate("insert into users_roles values ('3', '7')");

            statement.executeUpdate("insert into users_roles values ('4', '3')");
            statement.executeUpdate("insert into users_roles values ('4', '5')");
            statement.executeUpdate("insert into users_roles values ('4', '6')");
            statement.executeUpdate("insert into users_roles values ('4', '7')");

            statement.executeUpdate("insert into users_roles values ('5', '1')");
            statement.executeUpdate("insert into users_roles values ('5', '5')");
            statement.executeUpdate("insert into users_roles values ('5', '6')");
            statement.executeUpdate("insert into users_roles values ('5', '7')");

            statement.executeUpdate("insert into users_roles values ('6', '2')");
            statement.executeUpdate("insert into users_roles values ('6', '3')");
            statement.executeUpdate("insert into users_roles values ('6', '4')");

            statement.executeUpdate("insert into users_roles values ('7', '4')");
            statement.executeUpdate("insert into users_roles values ('7', '6')");

            ResultSet rs_u = statement.executeQuery("select * from users");
            int columns = rs_u.getMetaData().getColumnCount();
            while(rs_u.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs_u.getString(i) + "\t");
                }
                System.out.println();
            }

            System.out.print("-------------------------------------------------");
            rs_u = statement.executeQuery("select * from roles");
            columns = rs_u.getMetaData().getColumnCount();
            while(rs_u.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs_u.getString(i) + "\t");
                }
                System.out.println();
            }

            System.out.print("-------------------------------------------------");
            rs_u = statement.executeQuery("select * from users_roles");
            columns = rs_u.getMetaData().getColumnCount();
            while(rs_u.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs_u.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("sukabluad' : " + e.getMessage());
        }
    }
}
