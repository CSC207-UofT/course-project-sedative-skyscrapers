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
            return new FileWriter("/Users/varun/IdeaProjects/course-project-sedative-skyscrapersEnd/phase1/src/main/java/database/raffleTaskDetails.csv", true);
        }
        else if (Objects.equals(filename, "OuserCred")) {
            return new FileWriter("/Users/varun/IdeaProjects/course-project-sedative-skyscrapersEnd/phase1/src/main/java/database/OuserCred.csv", true);
        }
        else if (Objects.equals(filename, "raffleUserDetails")) {
            return new FileWriter("/Users/varun/IdeaProjects/course-project-sedative-skyscrapersEnd/phase1/src/main/java/database/raffleUserDetails.csv", true);
        }
        else if(Objects.equals(filename, "raffleDetails")){
            return new FileWriter("/Users/varun/IdeaProjects/course-project-sedative-skyscrapersEnd/phase1/src/main/java/database/raffleDetails.csv", true);
        }
        else if(Objects.equals(filename, "raffleWinners")){
            return new FileWriter("/Users/varun/IdeaProjects/course-project-sedative-skyscrapersEnd/phase1/src/main/java/database/raffleWinners.csv", true);
        }
        else{
            return new FileWriter("/Users/varun/IdeaProjects/course-project-sedative-skyscrapersEnd/phase1/src/main/java/database/PuserCred.csv", true);
        }

    }
}
