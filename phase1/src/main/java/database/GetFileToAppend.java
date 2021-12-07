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
            return new FileWriter("E:\\U_of_T_Work\\Y2\\CSC207\\FinalProjFinal\\Phase1\\course-project-sedative-skyscrapers\\phase1\\src\\main\\java\\database\\raffleTaskDetails.csv", true);
        }
        else if (Objects.equals(filename, "OuserCred")) {
            return new FileWriter("E:\\U_of_T_Work\\Y2\\CSC207\\FinalProjFinal\\Phase1\\course-project-sedative-skyscrapers\\phase1\\src\\main\\java\\database\\OuserCred.csv", true);
        }
        else if (Objects.equals(filename, "raffleUserDetails")) {
            return new FileWriter("E:\\U_of_T_Work\\Y2\\CSC207\\FinalProjFinal\\Phase1\\course-project-sedative-skyscrapers\\phase1\\src\\main\\java\\database\\raffleUserDetails.csv", true);
        }
        else if(Objects.equals(filename, "raffleDetails")){
            return new FileWriter("E:\\U_of_T_Work\\Y2\\CSC207\\FinalProjFinal\\Phase1\\course-project-sedative-skyscrapers\\phase1\\src\\main\\java\\database\\raffleDetails.csv", true);
        }
        else if(Objects.equals(filename, "raffleWinners")){
            return new FileWriter("E:\\U_of_T_Work\\Y2\\CSC207\\FinalProjFinal\\Phase1\\course-project-sedative-skyscrapers\\phase1\\src\\main\\java\\database\\raffleWinners.csv", true);
        }
        else{
            return new FileWriter("E:\\U_of_T_Work\\Y2\\CSC207\\FinalProjFinal\\Phase1\\course-project-sedative-skyscrapers\\phase1\\src\\main\\java\\database\\PuserCred.csv", true);
        }

    }
}
