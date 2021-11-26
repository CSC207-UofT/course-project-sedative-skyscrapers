package main.java.Helpers;

import java.util.ArrayList;

public interface EntityIdGenerator {

    String generateEntityId(char entityCode, ArrayList<Integer> takenIdNums);
    /* method to be implemented by all use case classes which generate entity ids:
    The way it works is that it takes the entity code (Raffle:R, Task: T, User: U)
    and generates a random integer from 1000 to 9999, which is to become the suffix of the
    respective entityCode in the resulting String
     */

    ArrayList<Integer> takenNumList(char entityCode);
    /* idea is for this to be a helper we call before making the call to generateEntityId
    since it gives us only the Number parts of the String ids which are fed to
    generateEntityId
     */
}
