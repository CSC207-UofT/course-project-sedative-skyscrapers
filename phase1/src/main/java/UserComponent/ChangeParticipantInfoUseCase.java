package main.java.UserComponent;

import java.util.Date;

public class ChangeParticipantInfoUseCase {
    private final Participant participant;

    public ChangeParticipantInfoUseCase(Participant participant) {
        this.participant = participant;
    }

    public void changeParticipantDateOfBirth(Date dateOfBirth) {
        participant.setDateOfBirth(dateOfBirth);
    }

    public void changeParticipantPhone(String phone) {
        participant.setPhone(phone);
    }

    public void changeParticipantEmail(String email) {
        participant.setEmail(email);
    }

    public Participant getEditedParticipant() {
        return this.participant;
    }
}
