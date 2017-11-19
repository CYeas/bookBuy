package test;

import model.Admin;
import model.Customer;
import model.User;
import util.DatabaseUtil;
import util.UserType;

/**
 * Created by cyans on 2017/11/7.
 */
public class DatabaseTest {
    public static void main( String args[]) {
        new DatabaseUtil().initDatabase();
//        new Admin(0 ,"Admin_1", "password", 0).signUp();
//        new Admin(0 ,"Admin_2", "password", 0).signUp();
//        new Customer(0 ,"Customer_1", "1234", 1).signUp();
//        new Customer(0 ,"Customer_2", "1234", 1).signUp();

        //new DatabaseTest().createBook();
        //new DatabaseTest().buyBook();
    }

    public void createBook() {
       User user = User.login("cyea", "1234");
       if(user.getUserType() == UserType.Admin) {
           Admin admin = (Admin) user;
           admin.createBook("lalala", "me", 100, 1, 10);
       }
    }

    public void buyBook() {
        User user = User.login("customer", "1234");
        if(user.getUserType() == UserType.Admin) {
            Admin admin = (Admin) user;
        } else {
            Customer customer = (Customer) user;
            int id = customer.getAllBook().get(0).getBookNumber();
            customer.purchase(id, 3);
        }
    }
}
