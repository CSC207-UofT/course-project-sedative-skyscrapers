package main.java.database;

import java.io.FileWriter;
import java.io.IOException;

public class AddParticipant {
    GetFileToAppend getfile = new GetFileToAppend();
    String username = "";
    String password = "";
    String phone = "";
    String email = "";
    String firstName = "";
    String lastName = "";
    String ageDate = "";

    String COMMA = ",";
    //Methods
    public AddParticipant() throws IOException {
    }

    public void updateParticipantPool(String iusername, String ipassword, String ifirstName,
                                      String ilastName, String idateOfBirth, String iphone,
                                      String iemail) throws IOException {
        String row = assignData(iusername, ipassword, ifirstName, ilastName, idateOfBirth, iphone, iemail);
        pleasePrint(row);
    }

    private void pleasePrint(String row) throws IOException {
        FileWriter fw  = getfile.getFile("PuserCred");
        fw.append(row);
        fw.flush();
        fw.close();
    }

    private String assignData(String iusername, String ipassword, String ifirstName, String ilastName, String idateOfBirth, String iphone, String iemail) {
        username = iusername;
        password = ipassword;
        firstName = ifirstName;
        lastName = ilastName;
        ageDate = idateOfBirth;
        phone = iphone;
        email = iemail;

        return "\n" + username + COMMA + password + COMMA + firstName + COMMA + lastName + COMMA +
                ageDate + COMMA + phone + COMMA + email;
    }


}
