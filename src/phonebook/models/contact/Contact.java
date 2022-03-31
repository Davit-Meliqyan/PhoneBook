package phonebook.models.contact;

import java.util.ArrayList;
import java.util.Scanner;

public class Contact {
    private ArrayList<PhoneNumber> phoneNumbers;
    private Email email;
    private String company;

    public Contact(ArrayList<PhoneNumber> phoneNumbers, Email email, String company) {
        this.phoneNumbers = phoneNumbers;
        this.email = email;
        this.company = company;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmails(Email email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            s.append(phoneNumber).append('\n');
        }

        if (!Email.isEmail(email.getEmail())) {
            return s + company;
        } else return s + email.toString() + '\n' + company;
    }

    public static Contact createContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating contact");
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(PhoneNumber.createPhoneNumber());
        while (true) {
            System.out.println("Would you like to input another phone number?");
            String str = scanner.nextLine();
            if (str.toLowerCase().equals("no")) {
                break;
            }
            if (str.toLowerCase().equals("yes")) {
                phoneNumbers.add(PhoneNumber.createPhoneNumber());
            }
        }
        Email email = Email.createEmail();
        String company = "";
        while (true) {
            System.out.println("Would you like to input company?");
            String str = scanner.nextLine();
            if (str.toLowerCase().equals("no")) {
                break;
            }
            if (str.toLowerCase().equals("yes")) {
                System.out.println("Enter company: ");
                company = scanner.nextLine();
                break;
            }
        }
        return new Contact(phoneNumbers, email, company);
    }
}
