package phonebook.models.contact;

import java.util.Scanner;

public class Email {
    private String email;
    private String emailType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Email() {
    }

    public Email(String email, String emailType) {
        this.email = email;
        this.emailType = emailType;
    }

    @Override
    public String toString() {
        return email + "\n" + emailType;
    }

    static Scanner scanner = new Scanner(System.in);

    public static Email createEmail() {
        System.out.println("Enter email");
        String email = scanner.nextLine();
        if (isEmail(email)) {
            return new Email(email, emailType(email));
        }
        return new Email();
    }

    public static boolean isEmail(String email) {
        if (email == null || email.length() == 0) {
            return false;
        }
        if (email.contains(" ") || !email.contains(".") || !email.contains("@")) {
            return false;
        }
        if (email.indexOf('@') > email.indexOf('.')) {
            return false;
        }
        if (email.charAt(0) == '.' || email.charAt(0) == '@' || email.charAt(email.length() - 1) == '@' || email.charAt(email.length() - 1) == '.') {
            return false;
        }
        return email.indexOf('.') == email.lastIndexOf('.') && email.indexOf('@') == email.lastIndexOf('@');
    }

    public static String emailType(String email) {
        return email.substring(email.indexOf('@') + 1);
    }
}
