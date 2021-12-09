package main.java.UserComponent;

import java.time.LocalDate;

/**
 * the class represents a Particpant user
 */
public class Participant {
    private final String userId;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private String phone;
    private final String email;

    public Participant(String userId, String username, String password, String firstName, String lastName,
                       LocalDate dateOfBirth, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = null;
        this.email = email;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User: " + this.username + "\nUser Type: Participant\n first name: " + this.firstName + "\nlast name: " +
                this.lastName + "\n date of birth: " + this.dateOfBirth + "\nphone: " + this.phone + "\nemail: " +
                this.email;
    }
}