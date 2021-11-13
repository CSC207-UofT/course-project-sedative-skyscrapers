package main.java.UserWeb;

import main.java.UserComponent.GetOrganizerUseCase;
import main.java.UserComponent.GetParticipantUseCase;
import main.java.UserComponent.Organizer;
import main.java.UserComponent.Participant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class UserController{
    private Set<String> userNamePool; //who should be checking whether the username is used
    private HashMap<String, Participant> participantPool;
    private HashMap<String, Organizer> organizerPool;
    private HashMap<String, ArrayList<String>> participantToRaffleID;
    private HashMap<String, ArrayList<String>> organizerToRaffleID;

    public UserController() {}

    /*
    Return a Participant object based on the information userName, firstName, lastName, dateOfBirth, phone, and email.
    */
    public Participant getParticipant(String userName, String firstName, String lastName,
                                      Date dateOfBirth, String phone, String email) {
        if (participantPool.containsKey(userName)) {
            return participantPool.get(userName);
        } else {
            GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(userName, firstName, lastName,
                    dateOfBirth, phone, email);
            Participant newParticipant = getParticipantUseCase.getParticipant();
            participantPool.put(userName, newParticipant);
            return newParticipant;
        }
    }

    /*
    Return an Organizer object based on the information userName, affiliatedOrganization, phone, and email.
     */
    public Organizer getOrganizer(String userName, String affiliatedOrganization, String phone, String email) {
        if (organizerPool.containsKey(userName)) {
            return organizerPool.get(userName);
        } else {
            GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(userName, affiliatedOrganization,
                    phone, email);
            Organizer newOrganizer = getOrganizerUseCase.getOrganizer();
            organizerPool.put(userName, newOrganizer);
            return newOrganizer;
        }
    }

    //TODO: implement addParticipantRaffleID to associate the list of raffleID to the participant
    public void addParticipantRaffleID(String userName, ArrayList<String> raffleID) {

    }

    //TODO: implement addOrganizerRaffleID to associate the list of raffleID to the organizer
    public void addOrganizerRaffleID(String userName, ArrayList<String> raffleID) {

    }
}
