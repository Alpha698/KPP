public class FrenchAddressFactory implements AddressFactory {
    public Address createAddress() {
        return new FrenchAddress();
    }

    public PhoneNumber createPhoneNumber() {
        return new FrenchPhoneNumber();
    }

}