package phonebook.models.user;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    private String name;
    private String lastName;

    private User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return name + ' ' + lastName;
    }

    public static User createUser() {

        boolean bool = true;
        String name = "";
        String lastName = "";
        while (bool) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name:");
            name = scanner.nextLine();
            System.out.println("Enter last name:");
            lastName = scanner.nextLine();
            if (name.length() > 1 || lastName.length() > 1) {
                return new User(name, lastName);
            }
            System.out.println("Enter a valid name, or last name.");
        }
        return new User(name, lastName);
    }
}