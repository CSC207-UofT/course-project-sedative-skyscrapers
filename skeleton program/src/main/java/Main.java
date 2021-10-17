package main.java;


import java.util.Scanner;

// this is the file to be run for the command line interface to start working
public class Main {

    public static void main(String[] args) {

        // setup
        System.out.print("Welcome, please enter your account information below :) \nUsername (single word): ");
        String username = new Scanner(System.in).nextLine();
        // since we still dont have our database, the setup of the proper login system is for phase1
        // System.out.print("Password: ");
        // String pswd = new Scanner(System.in).nextLine();
        User user1 = new User(username);
        CommandLine cmdLine = new CommandLine(user1);


        boolean running = true;

        while(running){
            cmdLine.offerRaffleCreation();  // prompt to create Raffle

            if (!cmdLine.getOrgProgramRaffles().isEmpty()){
                cmdLine.offerRaffleJoin();
            }

            running = cmdLine.offerExitProgram();
            // if running still false, loop back up and go through all options again
        }
    }

}

