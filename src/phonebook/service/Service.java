package phonebook.service;

import phonebook.models.ContactData;
import phonebook.models.contact.Contact;
import phonebook.models.contact.Email;
import phonebook.models.contact.PhoneNumber;
import phonebook.models.user.User;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Service implements Serializable {

    static Scanner scanner = new Scanner(System.in);

    public static void start() throws Exception {
        ArrayList<ContactData> phoneBook = new ArrayList<>();
        File file = new File("src/phonebook/resources/res.txt");
        if (file.length() != 0) {
            phoneBook = inputFile(phoneBook, file);
        }
        System.out.println("Type \"help\" to see available commands.");
        int illegalCount = 0;
        int commandCount = 0;
        String str = "";
        while (!str.equals("exit")) {
            if (commandCount < 4) {
                System.out.println("Enter command:");
                commandCount++;
            }
            str = scanner.nextLine();
            str = str.toLowerCase(Locale.ROOT);
            switch (str) {
                case "create":
                case "add":
                case "1":
                    create(phoneBook);
                    illegalCount = 0;
                    break;
                case "delete":
                case "remove":
                case "2":
                    delete(phoneBook);
                    illegalCount = 0;
                    break;
                case "print":
                case "show":
                case "3":
                    print(phoneBook);
                    illegalCount = 0;
                    break;
                case "update":
                case "4":
                    update(phoneBook);
                    illegalCount = 0;
                    break;
                case "help":
                case "5":
                    help();
                    break;
                case "search":
                case "6":
                    print(search(phoneBook));
                    illegalCount = 0;
                    break;
                case "search number":
                case "7":
                    print(searchFromNumber(phoneBook));
                    illegalCount = 0;
                    break;
                case "exit":
                case "0":
                    outputFile(phoneBook);
                    break;
                default:
                    System.out.println("Illegal command.");
                    illegalCount++;
                    if (illegalCount == 4) {
                        reverseTimer();
                        System.out.println("Too many illegal commands: application frozen for 5 seconds.");
                        System.out.println("1 more illegal command will exit the application.");
                        System.out.println("Enter command in 5 seconds:");
                        break;
                    }
                    if (illegalCount > 4) {
                        str = "exit";
                    }
            }
            System.out.println();
        }
        System.out.println("Ending application");
    }

    public static void create(ArrayList<ContactData> phoneBook) {
        phoneBook.add(ContactData.createContactData());
        System.out.println("A new contact is created.");
    }

    public static ArrayList<ContactData> search(ArrayList<ContactData> phoneBook) {
        System.out.println("Enter required user:");
        String str = scanner.nextLine();
        ArrayList<ContactData> searchResult = new ArrayList<>();
        for (ContactData c : phoneBook) {
            User user = c.getUser();
            str = str.toLowerCase(Locale.ROOT);
            String name = user.getName().toLowerCase(Locale.ROOT);
            String lastName = user.getLastName().toLowerCase(Locale.ROOT);
            if (user.getName().length() > 1 && user.getLastName().length() > 1) {
                if (str.equals(name) || str.equals(lastName) ||
                        str.equals(name + " " + lastName) ||
                        str.equals(lastName + " " + name)) {
                    searchResult.add(c);
                }
            }
            if (user.getName().length() > 1 && user.getLastName().length() < 1) {
                if (str.equals(name)) {
                    searchResult.add(c);
                }
            }
            if (user.getName().length() < 1 && user.getLastName().length() > 1) {
                if (str.equals(lastName)) {
                    searchResult.add(c);
                }
            }
        }
        return searchResult;
    }

    public static ArrayList<ContactData> searchFromNumber(ArrayList<ContactData> phoneBook) {
        System.out.println("Enter required number:");
        String number = scanner.nextLine();
        ArrayList<ContactData> searchResult = new ArrayList<>();
        for (ContactData c : phoneBook) {
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

    public static void update(ArrayList<ContactData> phoneBook) throws InterruptedException {
        ArrayList<ContactData> search = search(phoneBook);
        if (search.size() == 0) {
            System.out.println("Contact not found.");
            return;
        }
        ContactData contactData;
        if (search.size() == 1) {
            contactData = search.get(0);
        } else
            contactData = selectPosition(search);
        updateContactData(contactData, phoneBook);
    }

    public static void updateContactData(ContactData contactData, ArrayList<ContactData> phoneBook) throws InterruptedException {
        for (int i = 0; i < phoneBook.size(); i++) {
            if (phoneBook.get(i).equals(contactData)) {
                int illegalCount = 0;
                String str = "";
                System.out.println("How would you like to update the contact data?");
                helpUpdate();
                while (!str.equals("save")) {
                    str = scanner.nextLine();
                    str = str.toLowerCase(Locale.ROOT);
                    switch (str) {
                        case "all":
                        case "1":
                            phoneBook.set(i, ContactData.createContactData());
                            illegalCount = 0;
                            System.out.println("Save changes, or continue?");
                            System.out.println("Enter command:");
                            break;
                        case "user":
                        case "2":
                            User tempUser = User.createUser();
                            phoneBook.get(i).setUser(tempUser);
                            illegalCount = 0;
                            System.out.println("Save changes, or continue?");
                            System.out.println("Enter command:");
                            break;
                        case "number":
                        case "3":
                            System.out.println("Would you like to add a new number, or update an existing one?");
                            System.out.println("Type \"add\", or \"update\"");
                            str = scanner.nextLine();
                            str = str.toLowerCase(Locale.ROOT);
                            switch (str) {
                                case "add":
                                    PhoneNumber tempPN1 = PhoneNumber.createPhoneNumber();
                                    phoneBook.get(i).getContact().getPhoneNumbers().add(tempPN1);
                                    illegalCount = 0;
                                    System.out.println("A new number has been added.");
                                    System.out.println("Save changes, or continue?");
                                    System.out.println("Enter command:");
                                    break;
                                case "update":
                                    ArrayList<PhoneNumber> tempArray = phoneBook.get(i).getContact().getPhoneNumbers();
                                    PhoneNumber tempPN2 = PhoneNumber.selectPhoneNumber(tempArray);
                                    for (int k = 0; k < tempArray.size(); k++) {
                                        if (tempArray.get(k).equals(tempPN2)) {
                                            tempArray.set(k, PhoneNumber.createPhoneNumber());
                                            break;
                                        }
                                    }
                                    illegalCount = 0;
                                    System.out.println("The number has been updated.");
                                    System.out.println("Save changes, or continue?");
                                    System.out.println("Enter command:");
                                    break;
                            }
                            break;
                        case "email":
                        case "4":
                            Email tempEmail = Email.createEmail();
                            phoneBook.get(i).getContact().setEmails(tempEmail);
                            illegalCount = 0;
                            System.out.println("Email has been updated.");
                            System.out.println("Save changes, or continue?");
                            System.out.println("Enter command:");
                            break;
                        case "company":
                        case "5":
                            System.out.println("Enter new company name:");
                            str = scanner.nextLine();
                            phoneBook.get(i).getContact().setCompany(str);
                            illegalCount = 0;
                            System.out.println("Company has been updated.");
                            System.out.println("Save changes, or continue?");
                            System.out.println("Enter command:");
                            break;
                        case "help":
                        case "6":
                            illegalCount = 0;
                            helpUpdate();
                            break;
                        case "continue":
                            illegalCount = 0;
                            System.out.println("Enter command:");
                            break;
                        case "save":
                            illegalCount = 0;
                            System.out.println("The contact is updated.");
                            break;
                        default:
                            System.out.println("Illegal command.");
                            illegalCount++;
                            if (illegalCount == 4) {
                                System.out.println("Too many illegal commands: update function frozen for 5 seconds.");
                                System.out.println("1 more command will automatically end the update function.");
                                System.out.println("Enter command in 5 seconds:");
                                reverseTimer();
                                break;
                            }
                            if (illegalCount > 4) {
                                System.out.println("Saving changes");
                                str = "save";
                            }
                    }
                }
            }
        }
    }

    public static void delete(ArrayList<ContactData> phoneBook) {
        ArrayList<ContactData> search = search(phoneBook);
        if (search.size() == 0) {
            System.out.println("Contact not found.");
            return;
        }
        ContactData contactData;
        if (search.size() == 1) {
            contactData = search.get(0);
        } else
            contactData = selectPosition(search);
        for (int i = 0; i < phoneBook.size(); i++) {
            if (phoneBook.get(i).equals(contactData)) {
                System.out.println("This contact");
                System.out.println(phoneBook.get(i).toString());
                System.out.println("is deleting");
                phoneBook.remove(i);
                return;
            }
        }
    }

    public static ContactData selectPosition(ArrayList<ContactData> search) {
        Map<Integer, ContactData> searchMap = new HashMap<>();
        for (int i = 0; i < search.size(); i++) {
            searchMap.put(i + 1, search.get(i));
        }
        for (Map.Entry<Integer, ContactData> m : searchMap.entrySet()) {
            System.out.print(m.getKey() + " " + m.getValue());
            System.out.println();
        }
        System.out.println("Input the position number of selected contact: ");
        Scanner sc = new Scanner(System.in);
        Integer pos = sc.nextInt();
        if (pos > 0 && pos < searchMap.size() + 1) {
            return searchMap.get(pos);
        }
        System.out.println("Contact not found!");
        return null;
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

    public static ArrayList<ContactData> inputFile(ArrayList<ContactData> phoneBook, File file) throws Exception {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                try {
                    phoneBook.add((ContactData) objectInputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return phoneBook;
    }

    public static void outputFile(ArrayList<ContactData> phoneBook) throws Exception {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/phonebook/resources/res.txt"));
            for (ContactData c : phoneBook) {
                objectOutputStream.writeObject(c);
            }
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("saving to file");
    }

    public static void helpUpdate() {
        System.out.println("Command list");
        System.out.println("Type 1, or \"all\" to update all info");
        System.out.println("Type 2, or \"user\" to update name and last name");
        System.out.println("Type 3, or \"number\" to add, or update number");
        System.out.println("Type 4, or \"email\" to update email");
        System.out.println("Type 5, or \"company\" to update company info");
        System.out.println("Type \"save\" to complete updating");
    }

    public static void help() {
        System.out.println("Command list");
        System.out.println("Type 1, or \"create\" \\ \"add\" to create a contact");
        System.out.println("Type 2, or \"delete\" \\ \"remove\"to delete a contact");
        System.out.println("Type 3, or \"print\" \\ \"show\" to print info about a contact");
        System.out.println("Type 4, or \"update\" \\");
        System.out.println("Type 5, or \"help\" for help");
        System.out.println("Type 6, or \"search\" to search for a contact");
        System.out.println("Type 7, or \"search number\" to search from a number");
        System.out.println("Type 0, or \"exit\" to create a contact");
    }

    public static void reverseTimer() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 5;

            public void run() {
                System.out.println(countdownStarter);
                countdownStarter--;
                if (countdownStarter < 1) {
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }
}