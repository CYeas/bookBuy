package util;


import config.Config;
import model.Admin;
import model.Customer;
import model.User;

import java.sql.*;

/**
 * Created by cyea on 2017/11/6.
 */
public class DatabaseUtil {
    public static void main( String args[] ) {

    }

    public int initDatabase() {
        int status = 0;
        status += createUserTb();
        status += createBookTb();
        status += createUserTb();
        if(status > 0) {
            return Message.DatabaseInitError;
        } else {
            return Message.Success;
        }
    }

    public static int runUpdateSql(String sql) {
        Connection c = null;
        try {
            c = openDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Message.DatabaseOpenError;
        } catch (SQLException e) {
            e.printStackTrace();
            return Message.DatabaseOpenError;
        }
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            return Message.Success;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Message.DatabaseUpdateError;
    }

    public static User login(String name, String password) {
        String sql = "SELECT * FROM User" +
                "WHERE Username = '" + name +"'" +
                ";";
        Connection c = null;
        try {
            c = openDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = runQuerySql(stmt, sql);
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String dbName = rs.getString("Username");
                String dbPassword  = rs.getString("Password");
                int  dbUserType = rs.getInt("UserType");
                if (password == dbPassword) {
                    switch (id) {
                        case UserType.Admin:
                            return new Admin(id, dbName, dbPassword, dbUserType);
                        case UserType.Customer:
                            return new Customer(id, dbName, dbPassword, dbUserType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private static ResultSet runQuerySql(Statement stmt, String sql) throws SQLException {
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    private int createUserTb() {
        String sql = "CREATE TABLE User " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " Username       TEXT    NOT NULL UNIQUE, " +
                " Password       TEXT    NOT NULL, " +
                " UserType       INT     NOT NULL)";
        return runUpdateSql(sql);
    }

    private int createBookTb() {
        String sql = "CREATE TABLE Book " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " Name       TEXT    NOT NULL, " +
                " Author     TEXT    NOT NULL, " +
                " Price      FLOAT   NOT NULL," +
                " Available  INT     NOT NULL," +
                " Storage    INT     NOT NULL)";
        return runUpdateSql(sql);
    }

    private int createRecordTb() {
        String sql = "CREATE TABLE Record " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " UserId       INT    NOT NULL, " +
                " BookId       INT    NOT NULL, " +
                " Sum          INT     NOT NULL)";
        return runUpdateSql(sql);
    }

    private static Connection openDatabase() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:"+ Config.dbFile);
        return c;
    }
}
