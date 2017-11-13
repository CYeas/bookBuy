package model;

import util.DatabaseUtil;

/**
 * Created by cyans on 2017/11/7.
 */
public class BookRecord {
    private int userId;
    private int bookId;
    private int sum;

    public BookRecord(int userId, int bookId, int sum) {
        this.userId = userId;
        this.bookId = bookId;
        this.sum = sum;
    }

    protected int createRecord() {
        String sql = "INSERT INTO Record(UserId,BookId,Sum) " +
                "VALUES(" + this.userId + "," + this.bookId + "," + this.sum + ");";
        return DatabaseUtil.runUpdateSql(sql);
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getSum() {
        return sum;
    }
}
