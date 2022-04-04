package phonebook.service;

import phonebook.models.ContactData;
import phonebook.models.contact.Contact;
import phonebook.models.contact.PhoneNumber;
import phonebook.models.user.User;

import java.util.*;

public class Service {

    static Scanner scanner = new Scanner(System.in);

    public static void start() {
        ArrayList<ContactData> phoneBook = new ArrayList<>();
        String str = "";
        while (!str.equals("exit")) {

            System.out.println("enter command");
            str = scanner.nextLine();
            str = str.toLowerCase(Locale.ROOT);
            switch (str) {
                case "create":
                case "add":
                case "1":
                    create(phoneBook);
                    break;
                case "delete":
                case "remove":
                case "2":
                    delete(phoneBook);
                    break;
                case "print":
                case "show":
                case "3":
                    print(phoneBook);
                    break;
                case "update":
                    System.out.println();
                    ;
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                case "0":
                    break;
                case "search":
                    print(search(phoneBook));
                    break;
                case "search for number":
                    print(searchFromNumber(phoneBook));
                    break;
                default:
                    System.out.println("not legal command");
            }
            System.out.println();
        }
        System.out.println("Ending application");
    }

    public static void create(ArrayList<ContactData> phoneBook) {
        phoneBook.add(ContactData.createContactData());
        System.out.println("new contact is created");
    }

    public static ArrayList<ContactData> search(ArrayList<ContactData> phoneBook) {
        System.out.println("Input required user: ");
        String str = scanner.nextLine();
        ArrayList<ContactData> searchResult = new ArrayList<>();
        for (ContactData c : phoneBook) {
            User user = c.getUser();
            if (str.toLowerCase(Locale.ROOT).equals(user.getName().toLowerCase(Locale.ROOT)) ||
                    str.toLowerCase(Locale.ROOT).equals(user.getLastName().toLowerCase(Locale.ROOT)) ||
                    str.toLowerCase(Locale.ROOT).equals(user.getName().toLowerCase(Locale.ROOT) + " "
                            + user.getLastName().toLowerCase(Locale.ROOT)) ||
                    str.toLowerCase(Locale.ROOT).equals(user.getLastName().toLowerCase(Locale.ROOT) + " "
                            + user.getName().toLowerCase(Locale.ROOT))) {
                searchResult.add(c);
            }
        }
        return searchResult;
    }

    public static ArrayList<ContactData> searchFromNumber(ArrayList<ContactData> phoneBook) {
        System.out.println("Input required number: ");
        String number = scanner.nextLine();
        ArrayList<ContactData> searchResult = new ArrayList<>();
        for (ContactData c : phoneBook) {
            User user = c.getUser();
            Contact contact = c.getContact();
            for (PhoneNumber p : contact.getPhoneNumbers()) {
                if (p.getPhoneNumber().equals(number)) {
                    searchResult.add(c);
                    break;
                }
            }
        }
        return searchResult;
    }

    public static boolean delete(ArrayList<ContactData> phoneBook) {
        ArrayList<ContactData> search = search(phoneBook);
        if (search.size() == 0) {
            System.out.println("Contact not found! Please search from number");
            ArrayList<ContactData> searchNum = searchFromNumber(phoneBook);
            if (searchNum.size() == 0) {
                System.out.println("Contact not found!");
            } else if (searchNum.size() == 1) {
                System.out.println("This contact");
                print(searchNum);
                System.out.println("is deleting");
                for (int i = 0; i < phoneBook.size(); i++) {
                    if (phoneBook.get(i).equals(searchNum.get(0))) {
                        phoneBook.remove(i);
                        return true;
                    }
                }
            }

        } else if (search.size() == 1) {
            System.out.println("This contact");
            print(search);
            System.out.println("is deleting");
            for (int i = 0; i < phoneBook.size(); i++) {
                if (phoneBook.get(i).equals(search.get(0))) {
                    phoneBook.remove(i);
                    return true;
                }
            }
        } else if (search.size() > 1) {
            Map<Integer, ContactData> searchMap = new HashMap<>();
            for (int i = 0; i < search.size(); i++) {
                searchMap.put(i + 1, search.get(i));
            }
            for (Map.Entry<Integer, ContactData> m : searchMap.entrySet()) {
                System.out.print(m.getKey() + " " + m.getValue());
                System.out.println();
            }
            System.out.println("Input the position number of the contact you want to delete: ");
            Scanner sc = new Scanner(System.in);
            Integer pos = sc.nextInt();
            if (pos > 0 && pos < searchMap.size() + 1) {
                ContactData selected = searchMap.get(pos);
                for (int i = 0; i < phoneBook.size(); i++) {
                    if (phoneBook.get(i).equals(selected)) {
                        System.out.println("This contact");
                        System.out.println(phoneBook.get(i).toString());
                        System.out.println("is deleting");
                        phoneBook.remove(i);
                        return true;
                    }
                }
            } else {
                System.out.println("Wrong input: ");
            }
        }
        return true;
    }

    public static void help() {
        System.out.println("Command list");
        System.out.println("Type 0, or \"exit\" to create a contact");
        System.out.println("Type 1, or \"create\" \\ \"add\" to create a contact");
        System.out.println("Type 2, or \"delete\" \\ \"remove\"to delete a contact");
        System.out.println("Type 3, or \"print\" \\ \"show\" to print info about a contact");
        System.out.println("Type 4, or \"update\" \\");
        System.out.println("Type 5, or help for help");
        System.out.println("Type 6, or \"search\" to search for a contact");
    }

    public static void print(ArrayList<ContactData> phoneBook) {
        if (phoneBook.size() == 0) {
            System.out.println("Contact not found! ");
        } else {
            for (ContactData c : phoneBook) {
                System.out.println(c);
            }
        }
    }

//    public static void delete(ArrayList<ContactData> phoneBook) {
//        if (phoneBook.size() == 0) {
//            System.out.println("Contact not found! ");
//        } else {
//            for (ContactData c : phoneBook) {
//                System.out.println(c);
//            }
//        }
//    }
}
