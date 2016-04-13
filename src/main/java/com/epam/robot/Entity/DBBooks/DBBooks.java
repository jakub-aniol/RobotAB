package com.epam.robot.Entity.DBBooks;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jakub on 08.04.16.
 */


@Entity
@Table(name = "DBBOOKS")
public class DBBooks implements java.io.Serializable {
    private static final long serialVersionUID = -3960396761249882840L;
    private int bookId;
    private String libraryName;
    private String bookTitle;
    private String bookType;
    private String keyWords;
    private Date createdDate;

    public DBBooks() {
    }

    public DBBooks(String libraryName, String bookTitle, String bookType, String keyWords) {
        this.bookId = getBookId(); //skoro jest generated value to może nie dwać tego w kosntruktorze?
        this.libraryName = libraryName;
        this.bookTitle = bookTitle;
        this.bookType = bookType;
        this.keyWords = keyWords.toString();
        this.createdDate = getCreatedDate();
    }

   /* public DBBooks(Book book) {
        //this.bookId = ??,; //skoro jest generated value to może nie dwać tego w kosntruktorze?
        this.libraryName = book.getLibrary();
        this.bookTitle = book.getTitle();
        this.bookType = book.getType();
        this.keyWords = book.getKeyWords().toString();
        this.createdDate = getCreatedDate();
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", unique = true, nullable = false, precision = 5, scale = 0)
    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Column(name = "LIBRARY_NAME", nullable = true, length = 30)
    public String getLibraryName() {
        return this.libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @Column(name = "BOOK_TITLE", nullable = true, length = 1000)
    public String getBookTitle() {
        return this.bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Column(name = "BOOK_TYPE", nullable = true)
    public String getBookType() {
        return this.bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Column(name = "KEY_WORDS", nullable = true, length = 1000)
    public String getKeyWords() {
        return this.keyWords.toString();
    }

    public void setKeyWords(ArrayList<String> keyWords) {
        this.keyWords = keyWords.toString();
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE", nullable = true, length = 7)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
