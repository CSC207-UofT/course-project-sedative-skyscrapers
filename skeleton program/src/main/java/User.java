package main.java;

import main.java.Organizable;
import main.java.Participable;

public class User implements Participable, Organizable {
    private int raffleID;

    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void joinRaffle(int raffleID) {

    }

    @Override
    public void createRaffle() {

    }

    public int getRaffleID() {
        return raffleID;
    }

    public void setRaffleID(int raffleID) {
        this.raffleID = raffleID;
    }
}
