package com.biblioteka;

import java.util.ArrayList;
import java.util.Date;

public class ListingDatabase extends Database{
    public ListingDatabase(String name) {
        super(name);
    }

    public ListingRecord castToListingRecord(Object o)
    {
        return (ListingRecord)o;
    }

    public ArrayList pickBook(int id)
    {
        ArrayList ret = new ArrayList();
        for (Object o: records
             ) {
            if(castToListingRecord(o).getBookID() == id) {
                try {
                    ret.add(castToListingRecord(o).clone());
                } catch (CloneNotSupportedException ex) {
                    continue;
                }
            }
        }
        return ret;
    }

    public ArrayList pickReader(int id)
    {
        ArrayList ret = new ArrayList();
        for (Object o: records
        ) {
            if(castToListingRecord(o).getReaderID() == id) {
                try {
                    ret.add(castToListingRecord(o).clone());
                } catch (CloneNotSupportedException ex) {
                    continue;
                }
            }
        }
        return ret;
    }

    public ArrayList pickOverdue()
    {
        ArrayList ret = new ArrayList();
        for (Object o: records
        ) {
            ListingRecord castO = castToListingRecord(o);
            if(!castO.isReturned() && castO.getReturnTo().compareTo(new Date()) < 0) {
                try {
                    ret.add(castToListingRecord(o).clone());
                } catch (CloneNotSupportedException ex) {
                    continue;
                }
            }
        }
        return ret;
    }

    @Override
    boolean isValid(Record rec) {
        return rec instanceof ListingRecord && rec.Check();
    }
}
