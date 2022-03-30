package phonebook.models.contact;

import java.util.Scanner;

public enum PhoneNumberType {
    HOME,
    MOBILE,
    WORK,
    SCHOOL;

    public static PhoneNumberType selectPhoneNumberType() {

        System.out.println("Select phone number type: ");
        System.out.println("0 - not selected");
        System.out.println("1 - home");
        System.out.println("2 - mobile");
        System.out.println("3 - work");
        System.out.println("4 - school");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        switch (str) {
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
                System.out.println("Enter correct phone number type! ");
                return selectPhoneNumberType();
        }
    }
}
