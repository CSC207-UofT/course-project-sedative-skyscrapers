package main.java.DatabaseRe.Mediators;

import main.java.DatabaseRe.TalkToDatabase.ConfigConstants;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class QueryTools {
    static String pattern = "MM/dd/yyyy HH:mm:ss";
    static DateFormat df = new SimpleDateFormat(pattern);

    public ArrayList<String> getStrings(ResultSet results, String columnName) throws SQLException {
        ArrayList<String> strings = new ArrayList<>();

        while(results.next()) {
            strings.add(results.getString(columnName));
        }

        return strings;
    }

    /**
     * @param results The ResultSet object whose rows are to be extracted
     * @return A list of strings from the columns which were needed
     */
    public static ArrayList<Object> getRow(ResultSet results) throws SQLException, ParseException {
        ArrayList<Object> details = new ArrayList<>();
        if (results != null) {
            while (results.next()) {
                ResultSetMetaData resultSetMetaData = results.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    int type = resultSetMetaData.getColumnType(i);
                    ArrayList<Integer> possibleDataTypes = ConfigConstants.getTypes();
                    if (possibleDataTypes.contains(type)) {
                        if (type == Types.DATE) {
                            Date date2 = getDate(results, i);
                            details.add(date2);
                        }
                        else {
                            details.add(results.getString(i));
                        }
                    }
                }
            }
        }
        else {
            throw new RuntimeException("Empty Row found");
        }
        return details;
    }

    private static Date getDate(ResultSet results, int i) throws SQLException, ParseException {
        String date = results.getString(i);
        String modified = date.substring(8,10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        return new SimpleDateFormat("dd/MM/yyyy").parse(modified);
    }

    /**
     * Converts an ArrayList<objects> to ArrayList<Strings></Strings>
     * @param objectArrayList ArrayList of objects to be converted to ArrayList<Strings></Strings>
     */
    public static ArrayList<String> convertToString(ArrayList<Object> objectArrayList) {
        ArrayList<String> convertedStrings = new ArrayList<>();
        for (Object obj: objectArrayList) {
            if (obj instanceof Date) {
                convertedStrings.add(df.format(obj));
            }
            else {
                convertedStrings.add(Objects.toString(obj));
            }
        }
        return convertedStrings;
    }
}
