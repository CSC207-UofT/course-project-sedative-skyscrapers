package main.java.UserComponent;

import java.time.LocalDate;
import main.java.UserComponent.Participant;

public class GetParticipantUseCase {
    private final Participant participant;

    public GetParticipantUseCase(String userId, String userName, String password, String firstName, String lastName,
                                 LocalDate dateOfBirth, String phone, String email) {
        Participant newParticipant = new Participant(userId, userName, password, firstName, lastName);
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
