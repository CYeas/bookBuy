package util;


import config.Config;

import java.sql.*;

/**
 * Created by cyea on 2017/11/6.
 */
public class DatabaseUtil {
    public static void main( String args[] ) {

    }

    public int initDatabase() {
        createUserTb();
    }

    public int runUpdateSql(String sql) {
        Connection c = openDatabase();
        if(c == null) {
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

    public String runQuerySql(String sql) {

    }

    private int createUserTb() {
        String sql = "CREATE TABLE User " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " Username       TEXT    NOT NULL, " +
                " Password       TEXT    NOT NULL, " +
                " Usertype       INT     NOT NULL";
        return runUpdateSql(sql);
    }

    private int createBookTb() {
        String sql = "CREATE TABLE Book " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " Username       TEXT    NOT NULL, " +
                " Password       TEXT    NOT NULL, " +
                " Usertype       INT     NOT NULL";
        return runUpdateSql(sql);
    }

    private int createRecordTb() {
        String sql = "CREATE TABLE Record " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " Username       TEXT    NOT NULL, " +
                " Password       TEXT    NOT NULL, " +
                " Usertype       INT     NOT NULL";
        return runUpdateSql(sql);
    }

    private Connection openDatabase() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+ Config.dbFile);
            return c;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return c;
    }
}
