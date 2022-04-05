package phonebook.models.user;

import java.util.Scanner;

public class User {
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("input Name: ");
        String name = scanner.nextLine();
        System.out.println("input Last Name: ");
        String lastName = scanner.nextLine();

        return  new User(name, lastName);
    }
}
