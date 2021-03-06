package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;

import main.java.Helpers.EntityIdGenerator;
import main.java.DatabaseRe.DataAccessPoint;
import main.java.DatabaseRe.DataProviderPoint;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * the class uploads newly registered user's info onto the database using dependency injection
 */
public class CreateUserUseCase {
    private DataAccessPoint DataAccess;
    private DataProviderPoint DataProvider;

    public CreateUserUseCase() {
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

    /**
     * stores newly registered paritipcant's info
     */
    public void storeParticipant(String username, String password, String firstName, String lastName,
                                 String dateOfBirth, String phone, String email) {
        ArrayList<String> allTakenPtcIds = DataAccess.getTakenParticipantIds();
        EntityIdGenerator userIdGenerator = new EntityIdGenerator(allTakenPtcIds);
        ArrayList<Integer> takenPtcUserNums = userIdGenerator.takenNumList("P".charAt(0));
        String ptcUserId = userIdGenerator.generateEntityId("P".charAt(0), takenPtcUserNums);
        DataProvider.addParticipant(ptcUserId, username, password, firstName, lastName, dateOfBirth, phone,
                email, new ArrayList<String>(0));
    }

    /**
     * stores newly registered organizer's info
     */
    public void storeOrganizer(String username, String password, String organization, String phone,
                               String email) {
        ArrayList<String> allTakenOrgIds = DataAccess.getTakenOrganizerIds();
        EntityIdGenerator userIdGenerator = new EntityIdGenerator(allTakenOrgIds);
        ArrayList<Integer> takenOrgUserNums = userIdGenerator.takenNumList("O".charAt(0));
        String orgUserId = userIdGenerator.generateEntityId("O".charAt(0), takenOrgUserNums);
        DataProvider.addOrganizer(orgUserId, username, password, organization, phone, email);
    }
}
