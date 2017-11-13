package model;

import util.UserType;

/**
 * Created by cyans on 2017/11/7.
 */
public class Customer extends User{
    public Customer(int id, String name, String password, int userType) {
        super(id, name, password, UserType.Customer);
    }

    public int purchase(int bookNum, int sum) {
        Book book = Book.getBook(bookNum);
        return book.buyBook(sum, this.id);
    }
}
