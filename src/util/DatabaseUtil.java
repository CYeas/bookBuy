package util;


import config.Config;
import model.Admin;
import model.Customer;
import model.User;
import model.Book;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by cyea on 2017/11/6.
 */
public class DatabaseUtil {

    public int initDatabase() {
        int status = 0;
        status += createUserTb();
        status += createBookTb();
        status += createRecordTb();
        return status == Message.Success ? Message.Success : Message.DatabaseInitError;
    }

    public static int runUpdateSql(String sql) {
        Connection c = null;
        try {
            c = openDatabase();
        } catch (ClassNotFoundException e) {
            return Message.DatabaseOpenError;
        } catch (SQLException e) {
            return Message.DatabaseOpenError;
        }
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            return Message.Success;
        } catch (SQLException e) {
            return Message.DatabaseUpdateError;
        }
    }

    public static User login(String name, String password) {
        String sql = "SELECT rowid, * FROM User WHERE Username='" + name +"';";
        Connection c = null;
        try {
            c = openDatabase();
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = runQuerySql(stmt, sql);
            while ( rs.next() ) {
                int id = rs.getInt("rowid");
                String dbName = rs.getString("Username");
                String dbPassword  = rs.getString("Password");
                int  dbUserType = rs.getInt("UserType");
                rs.close();
                stmt.close();
                c.close();
                if (password.equals(dbPassword)) {
                    switch (dbUserType) {
                        case UserType.Admin:
                            System.out.println("Success: Admin");
                            return new Admin(id, dbName, dbPassword, dbUserType);

                        case UserType.Customer:
                            System.out.println("Success: Customer");
                            return new Customer(id, dbName, dbPassword, dbUserType);
                    }
                }
                else {
                    System.out.println("Failed");
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public static ArrayList<Book> getBook(int bookNum) {
        String sql = "SELECT rowid, * FROM Book" + ";";
        if(bookNum != -2) {
            sql = "SELECT rowid, * FROM Book " +
                    "WHERE rowid = " + bookNum +
                    ";";
        }
        Connection c = null;
        ArrayList<Book> books = new ArrayList<>();
        try {
            c = openDatabase();
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = runQuerySql(stmt, sql);
            while ( rs.next() ) {
                books.add(makeBook(rs));
            }
            rs.close();
            stmt.close();
            c.close();
            return books;
        } catch (SQLException e) {
            return null;
        }
    }

    private static Book makeBook(ResultSet rs) throws SQLException {
        int id = rs.getInt("rowid");
        String name = rs.getString("Name");
        String author  = rs.getString("Author");
        float  price = rs.getFloat("Price");
        int available = rs.getInt("Available");
        int storage = rs.getInt("Storage");
        return new Book(id, name, author, price, available, storage);
    }

    private static ResultSet runQuerySql(Statement stmt, String sql) throws SQLException {
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    private int createUserTb() {
        String sql = "CREATE TABLE User " +
//                "(ID INT PRIMARY KEY     NOT NULL," +
                " (Username       TEXT    NOT NULL UNIQUE, " +
                " Password       TEXT    NOT NULL, " +
                " UserType       INT     NOT NULL)";
        return runUpdateSql(sql);
    }

    private int createBookTb() {
        String sql = "CREATE TABLE Book " +
//                "(ID INT PRIMARY KEY     NOT NULL," +
                " (Name       TEXT    NOT NULL, " +
                " Author     TEXT    NOT NULL, " +
                " Price      FLOAT   NOT NULL," +
                " Available  INT     NOT NULL," +
                " Storage    INT     NOT NULL)";
        return runUpdateSql(sql);
    }

    private int createRecordTb() {
        String sql = "CREATE TABLE Record " +
//                "(ID INT PRIMARY KEY     NOT NULL," +
                " (UserId       INT    NOT NULL, " +
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
