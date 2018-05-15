package biblcorba;

import BiblServiceModule.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import org.omg.CORBA.*;

public class BiblServiceImpl extends BiblServicePOA {

    private Hashtable hashBook = new Hashtable();   // хэш-таблица для хранения книг
    private Hashtable hashReader = new Hashtable();  // хэш-таблица для хранения читателей
    private Hashtable hashBibl = new Hashtable();    // хэш-таблица для хранения выданных книг

    private ORB orb;
    // инициализация брокера
    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    // регистрация новой книги 
    @Override
    public void addNewBook(String newBookName, String newISBN) {
        hashBook.put(newBookName, newISBN);
    }

    // регистрация нового читателя
    @Override
    public void addNewReader(String bilet, String Name) {
        hashReader.put(Name, bilet);
    }

    // выдача книги читателю
    @Override
    public void takeBook(String reader, String book) {
        String bilet = (String) hashReader.get(reader);
        String ISBN = (String) hashBook.get(book);
        if (bilet != null || ISBN != null) {
            String zapis = reader + " " + book;
            hashBibl.put(zapis, reader + " " + book + " " + new Date());
        }
    }

    // возврат книги читателем
    @Override
    public void returnBook(String reader, String book) {
        String bilet = (String) hashReader.get(reader);
        String ISBN = (String) hashBook.get(book);
        if (bilet != null || ISBN != null) {
            String zapis = reader +" "+ book;
            hashBibl.remove(zapis);
        }
    }

    // получение списка книг по читателю
    @Override
    public String getReaderInfo(String searchName) {
        Enumeration names;
        String key;
        String FindResult = "";
        names = hashBibl.keys();
        while (names.hasMoreElements()) {
            key = (String) names.nextElement();
            String[] array = key.split(" ");
            String value = (String) hashBibl.get(key);
            if (searchName.equals(array[0])) {
                FindResult = FindResult + (value + "\n");
            }
        }
        return FindResult;
    }
    // получение полного списка книг
    @Override
    public String getFullInfo() {
        Enumeration names;
        String key;
        String FindResult = "";
        names = hashBibl.keys();
        while (names.hasMoreElements()) {
            key = (String) names.nextElement();
            String ff = (String) hashBibl.get(key);
            FindResult = FindResult + (ff + "\n");
        }
        return FindResult;
    }

}
