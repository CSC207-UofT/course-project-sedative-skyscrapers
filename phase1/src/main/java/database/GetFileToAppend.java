package main.java.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class GetFileToAppend {
    FileWriter raffleTask = new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\raffleTaskDetails.csv", true);
    FileWriter raffleUser = new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\raffleUserDetails.csv", true);
    FileWriter Ouser = new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\OuserCred.csv", true);
    FileWriter Puser = new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\PuserCred.csv", true);

    public GetFileToAppend() throws IOException {
    }

    public FileWriter getFile(String filename) {
        if (Objects.equals(filename, "raffleTaskDetails")) {
            return raffleTask;
        }
        else if (Objects.equals(filename, "OuserCred")) {
            return Ouser;
        }
        else if (Objects.equals(filename, "raffleUserDetails")) {
            return Ouser;
        }
        return Puser;
    }
}
