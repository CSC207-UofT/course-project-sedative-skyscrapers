package main.java.DatabaseRe.TalkToDatabase;


import java.sql.Types;
import java.util.ArrayList;

public abstract class ConfigConstants {

    public static String getDB_URL() {
        return "jdbc:mysql://sedskydatabase.cqf2i6tzjkck.us-east-2.rds.amazonaws.com?enabledTLSProtocols=TLSv1.2";
    }

    public static String getUSER() {
        return "admin";
    }

    public static String getPASSWORD() {
        return "Admin12345";
    }

    public static ArrayList<Integer> getTypes() {
        ArrayList<Integer> possibleValues = new ArrayList<>();
        possibleValues.add(Types.CHAR);
        possibleValues.add(Types.LONGVARCHAR);
        possibleValues.add(Types.DATE);
        possibleValues.add(Types.VARCHAR);
        return possibleValues;
    }
}
