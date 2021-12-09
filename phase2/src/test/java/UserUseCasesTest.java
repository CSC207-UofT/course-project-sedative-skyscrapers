package test.java;

import org.junit.Before;
import org.junit.Test;
import main.java.DatabaseRe.AccessData;
import main.java.UserComponent.CreateUserUseCase;
import main.java.UserComponent.CheckUserUsernameUseCase;
import main.java.UserComponent.LookUpUser;

import static org.junit.Assert.*;

public class UserUseCasesTest {
    String ptcUsername;
    String ptcPassword;
    String firstName;
    String lastName;
    String dateOfBirth;
    String ptcPhone;
    String ptcEmail;
    String orgUsername;
    String orgPassword;
    String organization;
    String orgPhone;
    String orgEmail;
    CreateUserUseCase userCreater;
    CheckUserUsernameUseCase usernameChecker;
    AccessData dataAccess;
    LookUpUser userLoopUp;

    @Before
    public void setUp() throws Exception {
        ptcUsername = "ptcTest";
        ptcPassword = "1234";
        firstName = "grape";
        lastName = "fruit";
        dateOfBirth = "2021-12-08";
        ptcPhone = "0123456789";
        ptcEmail = "ptctest@gmail.com";
        orgUsername = "orgTest";
        orgPassword = "5678";
        organization = "skyscraper";
        orgPhone = "1234567890";
        orgEmail = "orgtest@gmail.com";
        this.userCreater = new CreateUserUseCase();
        this.usernameChecker = new CheckUserUsernameUseCase();
        this.userLoopUp = new LookUpUser();
        this.dataAccess = new AccessData();
    }

    @Test
    public void TestStoreParticipant() {
        userCreater.storeParticipant(ptcUsername, ptcPassword, firstName, lastName, dateOfBirth, ptcPhone, ptcEmail);
        assertFalse(dataAccess.getParticipantInfo(ptcUsername).isEmpty());
        assertTrue(userLoopUp.getPtc(ptcUsername).getUserId().equals(userLoopUp.getPtcUserId(ptcUsername)));
    }

    @Test
    public void TestStoreOrganizer() {
        userCreater.storeOrganizer(orgUsername, orgPassword, organization, orgPhone, orgEmail);
        assertFalse(dataAccess.getOrganizerInfo(orgUsername).isEmpty());
    }

    @Test
    public void TestCheckUsernameUsed() {
        assertFalse(usernameChecker.checkUserNameUsed("test"));
    }

    @Test
    public void TestCheckUsernameMatchPassword() {
        assertFalse(usernameChecker.checkUsernameMatchPassword(ptcUsername, "5678", "P"));
        assertTrue(usernameChecker.checkUsernameMatchPassword(orgUsername, orgPassword, "O"));
    }
}