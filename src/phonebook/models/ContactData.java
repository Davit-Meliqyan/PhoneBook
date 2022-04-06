package phonebook.models;

import phonebook.models.contact.Contact;
import phonebook.models.user.User;

import java.io.Serializable;

public class ContactData implements Serializable {
    private User user;
    private Contact contact;

    public ContactData() {
    }

    public ContactData(User user, Contact contact) {
        this.user = user;
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return  user.toString() +'\n'+ contact.toString();
    }

    public static ContactData createContactData() {
        return new ContactData( User.createUser(),Contact.createContact());
    }
}