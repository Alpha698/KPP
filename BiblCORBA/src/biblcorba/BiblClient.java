package biblcorba;

import BiblServiceModule.*;
import java.util.Date;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class BiblClient {

    static BiblService BSImpl;

    public static void main(String[] args) {
        try {
            java.util.Properties p = new java.util.Properties();
            p.setProperty("com.sun.CORBA.codeset.charsets", "0x05010001, 0x00010109");    // UTF-8, UTF-16
            p.setProperty("com.sun.CORBA.codeset.wcharsets", "0x00010109, 0x05010001");    // UTF-16, UTF-8
            ORB orb = org.omg.CORBA_2_3.ORB.init(args, p);
           

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "BiblService";
            BSImpl = BiblServiceHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Модульная работа студента  ......");
            System.out.println("Регистрация книги Зов Ктулху c ISBN = 12346");
            BSImpl.addNewBook("Зов Ктулху", "12346");
            System.out.println("Регистрация книги Драконья сага c ISBN = 23456");
            BSImpl.addNewBook("Драконья сага!", "23456");
            System.out.println("Регистрация книги Черный коготь c ISBN = 34567");
            BSImpl.addNewBook("Черный коготь", "34567");
            System.out.println("Регистрация книги Коты воители c ISBN = 45678");
            BSImpl.addNewBook("Коты воители", "45678");

            System.out.println("Регистрация читателя Богдан c билетом = 1");
            BSImpl.addNewReader("1", "Богдан");
            System.out.println("Регистрация читателя Татьяна c билетом = 2");
            BSImpl.addNewReader("2", "Татьяна");
            System.out.println("Регистрация читателя Владислав c билетом = 3");
            BSImpl.addNewReader("3", "Владислав");

            System.out.println("Выдача читателю Богдан книги Зов Ктулху");
            BSImpl.takeBook("Богдан", "Зов Ктулху");
            System.out.println("Выдача читателю Иванову книги Драконья сага!");
            BSImpl.takeBook("Владислав", "Драконья сага!");
            System.out.println("Выдача читателю Петренко книги Черный коготь");
            BSImpl.takeBook("Татьяна", "Черный коготь");
            System.out.println("Читатель Иванов возвращает книгу Коты воители");
            BSImpl.returnBook("Владислав", "Коты воители");
            System.out.println(" ***********************");
            System.out.println(" Книги читателя Богдан: ");
            System.out.println(BSImpl.getReaderInfo("Богдан"));
            System.out.println(" ***********************");
            System.out.println(" Все Книги библиотеки, выданные читателям");
            System.out.println(BSImpl.getFullInfo());

            System.out.print("Работа выполнена "+new Date());
        } catch (Exception e) {
            System.out.println(" Error " + e);
            e.printStackTrace(System.err);
        }
    }

}
