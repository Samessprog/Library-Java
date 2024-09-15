package com.biblioteka;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract public class Database implements java.io.Serializable{
    protected ArrayList records;
    private int dataCount;
    private int lastIndex;
    protected String fileName;

    Database()
    {
        records = new ArrayList();
        dataCount = 0;
        lastIndex = 0;
    }

    Database(String name)
    {
        fileName = name;
        File f = new File(name);
        if(f.exists() && !f.isDirectory()) {
            loadDatabase();
        }
        else
        {
            records = new ArrayList();
            dataCount = 0;
            lastIndex = 0;
        }

    }

    public void loadDatabase()
    {
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            records = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            dataCount = records.size();
            lastIndex = dataCount == 0 ? 0 : ((Record)records.get(dataCount-1)).getIndex()+1;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException cnfe)
        {
            System.out.println("Database not found.");
            cnfe.printStackTrace();
            return;
        }
    }

    public void saveDatabase()
    {
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(records);
            oos.close();
            fos.close();
            System.out.println("Serialzation Done!!");
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    public void addRecord(Record rec)
    {
        if(rec != null)
        {
            if(isValid(rec))
            {

                try
                {
                    rec.setIndex(lastIndex++);
                    records.add(rec.clone());
                    dataCount++;
                }
                catch (CloneNotSupportedException ex)
                {
                    return;
                }

            }
        }
    }

    public void editRecord(int id, Record rec)
    {
        if(records.stream().filter((Object o) -> ((Record)o).getIndex()==id).findFirst().get() != null)
        {
            try
            {
                rec.setIndex(((Record)records.get(id)).getIndex());
                records.set(id, rec.clone());
            }
            catch (CloneNotSupportedException ex)
            {
                return;
            }
        }

    }

    public Record cloneRecord(int id)
    {
        try {
            return (Record) (((Record) (records.stream().filter(
                    (Object o) -> ((Record)o).getIndex()==id).findFirst().get())).clone());
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

    public Record getRecord(int id)
    {
        return cloneRecord(id);
    }

    public void removeRecord(int id)
    {
        for(Object o : records)
        {
            if (((Record)o).getIndex() == id)
            {
                records.remove(o);
                dataCount--;
                return;
            }
        }
    }

    public ArrayList deepCopy()
    {
        ArrayList recordsClone = new ArrayList();
        Iterator<Record> iterator = records.iterator();
        try
        {
            while (iterator.hasNext())
            {
                recordsClone.add((Record) iterator.next().clone());
            }
            return recordsClone;
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Coś poszło nie tak");
            return null;
        }
    }

    abstract boolean isValid(Record rec);


}
