package main.java.UserComponent;

import main.java.UserComponent.Organizer;

/**
 * the class creates an Organizer object using based on the information provided
 */
public class GetOrganizerUseCase {
    private final Organizer organizer;

    public GetOrganizerUseCase(String userId, String username, String password, String affiliatedOrganization,
                               String phone, String email) {
        if (phone != null && email != null) {
            this.organizer = new Organizer.OrganizerBuilder(userId, username, password, affiliatedOrganization)
                    .phone(phone)
                    .email(email)
                    .build();
        } else if (phone != null) {
            this.organizer = new Organizer.OrganizerBuilder(userId, username, password, affiliatedOrganization)
                    .phone(phone)
                    .build();
        } else if (email != null) {
            this.organizer = new Organizer.OrganizerBuilder(userId, username, password, affiliatedOrganization)
                    .email(email)
                    .build();
        } else {
            //both optional info are null
            this.organizer = new Organizer.OrganizerBuilder(userId, username, password, affiliatedOrganization).build();
        }
    }

    public Organizer getOrganizer() {
        return organizer;
    }

}
