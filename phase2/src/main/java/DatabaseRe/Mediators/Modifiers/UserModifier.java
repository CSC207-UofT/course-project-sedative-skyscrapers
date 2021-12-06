package main.java.DatabaseRe.Mediators.Modifiers;


import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

public class UserModifier {
    static InsertUpdateQuery insertUpdateQuery = new InsertUpdateQuery();
    public void changeOrgDetail(String username, String detailToBeChanged, String newValue) throws Exception {
        String query = UpdateQueries.changeOrg(username, detailToBeChanged, newValue);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void changeParticipantDetail(String username, String detailToBeChanged, String newValue) throws Exception {
        String query = UpdateQueries.changeParticipant(username, detailToBeChanged, newValue);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }
}
