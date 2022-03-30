package phonebook.models.contact;

import java.util.Scanner;

public class PhoneNumber {
    private String phoneNumber;
    private PhoneNumberType phoneNumberType;

    public PhoneNumber(String phoneNumber, PhoneNumberType phoneNumberType) {
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    @Override
    public String toString() {
        return phoneNumber + "\n" + phoneNumberType;
    }

    public static PhoneNumber createPhoneNumber() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("input phone number: ");
        String phoneNumber = scanner.nextLine();
        PhoneNumberType phoneNumberType = PhoneNumberType.selectPhoneNumberType();

        return new PhoneNumber(phoneNumber, phoneNumberType);
    }
}
