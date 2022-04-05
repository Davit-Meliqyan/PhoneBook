package phonebook.models.contact;

import java.util.Scanner;

public enum PhoneNumberType {
    HOME,
    MOBILE,
    WORK,
    SCHOOL;

    public static PhoneNumberType selectPhoneNumberType() {
        System.out.println("Select phone number type:");
        System.out.println("0 - Not selected");
        System.out.println("1 - Home");
        System.out.println("2 - Mobile");
        System.out.println("3 - Work");
        System.out.println("4 - School");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        switch (str.toLowerCase()) {
            case "0":
                return null;
            case "home":
            case "1":
                return PhoneNumberType.HOME;
            case "mobile":
            case "2":
                return PhoneNumberType.MOBILE;
            case "work":
            case "3":
                return PhoneNumberType.WORK;
            case "school":
            case "4":
                return PhoneNumberType.SCHOOL;
            default:
                System.out.println("Enter a correct phone number type.");
                return selectPhoneNumberType();
        }
    }
}