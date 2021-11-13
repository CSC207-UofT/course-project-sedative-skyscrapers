package main.java.UserComponent;

public class Organizer {
    private final String userName;
    private final String affiliatedOrganization;
    private final String phone;
    private final String email;

    public Organizer(OrganizerBuilder builder) {
        this.userName = builder.userName;
        this.affiliatedOrganization = builder.affiliatedOrganization;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    @Override
    public String toString() {
        return "User: " + this.userName + "\nUser Type: Organizer\naffiliated organization: " +
                this.affiliatedOrganization + "\nphone: " + this.phone + "\nemail: " + this.email;
    }

    public static class OrganizerBuilder {
        private final String userName; //required
        private final String affiliatedOrganization; //required
        private String phone; //optional
        private String email; //optional

        public OrganizerBuilder(String userName, String affiliatedOrganization) {
            this.userName = userName;
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
