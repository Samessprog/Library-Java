package com.biblioteka;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class ListingRecord extends Record{
    private int bookID;
    private int readerID;
    private Date borrowed;
    private Date returnTo;
    private boolean returned;

    public int getBookID() {
        return bookID;
    }

    public int getReaderID() {
        return readerID;
    }

    public Date getBorrowed() {
        return borrowed;
    }

    public Date getReturnTo() {
        return returnTo;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public ListingRecord(int bookID, int readerID, Date borrowed, int days) {
        super();
        this.bookID = bookID;
        this.readerID = readerID;
        this.borrowed = borrowed;
        Calendar c = Calendar.getInstance();
        c.setTime(borrowed);
        c.add(Calendar.DATE, days);
        this.returnTo = c.getTime();
        this.returned = false;
    }

    public ListingRecord() {
        super();
    }

    @Override
    String uniqueToString() {
        return getIndex() + " | " + "\t\t\t\t\t"
                + bookID + " | "+ "\t\t\t\t\t"
                + readerID + " | "+ "\t\t\t\t\t"
                + borrowed.toString() + " | " + "\t\t\t\t\t"
                + returnTo.toString() + " | " + "\t\t\t\t\t"
                + (returned ? "Oddane" : "Nieoddane");
    }

    @Override
    public boolean Check() {
        return borrowed != null;
    }

    public static class ListingDateComparator implements Comparator<ListingRecord>
    {
        @Override
        public int compare(ListingRecord o1, ListingRecord o2) {
            return o1.borrowed.compareTo(o2.borrowed);
        }
    }


}
