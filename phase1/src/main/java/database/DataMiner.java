package main.java.database;

import java.io.*;
import java.net.URL;

/**
 * Mines data from the file it wants to.
 * In case we change the type of database to SQL or something else, this is the class you want to mess with bud ;)
 */
public class DataMiner {

    //TODO: make them constants

    URL raffleUserPath = DataMain.class.getResource("raffleUserDetails.csv");
    File raffleUserDetailsFile = new File((raffleUserPath).getFile());
    URL raffleTaskPath = DataMain.class.getResource("raffleTaskDetails.csv");
    File raffleTaskDetailsFile = new File((raffleTaskPath).getFile());

    URL ousercredPath = DataMain.class.getResource("OuserCred.csv");
    URL pusercredPath = DataMain.class.getResource("PuserCred.csv");
    File OuserCredFile = new File((ousercredPath).getFile());
    File PuserCredFile = new File((pusercredPath).getFile());

    URL rafflePath = DataMain.class.getResource("raffleDetails.csv");
    File raffleDetailsFile = new File((rafflePath).getFile());

    URL raffleWinnersPath = DataMain.class.getResource("raffleWinners.csv");
    File raffleWinnersFile = new File((raffleWinnersPath).getFile());

    BufferedReader raffleBR = new BufferedReader(new FileReader(raffleUserDetailsFile));
    BufferedReader ouserBR = new BufferedReader(new FileReader(OuserCredFile));
    BufferedReader puserBR = new BufferedReader(new FileReader(PuserCredFile));
    BufferedReader raffleTaskBR = new BufferedReader(new FileReader(raffleTaskDetailsFile));
    BufferedReader raffleDetailsBR = new BufferedReader(new FileReader(raffleDetailsFile));
    BufferedReader raffleWinnersBR = new BufferedReader(new FileReader(raffleWinnersFile));

    public DataMiner() throws FileNotFoundException {
    }
    /**
     * @param data_file The name of the data file of which you need the line
     * @param reset must be true if you want to restart the data or start extracting data from final
     * @return returns an arraylist of the data from the file
     */

    // TODO: make getReader function
    public String[] get_line(String data_file, Boolean reset) throws IOException {
        String[] data_mined;
        if (reset.equals(true)) {
            reset_file(data_file);
        }
        BufferedReader currentBR = raffleBR;

        if ("OuserCred".equals(data_file)) {
            currentBR = ouserBR;
        }
        else if ("PuserCred".equals(data_file)) {
            currentBR = puserBR;
        }
        else if ("raffleTaskDetails".equals(data_file)) {
            currentBR = raffleTaskBR;
        }
        else if("raffleDetails".equals(data_file)) {
            currentBR = raffleDetailsBR;
        }
        else if("raffleWinners".equals(data_file)){
            currentBR = raffleWinnersBR;
        }
        String line = currentBR.readLine();
        if (line == null) {
            return null;}
        data_mined = line.split(",");
        return data_mined;
    }

    // TODO: make a smaller function

    private void reset_file(String data_file) throws IOException {

        if ("OuserCred".equals(data_file)) {
            ouserBR = new BufferedReader(new FileReader(OuserCredFile));
        }
        else if ("PuserCred".equals(data_file)) {
            puserBR = new BufferedReader(new FileReader(PuserCredFile));
        }
        else if ("raffleTaskDetails".equals(data_file)) {
            raffleTaskBR = new BufferedReader(new FileReader(raffleTaskDetailsFile));
        }
        else if("raffleDetails".equals(data_file)) {
            raffleDetailsBR = new BufferedReader(new FileReader(raffleDetailsFile));
        }
        else if("raffleWinners".equals(data_file)) {
            raffleDetailsBR = new BufferedReader(new FileReader(raffleWinnersFile));
        }
        else {
            raffleBR = new BufferedReader(new FileReader(raffleUserDetailsFile));
        }
    }


}
