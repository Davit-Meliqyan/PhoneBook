package phonebook.models.contact;

import phonebook.models.ContactData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        System.out.println("Input phone number:");
        while (true) {
            phoneNumber = scanner.nextLine();
            if (isPhoneNumber(phoneNumber)) {
                break;
            }
            System.out.println("Illegal phone number, please enter a correct phone number.");
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

    public static PhoneNumber selectPhoneNumber(ArrayList<PhoneNumber> phoneNumbers) {
        if (phoneNumbers.size() == 1) {
            return phoneNumbers.get(0);
        } else if (phoneNumbers.size() > 1) {
            Map<Integer, PhoneNumber> searchMap = new HashMap<>();
            for (int i = 0; i < phoneNumbers.size(); i++) {
                searchMap.put(i + 1, phoneNumbers.get(i));
            }
            for (Map.Entry<Integer, PhoneNumber> m : searchMap.entrySet()) {
                System.out.print(m.getKey() + " " + m.getValue());
                System.out.println();
            }
            System.out.println("Enter the position number of the selected phone number:");
            Scanner sc = new Scanner(System.in);
            Integer pos = sc.nextInt();
            if (pos > 0 && pos < searchMap.size() + 1) {
                return searchMap.get(pos);
            }
        } else
            System.out.println("Phone number not found.");
        return null;
    }
}