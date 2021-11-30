package main.java.Web;

import java.time.LocalDate;
import java.util.ArrayList;

// todo is this even needed?
/**
 * The interface defining the methods for the builder pattern for the OrganizerRaffleController class
 */
public interface ORCBuilderInterface {

    public void reset();

    public void setRaffleName(String raffleName);

    public void setRaffleWinnerNumber(int winnerNumber);

    public void setRaffleEndDate(LocalDate date);

    public void setRaffleOrgUsername(String orgUsername);

    public void setRaffleRulesString(String rules);

    public void setRaffleTaskIds(ArrayList<String> tasks);

    public OrgRaffleController getOrgRaffleController();
}
