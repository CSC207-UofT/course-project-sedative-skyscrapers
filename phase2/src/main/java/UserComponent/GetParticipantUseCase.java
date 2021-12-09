package main.java.UserComponent;

import java.time.LocalDate;

import main.java.UserComponent.Participant;

/**
 * the class creates a Participant object based on the information provide
 */
public class GetParticipantUseCase {
    private final Participant participant;

    public GetParticipantUseCase(String userId, String userName, String password, String firstName, String lastName,
                                 LocalDate dateOfBirth, String phone, String email) {
        Participant newParticipant = new Participant(userId, userName, password, firstName, lastName, dateOfBirth,
                email);
        if (phone != null) {
            newParticipant.setPhone(phone);
        }
        this.participant = newParticipant;
    }

    public Participant getParticipant() {
        return participant;
    }
}
