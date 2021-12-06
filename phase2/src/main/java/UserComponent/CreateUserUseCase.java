package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.EntityIdGenerator;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreateUserUseCase {
    private DataAccessPoint DataAccess;
    private final DataProviderPoint DataProvider;

    public CreateUserUseCase(){
        try {
            this.DataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.DataProvider = new ProvideData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeParticipant(String username, String password, String firstName, String lastName,
                                 String dateOfBirth, String phone, String email){
        ArrayList<String> allTakenPtcIds = DataAccess.getTakenParticipantIds();
        EntityIdGenerator userIdGenerator = new EntityIdGenerator(allTakenPtcIds);
        ArrayList<Integer> takenPtcUserNums = userIdGenerator.takenNumList("P".charAt(0));
        String ptcUserId = userIdGenerator.generateEntityId("P".charAt(0), takenPtcUserNums);
        DataProvider.addParticipant(ptcUserId, username, password, firstName, lastName, dateOfBirth, phone,
                email);
    }

    public void storeOrganizer(String username, String password, String organization, String phone,
                               String email){
        ArrayList<String> allTakenOrgIds = DataAccess.getTakenOrganizerIds();
        EntityIdGenerator userIdGenerator = new EntityIdGenerator(allTakenOrgIds);
        ArrayList<Integer> takenOrgUserNums = userIdGenerator.takenNumList("O".charAt(0));
        String orgUserId = userIdGenerator.generateEntityId("O".charAt(0), takenOrgUserNums);
        DataProvider.addOrganizer(orgUserId, username, password, organization, password, phone, email);
    }
}
