public class User implements Participable, Organizable {
    private int raffleID;

    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void joinRaffle(int raffleID) {

    }

}
