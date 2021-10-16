package main.java;

import java.time.LocalDate;
import java.util.Scanner;

// this is the file to be run for the command line interface to start working
public class main {

    public static void main(String[] args) {

        //todo: pull out onto another class cmdLine, to only call methods here in main

        System.out.print("Welcome, please enter your account information below :) \nUsername: ");
        String username = new Scanner(System.in).nextLine();
        // since we still dont have our database, the setup of the proper login system is for phase1
        // System.out.print("Password: ");
        // String pswd = new Scanner(System.in).nextLine();
        User user1 = new User(username);

        boolean running = true;

        while(running){
            System.out.print("Do you want to create a raffle[Y/N]: ");
            char createRaffle = new Scanner(System.in).next().charAt(0);
            if (createRaffle == 'Y'){
                Raffle newOrgRaffle = createRaffleWithProvidedInput(user1);
                System.out.println("You just created a Raffle with the following details: \n" + newOrgRaffle);
                System.out.print("Do you want to add a Task[Y/N]: ");
                char addTask = new Scanner(System.in).next().charAt(0);
                if (addTask == 'Y'){
                    Task newTask = createTaskWithProvidedInput();
                    newOrgRaffle.addNewTask(newTask);
                    System.out.println("You just created a Task with the following details: \n" + newTask);
                }
            }
            
            if (!Raffle.allRaffles.isEmpty()){
                System.out.print("Do you want to join a raffle[Y/N]: ");
                char joinRaffle = new Scanner(System.in).next().charAt(0);
                if (joinRaffle == 'Y'){
                    Raffle newPtcRaffle = joinRaffleWithProvidedInput(user1);
                    System.out.println("You just joined a Raffle with the following details: \n" + newPtcRaffle);
//                    System.out.print("Do you want to take a look at this raffle's tasks[Y/N]: ");
//                    char checkTasks = new Scanner(System.in).next().charAt(0);
//                    if (checkTasks == 'Y'){
//                        System.out.println();
                }
            }

            System.out.print("Do you want to exit the program[Y/N]: ");
            char quitProgram = new Scanner(System.in).next().charAt(0);
            if (quitProgram == 'Y'){
                running = false;
            }  // else loop back up and go through all options again

        }
    }

    public static Raffle createRaffleWithProvidedInput(User userObject){
        // get input
        System.out.print("Enter the name for the raffle: ");
        String raffleName = new Scanner(System.in).nextLine();
        System.out.print("Enter the number of winners for the raffle: ");
        int numOfWinners = new Scanner(System.in).nextInt();
        System.out.print("Enter the year when this raffle ends: ");
        int endYear = new Scanner(System.in).nextInt();
        System.out.print("Enter the month when this raffle ends: ");
        int endMonth = new Scanner(System.in).nextInt();
        System.out.print("Enter the day when this raffle ends: ");
        int endDay = new Scanner(System.in).nextInt();

        return userObject.createRaffle(raffleName, numOfWinners,
                LocalDate.of(endYear, endMonth, endDay));
    }

    public static Raffle joinRaffleWithProvidedInput(User userObject){
        // get input
        System.out.print("Enter the ID for the raffle (int): ");
        int raffleID = new Scanner(System.in).nextInt();

        return userObject.joinRaffle(raffleID);
    }

    public static Task createTaskWithProvidedInput(){
        // get input
        System.out.print("Enter the question for the task (str): ");
        String question = new Scanner(System.in).nextLine();
        System.out.print("Enter the answer for such question (str): ");
        String answer = new Scanner(System.in).nextLine();


        return new Task(question, answer);
    }

}

