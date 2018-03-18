public class USAddressFactory implements AddressFactory {

    public Address createAddress(){
        return new USAddress();
    }

    public PhoneNumber createPhoneNumber(){
        return new USPhoneNumber();
    }

}
