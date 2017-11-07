package model;

import util.DatabaseUtil;
import util.Message;

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


    protected static User login(String name, String password){
        return DatabaseUtil.login(name, password);
    }

    protected void logOut() {}

    public int isUserAvailabe() {
        if(this.id != -1) {
            return Message.Success;
        } else {
            return Message.UserIsLogOut;
        }
    }

    public boolean isUserType(int type){
       if(this.userType == type) {
           return true;
       } else {
           return false;
       }
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
