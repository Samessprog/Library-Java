package com.biblioteka;


import java.awt.print.Book;
import java.util.Comparator;

public class BookRecord extends Record{
    private String title;
    private String author;
    private TypUtworu type;
    private int year;
    private boolean state;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public TypUtworu getType() {
        return type;
    }

    public void setType(TypUtworu type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public BookRecord() {
        super();
    }

    @Override
    String uniqueToString() {
        return getIndex() + " | " + "\t\t\t\t\t"
                + title + " | " + "\t\t\t\t\t"
                + author + " | " + "\t\t\t\t\t"
                + year + " | " + "\t\t\t\t\t"
                + (state ? "Dostępne" : "Niedostępne");
    }

    @Override
    public boolean Check() {
        return year > 1450;
    }

    public BookRecord(String title, String author, TypUtworu type, int year, boolean state) {
        super();
        this.title = title;
        this.author = author;
        this.type = type;
        this.year = year;
        this.state = state;
    }

    public static class BookTitleComparator implements Comparator<BookRecord> {

        @Override
        public int compare(BookRecord o1, BookRecord o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    public static class BookAuthorComparator implements Comparator<BookRecord> {

        @Override
        public int compare(BookRecord o1, BookRecord o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    }


}
