package main.java.Web;

import java.time.LocalDate;
import java.util.ArrayList;

// todo is this even needed?
public class ORCBuilder implements ORCBuilderInterface{
    // An OrganizerRaffleController can have
    private OrgRaffleController ORC;

    public ORCBuilder(){
        this.reset();
    }

    @Override
    public void reset(){
        this.ORC = new OrgRaffleController();
    };

    @Override
    public void setRaffleName(String raffleName){
        this.ORC.setRaffleName(raffleName);
    };

    public void setRaffleWinnerNumber(int winnerNum){
        this.ORC.setNumOfWinners(winnerNum);
    };

    public void setRaffleEndDate(LocalDate date){
        this.ORC.setEndDate(date);
    };

    public void setRaffleOrgUsername(String orgUsername){
        this.ORC.setOrgUsername(orgUsername);
    };

    public void setRaffleRulesString(String rules){
        this.ORC.setRulesString(rules);
    };

    public void setRaffleTaskIds(ArrayList<String> tasks){
        this.ORC.setTaskIdList(tasks);
    };

    public OrgRaffleController getOrgRaffleController(){
        OrgRaffleController product = this.ORC;
        this.reset();
        return product;
    }

}
