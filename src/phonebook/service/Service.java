package phonebook.service;

import phonebook.models.ContactData;
import phonebook.models.contact.Contact;
import phonebook.models.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    static Scanner scanner= new Scanner(System.in);

    public static void start() {




        String str = "";

        while (!str.equals("exit")) {
            System.out.println("enter command");
            str = scanner.nextLine();

            switch (str) {
                case "create":
                    create();
                    break;
                case "delete":
                    delete();
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("not legal command");
            }
        }

        System.out.println("GAME OVER");
    }

    public static void create() {

        ArrayList<ContactData> phoneBook = new ArrayList<>();

        String str = scanner.nextLine();

//        while (!str.equals("no")) {
//            System.out.println("do you want to input another phone number? ");
//            str = scanner.nextLine();
//            if (str.equals("yes")) {
//                System.out.println("input another phone number: ");
//                phoneNumber = scanner.nextLine();
//                phoneNumbers.add(phoneNumber);
//            }
//        }


        phoneBook.add(new ContactData());
        System.out.println("new contact is created");
    }

    public static void delete() {
        System.out.println("delete");
    }

    public static void help() {
        System.out.println("legal command list");

    }

}
