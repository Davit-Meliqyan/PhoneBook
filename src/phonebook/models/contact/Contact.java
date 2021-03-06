package phonebook.models.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Contact implements Serializable {

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
        StringBuilder str = new StringBuilder();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            str.append(phoneNumber).append('\n');
        }
        if (!Email.isEmail(email.getEmail())) {
            if (company == null || company.length() == 0) {
                return str + "";
            }
            return str + "Company: " + company + '\n';
        } else if (company == null || company.length() == 0) {
            return str + email.toString() + '\n' + "Company: " + company + '\n';
        }
        return str + email.toString() + '\n' + "Company: " + company + '\n';
    }

    public static Contact createContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating a contact");
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(PhoneNumber.createPhoneNumber());
        while (true) {
            System.out.println("Would you like to add another phone number?");
            String str = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (str.equals("no")) {
                break;
            }
            if (str.equals("yes")) {
                phoneNumbers.add(PhoneNumber.createPhoneNumber());
            }
        }
        Email email = Email.createEmail();
        System.out.println("Enter company:");
        String company = scanner.nextLine();
        return new Contact(phoneNumbers, email, company);
    }
}