package phonebook;

import phonebook.models.contact.*;
import phonebook.models.user.User;

import static phonebook.service.Service.start;

public class Main {
    public static void main(String[] args) {
        // start();
        //   System.out.println(PhoneNumberType.selectPhoneNumberType());
        //  System.out.println(PhoneNumber.createPhoneNumber());
//        String n = "sdsd@gmail.com";
//        System.out.println(Email.isEmail(n));
//        System.out.println(Email.createEmail());
        System.out.println(User.createUser());
        System.out.println(Contact.createContact());
    }
}
