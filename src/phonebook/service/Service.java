package phonebook.service;

import phonebook.models.ContactData;
import phonebook.models.contact.Contact;
import phonebook.models.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    static Scanner scanner = new Scanner(System.in);


    public static void start() {

        ArrayList<ContactData> phoneBook = new ArrayList<>();

        String str = "";

        while (!str.equals("exit")) {

            System.out.println("enter command");
            str = scanner.nextLine();

            switch (str) {
                case "create":
                    System.out.println();
                    create(phoneBook);
                    break;
                case "delete":
                    delete();
                    break;
                case "help":
                    help();
                    break;
                case "print":
                case "show":
                    print(phoneBook);
                    break;
                case "search":
                    print(search(phoneBook));
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("not legal command");
            }
            System.out.println();
        }

        System.out.println("GAME OVER");
    }

    public static void create(ArrayList<ContactData> phoneBook) {

        phoneBook.add(ContactData.createContactData());
        System.out.println("new contact is created");
    }

    public static ArrayList<ContactData> search( ArrayList<ContactData> phoneBook) {
        System.out.println("Input required user: ");
        String str = scanner.nextLine();
        ArrayList<ContactData> searchResult = new ArrayList<>();
        for (ContactData c : phoneBook) {
            User user = c.getUser();
            if (str.equals(user.getName()) || str.equals(user.getLastName())) {
                searchResult.add(c);
            }
        }
        return searchResult;
    }

    public static void delete() {
        System.out.println("delete");
    }

    public static void help() {
        System.out.println("legal command list");

    }

    public static void print(ArrayList<ContactData> phoneBook) {
        for (ContactData c : phoneBook) {
            System.out.println(c);
        }
        System.out.println();
    }

}
