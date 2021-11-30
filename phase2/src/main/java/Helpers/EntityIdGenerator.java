package main.java.Helpers;

import java.util.ArrayList;

public class EntityIdGenerator implements IEntityIdGenerator {
    private ArrayList<String> takenRaffleIds;
//    private String username;

    public EntityIdGenerator(ArrayList<String> takenIds){
        this.takenRaffleIds = takenIds;
//        this.username = userId;
    }

    @Override
    public String generateEntityId(char entityCode, ArrayList<Integer> takenRaffleNums){
        int raffleNumber = generateIdNum(takenRaffleNums);
        String raffleCode = Character.toString(entityCode);  // String so far is just the entityCode

//        return this.username + ":" + raffleCode + raffleNumber;
        return raffleCode + raffleNumber;  // returns pure raffleId (without usernames involved)
    }

    @Override
    public ArrayList<Integer> takenNumList(char entityCode){

        ArrayList<Integer> numList = new ArrayList<>();

        for (String takenId : this.takenRaffleIds){
            String[] takenIdNum = takenId.split(Character.toString(entityCode)); // get the number on its own
            int idNumber = Integer.parseInt(takenIdNum[1]);
            numList.add(idNumber);
        }

        return numList;
    }

    public int generateIdNum(ArrayList<Integer> takenRaffleNums){
        int raffleNumber = (int)(8999 * Math.random() + 1000);

        while (takenRaffleNums.contains(raffleNumber)){
            raffleNumber = (int)(8999 * Math.random() + 1000);  // recalculate
        }

        return raffleNumber;

    }


}
