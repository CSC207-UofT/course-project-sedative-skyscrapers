package main.java.UserComponent;


public class GetOrganizerUseCase {
    private final Organizer organizer;

    public GetOrganizerUseCase(String userName, String affiliatedOrganization, String phone, String email) {
        if (phone != null && email != null){
            this.organizer = new Organizer.OrganizerBuilder(userName, affiliatedOrganization).phone(phone).email(email).build();
        }
        else if (phone != null) {
            this.organizer = new Organizer.OrganizerBuilder(userName, affiliatedOrganization).phone(phone).build();
        }
        else if (email != null) {
            this.organizer = new Organizer.OrganizerBuilder(userName, affiliatedOrganization).email(email).build();
        } else {
            //both optional info are null
            this.organizer = new Organizer.OrganizerBuilder(userName, affiliatedOrganization).build();
        }
    }

    public Organizer getOrganizer() {
        return organizer;
    }

}
