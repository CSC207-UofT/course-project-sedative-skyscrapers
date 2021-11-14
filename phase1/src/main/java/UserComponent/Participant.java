package main.java.UserComponent;
import java.util.Date;

public class Participant {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private Date dateOfBirth;
    private String phone;
    private String email;

    public Participant(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = null;
        this.phone = null;
        this.email = null;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

//    public static class ParticipantBuilder {
//        private final String userName; //required
//        private final String firstName; //required
//        private final String lastName; //required
//        private Date dateOfBirth; //optional
//        private String phone; //optional
//        private String email; //optional
//
//        public ParticipantBuilder(String userName, String firstName, String lastNme) {
//            this.userName = userName;
//            this.firstName = firstName;
//            this.lastName = lastNme;
//        }
//
//        public ParticipantBuilder dateOfBirth(Date dateOfBirth) {
//            this.dateOfBirth = dateOfBirth;
//            return this;
//        }
//
//        public ParticipantBuilder phone(String phone) {
//            this.phone = phone;
//            return this;
//        }
//
//        public ParticipantBuilder email(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Participant build() {
//            return new Participant(this);
//        }
//    }
}