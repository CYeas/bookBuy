package model;

import util.DatabaseUtil;
import util.ModelStatus;

import java.util.ArrayList;

/**
 * Created by cyans on 2017/11/7.
 */
public class User {
    protected int id;
    protected String name;
    protected String password;
    protected int userType;

    public User(int id, String name, String password, int userType) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

//    public int isUserAvailabe() {
//        if(this.id != ModelStatus.NotAvailable) {
//            return Message.Success;
//        } else {
//            return Message.UserIsLogOut;
//        }
//    }
//
//    public boolean isUserType(int type){
//        if(this.userType == type) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public ArrayList<Book> getAllBook() {
        return Book.listAllBook();
    }

    public Book getBook(int id) {
        return Book.getBookById(id);
    }

    public int signUp() {
        String sql = "INSERT INTO User(Username,Password,UserType) " +
                "VALUES('" + this.name + "','" + this.password + "'," + this.userType + ");";
        return DatabaseUtil.runUpdateSql(sql);
    }


    public static User login(String name, String password){
        return DatabaseUtil.login(name, password);
    }

    public void logOut() {
        this.id = ModelStatus.NotAvailable;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserType() {
        return userType;
    }
}
