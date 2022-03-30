package phonebook.models.contact;

import java.util.ArrayList;
import java.util.Scanner;

public class Contact {

    private ArrayList<PhoneNumber> phoneNumbers;
    private ArrayList<Email> emails;

    private String company;

    public Contact() {
    }

    public Contact(ArrayList<PhoneNumber> phoneNumbers, ArrayList<Email> emails, String company) {
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
        this.company = company;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return  phoneNumbers.toString() + "\n" + emails.toString() + "\n" + company;
    }

    public static Contact createContact(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating contact");
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(PhoneNumber.createPhoneNumber());
        ArrayList<Email> emails = new ArrayList<>();
        emails.add(Email.createEmail());

        System.out.println("Enter company: ");
        String company = scanner.nextLine();
        return new Contact(phoneNumbers,emails,company);
    }
}
