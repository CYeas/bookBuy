package test;

import controller.controller;
import model.Admin;
import model.Book;
import model.User;

import javax.naming.ldap.Control;

public class controllerTest {
    public static void main(String args[]) {
        //new DatabaseUtil().initDatabase();
        //new Admin(0 ,"cyea", "1234", 0).signUp();
        //new Admin(1 ,"cyea2", "1234", 0).signUp();
        //new Customer(2 ,"customer", "1234", 1).signUp();
        controller Controller = new controller();
        TestLogin();
        TestAddBook();
        TestDeleteBook();
        TestGetList();
        TestGetInfo();
        TestGetSpecialList();
        TestBuyBook();
        Controller.runUser();


    }

    private static void TestLogin() {
        System.out.println("登陆测试");
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
        System.out.println();
    }

    private static void TestAddBook(){
        System.out.println("书籍增加测试");
        Admin admin = new Admin(200,"test","1234",0);
        int flag;
        System.out.print("(S)1: ");
        flag = admin.createBook("book1", "author1", 20,1, 200);
        System.out.println(flag);

        System.out.print("(F)2: ");
        flag = admin.createBook( "book1", "author2", 20, 1,200);
        System.out.println(flag);

        System.out.print("(F)3: ");
        flag = admin.createBook( "", "", 20, 1,300);
        System.out.println(flag);

        System.out.print("(F)4: ");
        flag = admin.createBook( "book4", "author4", -1, 1,200);
        System.out.println(flag);

        System.out.print("(S)5: ");
        flag = admin.createBook( "book5", "author5", 999999999, 1,200);
        System.out.println(flag);

        System.out.print("(F)6: ");
        flag = admin.createBook( "book6", "author5", 20, -1,1);
        System.out.println(flag);

        System.out.print("(F)7: ");
        flag = admin.createBook( "book7", "author5", 20, 200,-1);
        System.out.println(flag);
    }

    private static void TestDeleteBook(){
        System.out.println("书籍删除测试，请务必保证书籍增加测试正常运行");
    }

    private static void TestGetList(){
        System.out.println("书籍列表查看测试");
    }

    private static void TestGetInfo(){
        System.out.println("书籍详情查看测试");
    }

    private static void TestGetSpecialList(){
        System.out.println("书籍条件查询测试");
    }

    private static void TestBuyBook(){
        System.out.println("购书、库存更新、交易记录增加测试");
    }
}
