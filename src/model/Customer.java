package model;

import util.DatabaseUtil;
import util.ModelStatus;
import util.UserType;

import java.util.ArrayList;

/**
 * Created by cyans on 2017/11/7.
 */
public class Customer extends User{
    public Customer(int id, String name, String password, int userType) {
        super(id, name, password, UserType.Customer);
    }

    public int purchase(int bookNum, int sum) {
        Book book = Book.getBookById(bookNum);
        return book.buyBook(sum, this.id);
    }

    public ArrayList<Book> getBookbyName(String name){
        ArrayList<Book> books = Book.listAllBook();
        ArrayList<Book> limitedBooks = new ArrayList<>();
        for(Book i : books) {
            if(i.getName().equals(name) && (i.getAvailable() == 1)){
                limitedBooks.add(i);
            }
        }
        if(limitedBooks.size()==0){
            System.out.println("No books here.");
        }
        return limitedBooks;
    }
}
