import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class User implements Participable, Organizable {
    private int raffleID;
    private String username;


    public User(String username) {
        this.username = username;
    }

    @Override
    public void joinRaffle(int raffleID) {

    }

    // Change 1: Take raffleNo as input parameter.
    @Override
    public void createRaffle(int raffleNo) {
        /**
         * Creates a new raffle and assigns the raffleID to the raffleNo
         * (denotes the raffle object number like 1st raffle, 2nd raffle... 12th raffle, etc -- start at 0 and increase).
         */

        this.raffleID =  raffleNo;


        // Not required since we already assign username from User() constructor.

//        Scanner nameInput = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter username :");
//
//        username = nameInput.nextLine();  // Read user input. nextLine() expects a String input from user.

    }

    @Override
    public String addRaffleRules(int raffleID, File rulesDescriptionFile) {
        /**
         * Returns the rules for the given raffleID in a readable String format based on
         * the input text file from the parameter rulesDescriptionFile
         *
         * @param  raffleID unique integer value representing each raffle contest
         * @param  rulesDescriptionFile text file with instructions for the given raffle
         * @return      Readable string format of the rules for the contest.
         */

        //TODO: Implement this method.
        return  "";
    }

    // Change 2: Take Raffle as input so we can access endDate attribute.
    @Override
    public void changeRaffleEndDate(Date newEndDate, Raffle rf) {
        /**
         * Change the date for the raffle object to the newEndDate value.
         *
         * @param  rf Raffle object to access the date attribute for the raffle
         * @param  newEndDate date value in the desired format (discuss which format we will use)
         */

        rf.endDate = newEndDate;

    }

    // Suggestion: Have a map in Raffle class that will map each raffle ID to a list of strings of rafflePrizes
    @Override
    public void declarePrizes(int raffleID, String rafflePrizes) {
        /**
         * Display the available prizes for the given raffleID
         *
         * @param  raffleID unique integer value representing each raffle contest
         * @param  rafflePrizes String representing the available prizes for the raffle
         */
        System.out.println("The prizes for the given Raffle ID '" + raffleID + "' is :" + rafflePrizes);

    }

//    @Override
//    public void declareNumWinners(int raffleID, int numWinners) {
//        /**
//         * Display the total number of allowed winners for the given raffleID
//         *
//         * @param  raffleID unique integer value representing each raffle contest
//         * @param  numWinners int representing total number of allowed winners for the raffle contest
//         */
//
//        System.out.println("The number of winners for the given Raffle ID '" + raffleID + "' are :" + numWinners);
//
//    }

    @Override
    public User generateAndNotifyWinner(List<User> participantList, int raffleNo) {
        /**
         * Return and inform the chosen winner for the raffle contest.
         *
         * @param  raffleID unique integer value representing each raffle contest
         * @param  participantList List<User> representing the participants in the raffle contest
         * @return Selected Winner (User object) for the raffle contest
         */

        //TODO: This code is under the assumption that all users have same raffleID for 1 contest and will
        // win based on the actions completed.

        if (participantList.isEmpty()){
            return null; //Later throw exception (custom message like no user found bla bla bla)
        }
        else{
            // List of those participants that are in the given raffleNo (since they can be in multiple raffles)
            List<User> currentRaffleList = new ArrayList<>();
            for(User user: participantList){
                if(raffleNo == user.raffleID){
                    //Change later to loop over user's list of raffleID's and check for 'raffleNo'.
                    currentRaffleList.add(user);
                }

            }
            if (currentRaffleList.size() == 0){
                return null; //Later throw custom message exception
            }
            else{
                //TODO: Later select based on actions completed or something
                int winnerIndex = (int) (Math.random() * (currentRaffleList.size() - 1)); // from 0 to last element.

                //TODO: Add code for informing the selected winner.

                return currentRaffleList.get(winnerIndex); // Returns the selected winner (random) for the raffle
            }

        }


    }
}

