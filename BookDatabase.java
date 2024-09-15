package com.biblioteka;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BookDatabase extends Database{
    public BookDatabase(String name) {
        super(name);
    }

    public BookRecord castToBookRecord(Object o)
    {
        return (BookRecord)o;
    }

    public ArrayList lookupTitle(String title)
    {
        ArrayList ret = new ArrayList();
        for(Object o : records)
        {
            if(castToBookRecord(o).getTitle().equals(title)) ret.add(o);
        }
        return ret;
    }

    public ArrayList lookupAuthor(String author)
    {
        ArrayList ret = new ArrayList();
        for(Object o : records)
        {
            if(castToBookRecord(o).getAuthor().equals(author)) ret.add(o);
        }
        return ret;
    }

    public void markBookAsBorrowed(int id)
    {
        BookRecord r = castToBookRecord(getRecord(id));
        r.setState(true);
        editRecord(id, r);
    }

    @Override
    boolean isValid(Record rec) {
        return rec instanceof BookRecord && rec.Check();
    }
}
