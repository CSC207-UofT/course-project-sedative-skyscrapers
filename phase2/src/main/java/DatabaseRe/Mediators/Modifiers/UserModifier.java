package main.java.DatabaseRe.Mediators.Modifiers;


import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

public class UserModifier {

    public void changeOrgDetail(String username, String detailToBeChanged, String newValue) throws Exception {
        String query = UpdateQueries.changeOrg(username, detailToBeChanged, newValue);
        InsertUpdateQuery.run(query);
    }

    public void changeParticipantDetail(String username, String detailToBeChanged, String newValue) throws Exception {
        String query = UpdateQueries.changeParticipant(username, detailToBeChanged, newValue);
        InsertUpdateQuery.run(query);
    }
}
