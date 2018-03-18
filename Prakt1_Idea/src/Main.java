public class Main{
public static void main(String[] args) {

        System.out.println ("Пример работы шаблона проектирования Abstract Factory...");

        System.out.println();
        System.out.println("Создание АМЕРИКАНСКОГО адреса и телефона:");
        AddressFactory USAddressFactory = new USAddressFactory();
        Address usAddress = USAddressFactory.createAddress();
        PhoneNumber usPhone = USAddressFactory.createPhoneNumber();
        usAddress.setStreet("142 Lois Lane");
        usAddress.setCity("Metropolis");
        usAddress.setRegion("WY");
        usAddress.setPostalCode("54321"); usPhone.setPhoneNumber("7039214722"); System.out.println("U.S. адрес:"); System.out.println(usAddress.getFullAddress() ) ; System.out.println("U.S. тел. номер:"); System.out.println(usPhone.getPhoneNumber() ) ; System.out.println();
        System.out.println("Создание ФРАНЦУЗСКОГО адреса и телефона:");
        AddressFactory frenchAddressFactory = new FrenchAddressFactory();
        Address frenchAddress = frenchAddressFactory.createAddress();
        PhoneNumber frenchPhone = frenchAddressFactory.createPhoneNumber();
        frenchAddress.setStreet("21 Rue Victor Hugo");
        frenchAddress.setCity("Paris");
        frenchAddress.setPostalCode("40493");
        frenchPhone.setPhoneNumber("011324290");
        System.out.println("Французский адрес:");
        System.out.println(frenchAddress.getFullAddress() ) ;
        System.out.println("Французский тел. номер:");
        System.out.println(frenchPhone.getPhoneNumber() ) ;
        }

}