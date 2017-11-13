package test;

import controller.controller;
import model.User;

public class controllerTest {
    public static void main(String args[]) {
        //new DatabaseUtil().initDatabase();
        //new Admin(0 ,"cyea", "1234", 0).signUp();
        //new Admin(1 ,"cyea2", "1234", 0).signUp();
        //new Customer(2 ,"customer", "1234", 1).signUp();
        controller Controller = new controller();
        TestLogin();
        //Controller.Adminop();
        //Controller.Customerop();


    }

    private static void TestLogin() {
        System.out.print("(S)1: ");
        User user1 = User.login("cyea", "1234");
        System.out.print("(S)2: ");
        User user2 = User.login("cyea2", "1234");
        System.out.print("(F)3: ");
        User user3 = User.login("cyea", "123");
        System.out.print("(F)4: ");
        User user4 = User.login("cyea", "12345");
        System.out.print("(F)5: ");
        User user5 = User.login("cyea3", "1234");
        System.out.print("(S)6: ");
        User user6 = User.login("customer", "1234");
        System.out.print("(F)7: ");
        User user7 = User.login("customer", "12345");
        System.out.print("(F)8: ");
        User user8 = User.login("customer1", "1234");




    }
}
