package main.java;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class User implements Participable, Organizable {
    private String username;
    private HashMap<Integer, ParticipantRaffle> ptcRaffles;
    private HashMap<Integer, OrganizerRaffle> orgRaffles;


    public User(String username) {
        this.username = username;
        this.ptcRaffles = new HashMap<>();
        this.orgRaffles = new HashMap<>();
    }

    @Override
    public OrganizerRaffle createRaffle(String raffleName, int numOfWinners, LocalDate endDate) {
        /**
         * Creates a new raffle and assigns the raffleID to the raffleNo
         * (denotes the raffle object number like 1st raffle, 2nd raffle... 12th raffle, etc -- randomly assigned).
         */
        OrganizerRaffle resultRaffle = new OrganizerRaffle(this, raffleName, numOfWinners, endDate);
        Raffle.allRaffles.put(resultRaffle.getRaffleID(), resultRaffle);
        this.orgRaffles.put(resultRaffle.getRaffleID(), resultRaffle);
        return resultRaffle;

    }

    @Override
    public ParticipantRaffle joinRaffle(int raffleID) {
        ParticipantRaffle resultRaffle = new ParticipantRaffle(this, raffleID);
        this.ptcRaffles.put(resultRaffle.getRaffleID(), resultRaffle);
        return resultRaffle;
    }

    // Change 1: Take raffleNo as input parameter.


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

    public void changeRaffleEndDate(LocalDate newEndDate, Raffle rf) {
        /**
         * Change the date for the raffle object to the newEndDate value.
         *
         * @param  rf Raffle object to access the date attribute for the raffle
         * @param  newEndDate date value in the desired format (discuss which format we will use)
         */

        rf.setEndDate(newEndDate);

    }

    // Suggestion: Have a map in Raffle class that will map each raffle ID to a list of strings of rafflePrizes

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


    //    public User generateAndNotifyWinner(List<User> participantList, int raffleNo) {
//        /**
//         * Return and inform the chosen winner for the raffle contest.
//         *
//         * @param  raffleID unique integer value representing each raffle contest
//         * @param  participantList List<main.java.User> representing the participants in the raffle contest
//         * @return Selected Winner (main.java.User object) for the raffle contest
//         */
//
//        //TODO:This code is under the assumption that all users have same raffleID for 1 contest and will
//        // win based on the actions completed.
//
//        if (participantList.isEmpty()){
//            return null; //Later throw exception (custom message like no user found bla bla bla)
//        }
//        else{
//            // List of those participants that are in the given raffleNo (since they can be in multiple raffles)
//            List<User> currentRaffleList = new ArrayList<>();
//            for(User user: participantList){
//                if(raffleNo == user.raffleID){
//                    //Change later to loop over user's list of raffleID's and check for 'raffleNo'.
//                    currentRaffleList.add(user);
//                }
//
//            }
//            if (currentRaffleList.size() == 0){
//                return null; //Later throw custom message exception
//            }
//            else{
//                //TODO: Later select based on actions completed or something
//                int winnerIndex = (int) (Math.random() * (currentRaffleList.size() - 1)); // from 0 to last element.
//
//                //TODO: Add code for informing the selected winner.
//
//                return currentRaffleList.get(winnerIndex); // Returns the selected winner (random) for the raffle
//            }
//
//        }
//
//
//    }
    public void completeTask(int taskID, int raffleID){
        ParticipantRaffle participantRaffle;
        participantRaffle = this.ptcRaffles.get(raffleID);
        ArrayList<Task> tasksToComplete = participantRaffle.getTasksToComplete();
        for(int i = 0; i < tasksToComplete.size(); i++){
            if(tasksToComplete.get(i).getTaskID() == taskID){
                reqTaskLocation = i;
            }
        }
    }

    @Override
    public boolean checkAnswer(int raffleID, int taskID) {
        currentRafflesMap = getPtcRaffles();
        tasks = currentRafflesMap.get(raffleID).getTasksToComplete();
        for(Task x : tasks){
            if (x.getTaskID == taskID){
                return x.verifyAnswer(x.getAnswer(), x.getUserAnswer());
            }
            else{
                return false;
            }
        }
    }

    public void showTasks(int raffleID) {
        currentRafflesMap = getPtcRaffles();
        tasks = currentRafflesMap.get(raffleID).getTasksToComplete();
        for(Task x : tasks){
            System.out.println(x.getQuestion());
        }
    }


    public String getUsername() {
        return username;
    }

    public HashMap<Integer, ParticipantRaffle> getPtcRaffles() {
        return ptcRaffles;
    }

    public HashMap<Integer, OrganizerRaffle> getOrgRaffles() {
        return orgRaffles;
    }


    @Override
    public void changeRaffleEndDate(int raffleID, LocalDate newEndDate) {

    }

    @Override
    public void addTask(int raffleID, String question, String answer) {
        for (Raffle raff : Raffle.allRaffles.values()){
            if (raff.getRaffleID() == raffleID){
                Raffle.allRaffles.get(raffleID).addNewTask(new Task(question, answer));
            }
        }
    }

    public void addOrgRaffle(OrganizerRaffle orgRaffle) {
        this.orgRaffles.put(orgRaffle.getRaffleID(), orgRaffle);
    }

    public void addPtcRaffles(ParticipantRaffle ptcRaffle) {
        this.ptcRaffles.put(ptcRaffle.getRaffleID(), ptcRaffle);
    }
}