package main.java.UserComponent;

/**
 * the class represents an Organizer user
 */
public class Organizer {
    private final String userId;
    private final String username;
    private final String password;
    private final String affiliatedOrganization;
    private final String phone;
    private final String email;

    public Organizer(OrganizerBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.affiliatedOrganization = builder.affiliatedOrganization;
        this.phone = builder.phone;
        this.email = builder.email;
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

    @Override
    public String toString() {
        return "User: " + this.username + "\nUser Type: Organizer\naffiliated organization: " +
                this.affiliatedOrganization + "\nphone: " + this.phone + "\nemail: " + this.email;
    }

    public static class OrganizerBuilder {
        private final String userId;
        private final String username;
        private final String password;
        private final String affiliatedOrganization;
        private String phone;
        private String email;

        public OrganizerBuilder(String userId, String username, String password, String affiliatedOrganization) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.affiliatedOrganization = affiliatedOrganization;
        }

        public OrganizerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public OrganizerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Organizer build() {
            return new Organizer(this);
        }
    }
}
