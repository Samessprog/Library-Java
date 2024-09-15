package com.biblioteka;

abstract public class Record implements java.io.Serializable, Cloneable
{
    private int index;

    public Record()
    {}

    public Record(int index)
    {
        index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        Record clone = null;
        try
        {
            clone = (Record) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
        return clone;
    }

    public String toString()
    {
        return uniqueToString();
    }

    abstract String uniqueToString();

    abstract public boolean Check();
}
