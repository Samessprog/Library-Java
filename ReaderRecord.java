package com.biblioteka;

import java.util.Comparator;

public class ReaderRecord extends Record{
    private String name;
    private String surname;
    private int age;
    private boolean block;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public ReaderRecord(String name, String surname, int age, boolean block) {
        super();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.block = block;
    }

    public ReaderRecord() {
        super();
    }

    @Override
    String uniqueToString() {
        return getIndex() + " | " + "\t\t\t\t\t"
                + name + " | " + "\t\t\t\t\t"
                + surname + " | "+ "\t\t\t\t\t"
                + age + " | "+ "\t\t\t\t\t"
                + (block ? "Zablokowany" : "-");
    }

    @Override
    public boolean Check() {
        return age > 0 && age < 99;
    }

    public static class ReaderNameComparator implements Comparator<ReaderRecord>
    {
        @Override
        public int compare(ReaderRecord o1, ReaderRecord o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public static class ReaderSurnameComparator implements Comparator<ReaderRecord>
    {
        @Override
        public int compare(ReaderRecord o1, ReaderRecord o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }
    public static class ReaderAgeComparator implements Comparator<ReaderRecord>
    {
        @Override
        public int compare(ReaderRecord o1, ReaderRecord o2) {
            return o1.getAge() - (o2.getAge());
        }
    }
}
