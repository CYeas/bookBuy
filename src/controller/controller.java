package controller;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Admin;
import model.Customer;
import model.User;
import util.Line;
import util.Message;
import util.UserType;

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
        String name, pwd;
        System.out.println("Step 1: Input your user name:");
        name = sc.nextLine();
        System.out.println("Step 2: Input your password:");
        pwd = sc.nextLine();
        user = User.login(name, pwd);
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
            name = sc.nextLine();
            System.out.println("Step 2: Input the author of the book.");
            author = sc.nextLine();
            System.out.println("Step 3: Input the price of the book.");
            price = sc.nextFloat();
            System.out.println("Step 4: Input the availability of the book.");
            available = sc.nextInt();
            System.out.println("Step 5: Input the number of storage of the book.");
            storage = sc.nextInt();

            admin.createBook(name, author, price, available, storage);
            System.out.println("Message.Success" + Message.Success );
            Line.seperateLine();
            System.out.println("Continue creating book? 0 for continue and 1 for quit.");
            boolean temp = sc.nextBoolean();
            if (temp) break;
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
               id = admin.getAllBook().get(bookNum).getBookNumber();
               admin.deleteBook(id);
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
        for (int i = customer.getAllBook().size(); i >= 0; i--) {
            System.out.println(customer.getAllBook().get(i).getBookNumber() +
                    customer.getAllBook().get(i).getName() +
                    customer.getAllBook().get(i).getAuthor() +
                    customer.getAllBook().get(i).getPrice() +
                    customer.getAllBook().get(i).getStorage());
        }
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
            break;
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
        customer.purchase(id, num);
        System.out.println("Message.Success: " + Message.Success);
    }

    private void runCustomer() {
        int opt;
        System.out.println("Welcome to Customer's operation page.");
        while (true) {
            System.out.println("You have two choices bellow.");
            System.out.println("1. Purchase some book.");
            System.out.println("2. Log out.");
            opt = sc.nextInt();
            if (opt == 1) {
                purchaseBook(); break;
            } else if (opt == 2) {
                customer.logOut();
                System.out.println("Customer Log Out.");
                break;
            }
        }
    }


    /**
     *  this is the only public function.
     */
    public void runUser() {
        logIn();
        if (user.getUserType() == UserType.Customer) {
            runCustomer();
        }
        else if (user.getUserType() == UserType.Admin) {
            runAdmin();
        }
        else {
            System.out.println("Message.Error" + Message.Error);
            System.out.println("Log in failed, please check your user name and password.");
        }
    }

    public static void main(String[] a) {
        controller c = new controller();
        c.runUser();
    }

}
