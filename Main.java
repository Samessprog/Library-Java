package com.biblioteka;

import java.util.*;

public class Main {
    static BookDatabase books = new BookDatabase("Books.ser");
    static ReaderDatabase readers = new ReaderDatabase("Readers.ser");
    static ListingDatabase listings = new ListingDatabase("Listings.ser");
    static Scanner scan = new Scanner(System.in);
    static BookRecord book = new BookRecord();
    static ReaderRecord reader = new ReaderRecord();

    public static int scanInt()
    {
        int i = scan.nextInt();
        scan.nextLine();
        return i;
    }

    public static String stripScan()
    {
        return scan.nextLine().trim();
    }

    public static void makeHeader(String[] strings)
    {
        String s = "";
        for(Object o : strings)
        {
            s += o.toString() + " | " + "\t\t\t\t\t";
        }
        System.out.println(s);
    }

    public static void makeBookHeader()
    {
        makeHeader(new String[]{"ID","Tytuł","Autor","Data wydania","Typ","Stan"});
    }

    public static void makeReaderHeader()
    {
        makeHeader(new String[]{"ID","Imie","Nazwisko","Wiek","Stan"});
    }

    public static void makeListingHeader()
    {
        makeHeader(new String[]{"ID","ID książki","ID czytelnika","Data wypożyczenia","Data oddania","Stan"});
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("----------------------------------\n");
    }

    public static int askForID()
    {
        System.out.print("Podaj ID: ");
        return scanInt();
    }

    public static void showArrayList(ArrayList arrayList, int header)
    {
        switch (header)
        {
            case 1:
                makeBookHeader();
                break;
            case 2:
                makeReaderHeader();
                break;
            case 3:
                makeListingHeader();
                break;
        }
        for (Object o : arrayList)
        {
            System.out.println(o.toString());
        }
    }

    public static void showDatabaseCopy(int id, Comparator cmp)
    {
        Database db = null;
        switch (id)
        {
            case 1: db = books; makeBookHeader(); break;
            case 2: db = readers; makeReaderHeader(); break;
            case 3: db = listings; makeListingHeader(); break;
            default: return;
        }
        ArrayList toShow = db.deepCopy();
        Collections.sort(toShow, cmp);
        for (Object o: toShow)
        {
            System.out.println(o.toString());
        }
    }

    public static void editBook()
    {
        int id = askForID();
        System.out.println("Podaj tytuł: ");
        book.setTitle(stripScan());
        System.out.println("Podaj autora: ");
        book.setAuthor(stripScan());
        System.out.println("Podaj typ (proza-0, wiersz-1, dramat-2): ");
        book.setType(TypUtworu.values()[scanInt()]);
        System.out.println("Podaj rok wydania: ");
        book.setYear(scanInt());
        System.out.println("Podaj stan: ");
        book.setState(scanInt() > 0 ? true : false);
        books.editRecord(id, book);
    }

    public static void addBook()
    {
        System.out.println("Podaj tytuł: ");
        book.setTitle(stripScan());
        System.out.println("Podaj autora: ");
        book.setAuthor(stripScan());
        //System.out.println("Podaj typ (proza-0, wiersz-1, dramat-2): ");
        //book.setType(TypUtworu.values()[scanInt()]);
        book.setType(TypUtworu.Proza);
        System.out.println("Podaj rok wydania: ");
        book.setYear(scanInt());
        book.setState(true);
        books.addRecord(book);
    }

    public static void editReader()
    {
        int id = askForID();
        System.out.println("Podaj imie: ");
        reader.setName(stripScan());
        System.out.println("Podaj nazwisko: ");
        reader.setSurname(stripScan());
        System.out.println("Podaj wiek: ");
        reader.setAge(scanInt());
        System.out.println("Podaj stan: ");
        reader.setBlock(scanInt() > 0 ? true : false);
        readers.editRecord(id, reader);
    }

    public static void addReader()
    {
        System.out.println("Podaj imie: ");
        reader.setName(stripScan());
        System.out.println("Podaj nazwisko: ");
        reader.setSurname(stripScan());
        System.out.println("Podaj wiek: ");
        reader.setAge(scanInt());
        reader.setBlock(false);
        readers.addRecord(reader);
    }

    public static void blockReader()
    {
        readers.blockReader(askForID());
    }

    public static void removeBook()
    {
        int id = askForID();
        books.removeRecord(id);
    }

