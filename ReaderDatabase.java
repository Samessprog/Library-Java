package com.biblioteka;

public class ReaderDatabase extends Database {
    public ReaderDatabase(String name) {
        super(name);
    }

    public void blockReader(int id)
    {
        ReaderRecord r = (ReaderRecord) getRecord(id);
        r.setBlock(true);
        editRecord(id, r);
    }

    @Override
    boolean isValid(Record rec) {
        return rec instanceof ReaderRecord && rec.Check();
    }
}
