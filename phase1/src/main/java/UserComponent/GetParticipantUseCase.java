package main.java.UserComponent;

import java.util.Date;

public class GetParticipantUseCase {
    private final Participant participant;

    public GetParticipantUseCase(String userName, String firstName, String lastName,
                                      Date dateOfBirth, String phone, String email) {
        Participant newParticipant = new Participant(userName, firstName, lastName);
        if (dateOfBirth != null) {
            newParticipant.setDateOfBirth(dateOfBirth);
        }
        if (phone != null) {
            newParticipant.setPhone(phone);
        }
        if (email != null) {
            newParticipant.setEmail(email);
        }
        this.participant = newParticipant;
    }

    public Participant getParticipant() {
        return participant;
    }
}