    public static void findBook()
    {
        System.out.println("Po tytule - 1\n" +
                "Po autorze - 2");
        int pick = scanInt();
        ArrayList records;
        switch (pick)
        {
            case 1:
            {
                System.out.println("Podaj tytul: ");
                records = books.lookupTitle(stripScan());
                break;
            }
            case 2:
            {
                System.out.println("Podaj autora: ");
                records = books.lookupAuthor(stripScan());
                break;
            }
            default: return;
        }
        makeBookHeader();
        for(Object o : records)
        {
            System.out.println(o.toString());
        }
    }

    public static void showAllBooks()
    {
        System.out.println("Po tytule - 1\n" +
                "Po autorze - 2");
        Comparator cmp;
        int pick = scanInt();
        switch (pick)
        {
            case 1: cmp = new BookRecord.BookTitleComparator(); break;
            case 2: cmp = new BookRecord.BookAuthorComparator(); break;
            default: return;
        }
        showDatabaseCopy(1, cmp);
    }

    public static void showAllReaders()
    {
        System.out.println("Po imieniu - 1\n" +
                "Po nazwisku - 2\n" +
                "Po wieku - 3");
        Comparator cmp;
        int pick = scanInt();
        switch (pick)
        {
            case 1: cmp = new ReaderRecord.ReaderNameComparator(); break;
            case 2: cmp = new ReaderRecord.ReaderSurnameComparator(); break;
            case 3: cmp = new ReaderRecord.ReaderAgeComparator(); break;
            default: return;
        }
        showDatabaseCopy(2, cmp);
    }

    public static void showAllListings()
    {
        Comparator cmp = new ListingRecord.ListingDateComparator();
        showDatabaseCopy(3, cmp);
    }

    public static void borrowBook()
    {
        int bookID = askForID();
        int readerID = askForID();
        System.out.println("Podaj liczbe dni: ");
        int days = scanInt();
        if(books.getRecord(bookID) != null &&
        readers.getRecord(readerID) != null &&
        days > 0)
        {
            if(((BookRecord)books.getRecord(bookID)).getState() &&
                    !((ReaderRecord)readers.getRecord(readerID)).isBlock())
            {
                listings.addRecord(new ListingRecord(bookID, readerID, new Date(), days));
                books.markBookAsBorrowed(bookID);
            }
        }
    }

    public static void bookProfile()
    {
        int bookID = askForID();
        showArrayList(listings.pickBook(bookID), 1);
    }

    public static void readerProfile()
    {
        int readerID = askForID();
        showArrayList(listings.pickReader(readerID), 2);
    }

    public static void overdue()
    {
        showArrayList(listings.pickOverdue(), 3);
    }

    public static void exit()
    {
        books.saveDatabase();
        readers.saveDatabase();
        listings.saveDatabase();
        System.exit(0);
    }

    public static void main(String[] args) {
        while (true)
        {
            clearScreen();
            System.out.println("Dodaj ksiazke - 1\n" +
                    "Edytuj ksiazke - 2\n" +
                    "Usun ksiazke - 3\n" +
                    "Szukaj ksiazki - 4\n" +
                    "Wszystkie ksiazk - 5\n" +
                    "Dodaj czytelnika - 6\n" +
                    "Edytuj czytelnika - 7\n" +
                    "Zablokuj czytelnika - 8\n" +
                    "Wszyscy czytelnicy - 9\n" +
                    "Wypozycz ksiazke - 10\n" +
                    "Wypozyczone ksiazki - 11\n" +
                    "Profil ksiazki - 12\n" +
                    "Profil czytelnika - 13\n" +
                    "Przeterminowane zwroty - 14\n" +
                    "Wyjdz - 0\n" +
                    "Wybierz: "
            );
            int pick;
            try
            {
                pick = scanInt();
            }
            catch (InputMismatchException ex)
            {
                continue;
            }
            clearScreen();
            switch (pick) {
                case 0:
                    exit();
                case 1:
                    addBook();
                    break;
                case 2:
                    editBook();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    findBook();
                    break;
                case 5:
                    showAllBooks();
                    break;
                case 6:
                    addReader();
                    break;
                case 7:
                    editReader();
                    break;
                case 8:
                    blockReader();
                    break;
                case 9:
                    showAllReaders();
                    break;
                case 10:
                    borrowBook();
                    break;
                case 11:
                    showAllListings();
                    break;
                case 12:
                    bookProfile();
                    break;
                case 13:
                    readerProfile();
                    break;
                case 14:
                    overdue();
                    break;
                default:
                    continue;
            }
            System.out.println("Wciśnij enter, aby wrócić do menu");
            scan.nextLine();
        }

    }
}
