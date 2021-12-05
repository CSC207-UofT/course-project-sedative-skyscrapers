package main.java.UserComponent;
import java.time.LocalDate;

public class Participant {
    private final String userId;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;

    public Participant(String userId, String username, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = null;
        this.phone = null;
        this.email = null;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User: " + this.username + "\nUser Type: Participant\n first name: " + this.firstName + "\nlast name: " +
                this.lastName + "\n date of birth: " + this.dateOfBirth + "\nphone: " + this.phone + "\nemail: " +
                this.email;
    }
}