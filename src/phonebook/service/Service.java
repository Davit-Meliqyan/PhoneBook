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
        while (!str.toLowerCase().equals("exit")) {
            System.out.println("Enter a command:");
            System.out.println("Type \"help\" to see the commands list");
            str = scanner.nextLine();
            switch (str.toLowerCase()) {
                case "create":
                case "1":
                    System.out.println();
                    create(phoneBook);
                    break;
                case "delete":
                case "2":
                    delete();
                    break;
                case "print":
                case "show":
                case "3":
                    print(phoneBook);
                    break;
                case "help":
                case "4":
                    help();
                    break;
                case "exit":
                case "0":
                    break;
                default:
                    System.out.println("Not a legal command");
            }
            System.out.println();
        }
        System.out.println("Ending application");
    }

    public static void create(ArrayList<ContactData> phoneBook) {
        phoneBook.add(ContactData.createContactData());
        System.out.println("New contact is created");
    }

    public static void delete() {
        System.out.println("delete");
    }

    public static void help() {
        System.out.println("Command list:");
        System.out.println("1, or create: Create a contact");
        System.out.println("2, or delete: Delete a contact");
        System.out.println("3, or print/show: Show contact info");
        System.out.println("0, or exit: Exit application");
    }

    public static void print(ArrayList<ContactData> phoneBook) {

        for (ContactData c : phoneBook) {
            System.out.println(c);
        }
    }
}
