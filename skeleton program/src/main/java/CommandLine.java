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

            this.printSeparator();
        }
    }


    public void offerRaffleJoin(){
        System.out.print("Do you want to join a raffle[Y/N] (single character): ");
        char joinRaffle = new Scanner(System.in).next().charAt(0);
        if (joinRaffle == 'Y'){
            Raffle newPtcRaffle = this.joinRaffleWithProvidedInput();
            programPtcRaffles.put(newPtcRaffle.getRaffleID(), newPtcRaffle);
            System.out.println("You just joined a Raffle with the following details: \n" +
                    programPtcRaffles.get(newPtcRaffle.getRaffleID()));

            this.printSeparator();

            // completing a task using helper offerCompleteTask
            if (!programOrgRaffles.get(newPtcRaffle.getRaffleID()).getTaskList().isEmpty()) {
                offerCompleteTask(newPtcRaffle);
            }
        }
    }

    public void offerCompleteTask(Raffle ptcRaffle){
        System.out.print("Do you want to complete this raffle's task[Y/N] (single character): ");
        char completeTask = new Scanner(System.in).next().charAt(0);
        if (completeTask == 'Y'){
            // since there is a single task in the task list, we access it by just indexing 0 for now
            System.out.println("The task question is: " + ptcRaffle.getTaskList().get(0).getQuestion());
            System.out.print("Enter your answer (correct answer would be the exact same as the " +
                    "correct answer input when creating the task above): ");
            String userAns = new Scanner(System.in).nextLine();
            // setting the Task answer as indicated by the user
            programPtcRaffles.get(ptcRaffle.getRaffleID()).getTaskList().get(0).setUserAnswer(userAns);

            // since there is a single task id which is default to 0, we access it this way for now
            boolean result = user0.checkAnswer(ptcRaffle.getRaffleID(), 0);

            this.printSeparator();

            if (result){
                System.out.println("You have successfully completed this task! The resulting task object" +
                        " now has the following details: \n" +
                        programPtcRaffles.get(ptcRaffle.getRaffleID()).getTaskList().get(0));

                // since there is a single task id which is default to 0, we access it this way for now
                user0.completeTask(0, ptcRaffle.getRaffleID());  // task gets removed from tasks to be completed
                System.out.println("The resulting raffle now has the following details:" +
                        " \n" +  programPtcRaffles.get(ptcRaffle.getRaffleID()));
            } else {
                System.out.println("That answer was wrong, you'll get 'em next time!");
            }

            // changes involving how to access tasks will come around in phase1, once we get the database set up
        }
    }


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


    public Raffle joinRaffleWithProvidedInput(){
        // get input
        System.out.print("Enter the ID for the raffle (single number): ");
        int raffleID = new Scanner(System.in).nextInt();

        return this.user0.joinRaffle(raffleID);
    }


    public Task createTaskWithProvidedInput(){
        // get input
        System.out.print("Enter the question for the task (>=1 words): ");
        String question = new Scanner(System.in).nextLine();
        System.out.print("Enter the answer for such question (>=1 words): ");
        String answer = new Scanner(System.in).nextLine();

        return new Task(question, answer);
    }

    public void printSeparator(){
        System.out.println("\n--------------------------------------------------------------------\n");
    }

    public boolean offerExitProgram() {
        System.out.print("Do you want to exit the program[Y/N] (single character): ");
        char quitProgram = new Scanner(System.in).next().charAt(0);
        return quitProgram != 'Y';
    }


    public HashMap<Integer, Raffle> getOrgProgramRaffles(){
        return this.programOrgRaffles;
    }

//    public HashMap<Integer, Raffle> getPtcProgramRaffles(){
//        return this.programPtcRaffles;
//    }

}

