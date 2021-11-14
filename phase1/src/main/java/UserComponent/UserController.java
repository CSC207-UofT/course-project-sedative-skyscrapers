package main.java.UserComponent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class UserController {
    private HashMap<String, Participant> participantPool;
    private HashMap<String, Organizer> organizerPool;

    //TODO: implement constructor
    public UserController() {}

    /**
     * Return a participant upon request
     * @param username username
     * @param password password
     * @param firstName first name
     * @param lastName last name
     * @param dateOfBirth date of birth
     * @param phone phone
     * @param email email
     * @return a Participant object
     */
    public Participant getParticipant(String username, String password, String firstName, String lastName,
                                      Date dateOfBirth, String phone, String email) {
        if (participantPool.containsKey(username)) {
            return participantPool.get(username);
        } else {
            GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(username, password, firstName,
                    lastName, dateOfBirth, phone, email);
            Participant newParticipant = getParticipantUseCase.getParticipant();
            participantPool.put(username, newParticipant);
            return newParticipant;
        }
    }

    /**
     * Return an organizer upon request
     * @param username username
     * @param password password
     * @param affiliatedOrganization affiliated organization
     * @param phone phone
     * @param email email
     * @return an Organizer object
     */
    public Organizer getOrganizer(String username, String password, String affiliatedOrganization, String phone, String email) {
        if (organizerPool.containsKey(username)) {
            return organizerPool.get(username);
        } else {
            GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(username, password, affiliatedOrganization,
                    phone, email);
            Organizer newOrganizer = getOrganizerUseCase.getOrganizer();
            organizerPool.put(username, newOrganizer);
            return newOrganizer;
        }
    }
}
