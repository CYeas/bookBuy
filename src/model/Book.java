package model;

import util.DatabaseUtil;
import util.Message;
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

    public int getAvailable() {
        return available;
    }

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
        return DatabaseUtil.getBook(ModelStatus.allBookCode);
    }

    protected static Book getBookById(int id) {
        return DatabaseUtil.getBook(id).get(0);
    }

    protected int createBook() {
        String sql = "INSERT INTO Book(Name,Author,Price,Available,Storage) " +
                "VALUES('" + this.name + "','" + this.author + "'," + this.price + "," + this.available +"," + this.storage+ ");";
        return DatabaseUtil.runUpdateSql(sql);
    }

    protected int deleteBook() {
        String sql = "DELETE FROM Book WHERE rowid=" + this.bookNumber + ";";
        return DatabaseUtil.runUpdateSql(sql);
    }

    protected int buyBook(int sum, int UserId) {
        int status = 0;
        if(sum <= storage && sum >= 0) {
            this.storage = storage - sum;
            BookRecord bookRecord = new BookRecord(UserId, this.bookNumber, sum);
            status += bookRecord.createRecord();
            status += this.save();
        } else {
          status = Message.BuyError;
        }
        return status == Message.Success ? Message.Success : Message.BuyError;
    }

    protected int setNotAvailable() {
        this.available = 0;
        return this.save();
    }

    protected int save() {
        String sql = "UPDATE Book " +
                "SET Name = '" + this.name + "'," +
                "Author = '" + this.author + "'," +
                "Price = " + this.price + "," +
                "Available = " + this.available + "," +
                "Storage = " + this.storage +
                " WHERE rowid = " + this.bookNumber;
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
