package controller;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Admin;
import model.Book;
import model.Customer;
import model.User;
import util.Line;
import util.Message;
import util.UserType;

import java.util.ArrayList;
import java.util.Scanner;

public class controller {
    private User user;
    private Customer customer;
    private Admin admin;
    private Scanner sc;

    public controller() {
        sc = new Scanner(System.in);
    }

    // input user info and set the user.
    private void logIn() {
        while(true) {
            String name, pwd;
            System.out.println("Step 1: Input your user name:");
            name = sc.next();
            System.out.println("Step 2: Input your password:");
            pwd = sc.next();
            user = User.login(name, pwd);
            if (user != null) {
                break;
            } else {
                System.out.println("login error,please try again");
            }
        }
    }

    private void createBook() {
        String name;
        String author;
        float price;
        int available;
        int storage;
        while (true) {
            System.out.println("Create a new book.");
            System.out.println("Step 1: Input the name of the book.");
            name = sc.next();
            System.out.println("Step 2: Input the author of the book.");
            author = sc.next();
            System.out.println("Step 3: Input the price of the book.");
            price = sc.nextFloat();
            System.out.println("Step 4: Input the availability of the book. (0/1)");
            available = sc.nextInt();
            System.out.println("Step 5: Input the number of storage of the book.");
            storage = sc.nextInt();

            admin.createBook(name, author, price, available, storage);
            System.out.println("Message.Success: " + Message.Success );
            Line.seperateLine();
            System.out.println("Continue creating book? 0 for continue and 1 for quit.");
            int temp = sc.nextInt();
            if (temp==1) break;
        }
    }

    private void deleteBook() {
        int bookNum;
        int id;
        int opt;
        while (true) {
            listAllBook();
            System.out.println("Input the number of the book you want to delete.");
            bookNum = sc.nextInt();
            System.out.println("You are going to delete book " + bookNum +
                    ", are you sure? 0 for delete and 1 for quit:");
            opt = sc.nextInt();
            if (opt == 0) {
//                System.out.println("input book num:");
//               id = admin.getAllBook().get(bookNum).getBookNumber();
//               id = sc.nextInt();
               admin.deleteBook(bookNum);
               System.out.println("Message.Success" + Message.Success);
            }
            Line.seperateLine();
            System.out.println("Continue delete book? 0 for continue and 1 for quit.");
            opt = sc.nextInt();
            if (opt == 1) break;
        }
    }

    private void listAllBook() {

        System.out.println("The list of all books:");
        Line.seperateLine();
        ArrayList<Book> books = user.getAllBook();
        printBooks(books);
    }

    private void runAdmin() {
        admin = (Admin) user;
        int opt;
        System.out.println("Welcome to Administrator's operation page.");
        while (true) {
            System.out.println("You have tow operation to choose bellow.");
            System.out.println("1. Create a book.");
            System.out.println("2. Delete a book.");
            Line.seperateLine();
            System.out.println("Input your option:");
            System.out.println();
            opt = sc.nextInt();
            switch (opt) {
                case 1: createBook(); break;
                case 2: deleteBook(); break;
            }
            System.out.println("Anymore operation? Input 0 for quit, 1 for continue.");
            opt = sc.nextInt();
            if (opt == 0) break;
            //break;
        }
        admin.logOut();
        System.out.println("Admin Log Out.");
    }

    private void purchaseBook() {
        int id;
        int num;
        listAllBook();
        System.out.println("Step 1: Input the ID of the book.");
        id = sc.nextInt();
        System.out.println("Step 1: Input the number of the book.");
        num = sc.nextInt();
        if(customer.purchase(id, num) == Message.Success) {
            System.out.println("Message.Success: " + Message.Success);
        } else {
            System.out.println("purchase book error, plase check the number");
        }
    }

    private void runCustomer() {
        customer = (Customer)user;
        int opt;
        System.out.println("Welcome to Customer's operation page.");
        while (true) {
            System.out.println("You have two choices bellow.");
            System.out.println("1. Purchase some book.");
            System.out.println("2. Search books by name.");
            System.out.println("3. Log out.");
            opt = sc.nextInt();
            if (opt == 1) {
                purchaseBook();
            }
            else if(opt ==2){
                System.out.println("Enter name:");
                String tmpName = sc.next();
                ArrayList<Book> limitedBooks = customer.getBookbyName(tmpName);
                printBooks(limitedBooks);
            }
            else if (opt == 3) {
                customer.logOut();
                System.out.println("Customer Log Out.");
                break;
            }
            System.out.println("Anymore operation? Input 0 for quit, 1 for continue.");
            opt = sc.nextInt();
            if (opt == 0) break;
        }
    }


    /**
     *  this is the only public function.
     */
    public void runUser() {
    while(true) {
        logIn();
        if (user.getUserType() == UserType.Customer) {
            runCustomer();
        } else if (user.getUserType() == UserType.Admin) {
            runAdmin();
        } else {
            System.out.println("Message.Error" + Message.Error);
            System.out.println("login error,please try again");
        }
        System.out.println("Logged Out. 0 for Quit and other integers for Login.");
        int num = sc.nextInt();
        if(num==0){
            break;
        }
    }
    }

    public static void main(String[] a) {
        controller c = new controller();
        c.runUser();
    }













    private void printBooks(ArrayList<Book> books){
        System.out.println("Id"+"\t"+"Name" + " \t" + "Author"+"\t"+ "Price"+"\t"+ "Storage");
        for (int i = 0; i < books.size();i++) {
            System.out.println(books.get(i).getBookNumber() + "\t" +
                    books.get(i).getName() + "\t" +
                    books.get(i).getAuthor() + "\t" +
                    books.get(i).getPrice() + "\t" +
                    books.get(i).getStorage());
        }
    }
}
