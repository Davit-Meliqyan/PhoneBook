package phonebook.models.contact;

import java.util.Scanner;

public class PhoneNumber {
    private String phoneNumber;
    private PhoneNumberType phoneNumberType;

    static Scanner scanner = new Scanner(System.in);

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
        if (phoneNumberType == null) {
            return phoneNumber;
        }
        return phoneNumber + " " + phoneNumberType;
    }

    public static PhoneNumber createPhoneNumber() {

        String phoneNumber = "";


        System.out.println("input phone number: ");

        while (true) {
            phoneNumber = scanner.nextLine();
            if (isPhoneNumber(phoneNumber)) {
                break;
            }
            System.out.println("Not legal phone number. Enter correct phone number! ");
        }
        PhoneNumberType phoneNumberType = PhoneNumberType.selectPhoneNumberType();

        return new PhoneNumber(phoneNumber, phoneNumberType);
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.equals("")) {
            return false;
        }
        if ("0123456789+".indexOf(phoneNumber.charAt(0)) == -1) {
            return false;
        }
        if (phoneNumber.length() > 32) {
            return false;
        }
        for (int i = 1; i < phoneNumber.length(); i++) {

            if ("0123456789".indexOf(phoneNumber.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}
