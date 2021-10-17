package main.java;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class CommandLine {
    private final User user0;
    private HashMap<Integer, Raffle> programOrgRaffles;
    private HashMap<Integer, Raffle> programPtcRaffles;

    /**
     * Initializer for a CommandLine object
     * @param userObject The User object to start up and run the program
     */
    public CommandLine(User userObject){
        this.user0 = userObject;
        programOrgRaffles = new HashMap<>();
        programPtcRaffles = new HashMap<>();
    }

    // todo
    public void offerRaffleCreation(){
        System.out.print("Do you want to create a raffle[Y/N] (single character): ");
        char createRaffle = new Scanner(System.in).next().charAt(0);
        if (createRaffle == 'Y') {
            Raffle newOrgRaffle = this.createRaffleWithProvidedInput();
            programOrgRaffles.put(newOrgRaffle.getRaffleID(), newOrgRaffle);
            System.out.println("You just created a Raffle with the following details: \n" +
                    programOrgRaffles.get(newOrgRaffle.getRaffleID()));

            System.out.print("Do you want to add a Task[Y/N] (single character): ");
            char addTask = new Scanner(System.in).next().charAt(0);
            if (addTask == 'Y') {
                Task newTask = this.createTaskWithProvidedInput();
                programOrgRaffles.get(newOrgRaffle.getRaffleID()).addNewTask(newTask);  // update taskList
                System.out.println("You just created a Task with the following details: \n" + newTask);
            }
        }
    }

    // todo
    public void offerRaffleJoin(){
        System.out.print("Do you want to join a raffle[Y/N] (single character): ");
        char joinRaffle = new Scanner(System.in).next().charAt(0);
        if (joinRaffle == 'Y'){
            Raffle newPtcRaffle = this.joinRaffleWithProvidedInput();
            programPtcRaffles.put(newPtcRaffle.getRaffleID(), newPtcRaffle);
            System.out.println("You just joined a Raffle with the following details: \n" +
                    programPtcRaffles.get(newPtcRaffle.getRaffleID()));
//                    System.out.print("Do you want to take a look at this raffle's tasks[Y/N]: ");
//                    char checkTasks = new Scanner(System.in).next().charAt(0);
//                    if (checkTasks == 'Y'){
//                        System.out.println();
//            System.out.print("Do you want to complete this Raffle's task[Y/N] (single character): ");
//            char completeTask = new Scanner(System.in).next().charAt(0);
//            if (completeTask == 'Y'){
//
//            }
        }
    }

    // todo
    public Raffle createRaffleWithProvidedInput(){
        // get input
        System.out.print("Enter the name for the raffle (>=1 words): ");
        String raffleName = new Scanner(System.in).nextLine();
        System.out.print("Enter the number of winners for the raffle (single number): ");
        int numOfWinners = new Scanner(System.in).nextInt();
        System.out.print("Enter the year when this raffle ends (single number): ");
        int endYear = new Scanner(System.in).nextInt();
        System.out.print("Enter the month when this raffle ends (single number [1-12]): ");
        int endMonth = new Scanner(System.in).nextInt();
        System.out.print("Enter the day when this raffle ends: (single number[1-31])");
        int endDay = new Scanner(System.in).nextInt();

        return this.user0.createRaffle(raffleName, numOfWinners,
                LocalDate.of(endYear, endMonth, endDay));
    }

    // todo
    public Raffle joinRaffleWithProvidedInput(){
        // get input
        System.out.print("Enter the ID for the raffle (single number): ");
        int raffleID = new Scanner(System.in).nextInt();

        return this.user0.joinRaffle(raffleID);
    }

    // todo
    public Task createTaskWithProvidedInput(){
        // get input
        System.out.print("Enter the question for the task (>=1 words): ");
        String question = new Scanner(System.in).nextLine();
        System.out.print("Enter the answer for such question (>=1 words): ");
        String answer = new Scanner(System.in).nextLine();

        return new Task(question, answer);
    }

    // todo
    public boolean offerExitProgram() {
        System.out.print("Do you want to exit the program[Y/N] (single character): ");
        char quitProgram = new Scanner(System.in).next().charAt(0);
        return quitProgram != 'Y';
    }

    public HashMap<Integer, Raffle> getOrgProgramRaffles(){
        return this.programOrgRaffles;
    }

    public HashMap<Integer, Raffle> getPtcProgramRaffles(){
        return this.programPtcRaffles;
    }

}

