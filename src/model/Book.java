package model;

import util.DatabaseUtil;
import util.ModelStatus;

import java.util.ArrayList;

/**
 * Created by cyans on 2017/11/7.
 */
public class Book {
    private int bookNumber;
    private String name;
    private String author;
    private float price;
    private int storage;
    private int available;

    public Book(int bookNumber, String name, String author, float price, int storage, int available) {
        this.bookNumber = bookNumber;
        this.name = name;
        this.author = author;
        this.price = price;
        this.storage = storage;
        this.available = available;
    }


    protected static ArrayList<Book> listAllBook() {

    }

    protected static Book getBook(int id) {

    }

    protected int createBook(int id) {

    }

    protected int destoryBook(int id) {

    }

    protected int buyBook(int sum) {
        if(sum <= storage) {
            this.storage = storage - sum;
        }
    }

    protected int setNotAvailable() {
        this.available = 0;
        return this.save();
    }

    protected int save() {
        String sql = "UPDATE Book" +
                "SET Name = " + this.name + "," +
                "Author = " + this.author + "," +
                "Price = " + this.price + "," +
                "Available = " + this.available + "," +
                "Storage = " + this.storage +
                "WHERE id = " + this.bookNumber;
        return DatabaseUtil.runUpdateSql(sql);
    }

    private void destoryThis() {
        this.bookNumber = ModelStatus.NotAvailable;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    public int getStorage() {
        return storage;
    }
}
