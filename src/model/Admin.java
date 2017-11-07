package model;

import util.UserType;

/**
 * Created by cyans on 2017/11/7.
 */
public class Admin extends User{
    public Admin(int id, String name, String password, int userType) {
        super(id, name, password, UserType.Admin);
    }

    public int createBook(String name, String author, float price, int available, int storage) {
        Book book = new Book( 0 ,name, author, price, storage,available);
        return book.createBook();
    }

    public int deleteBook(int bookNum) {
        Book book = Book.getBook(bookNum);
        return book.deleteBook();
    }
}
