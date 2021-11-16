package main.java.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class GetFileToAppend {

    //

    public GetFileToAppend() throws IOException {
    }
    public FileWriter getFile(String filename) throws IOException {
        if (Objects.equals(filename, "raffleTaskDetails")) {
            return new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\raffleTaskDetails.csv", true);
        }
        else if (Objects.equals(filename, "OuserCred")) {
            return new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\OuserCred.csv", true);
        }
        else if (Objects.equals(filename, "raffleUserDetails")) {
            return new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\raffleUserDetails.csv", true);
        }
        else{
            return new FileWriter("E:\\csc207\\skeleton program\\src\\main\\java\\database\\PuserCred.csv", true);
        }

    }
}
