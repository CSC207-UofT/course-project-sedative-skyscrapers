package main.java.Helpers;

public class UseCaseDateFormatter {

    /**
     * Being given a LocalDate.toString(), return the array of ints [year, month, day]
     *
     * @param date LocalDate.toString() object to extract date from
     * @return the list of ints year, month, day
     */
    public static int[] formatDateIntoStrings(String date) {
        int day = Integer.parseInt(date.substring(8, 10));
        int month = convertMonthToInt(date.substring(4, 7));
        int year = Integer.parseInt(date.substring(24, 28));
        return new int[]{year, month, day};
    }


    public static int convertMonthToInt(String month) {
        // this warning does not work in some versions of java, so we've kept it
        switch (month) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            default:
                return 12;
        }
    }

}

