package model;

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

    public int buyBook(int sum) {
        if(sum <= storage) {
            this.storage = storage - sum;
        }
    }

    public static ArrayList<Book> listAllBook() {

    }

    public int setNotAvailable() {
        this.available = 0;
        this.save();
    }

    protected int save() {

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
