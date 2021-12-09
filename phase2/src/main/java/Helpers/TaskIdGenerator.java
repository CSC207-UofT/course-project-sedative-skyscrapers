package main.java.Helpers;

import java.util.ArrayList;

public class TaskIdGenerator implements IEntityIdGenerator {
    private final ArrayList<String> takenTaskIds;

    public TaskIdGenerator(ArrayList<String> takenIds) {
        this.takenTaskIds = takenIds;
    }

    @Override
    public String generateEntityId(char entityCode, ArrayList<Integer> takenTaskNums) {
        int taskNumber = generateIdNum(takenTaskNums);
        String taskCode = Character.toString(entityCode);

        return taskCode + taskNumber;

    }

    @Override
    public ArrayList<Integer> takenNumList(char entityCode) {

        ArrayList<Integer> numList = new ArrayList<>();

        for (String takenId : this.takenTaskIds) {
            if (takenId.equals("")) {
                continue;
            }
            String[] takenIdNum = takenId.split(Character.toString(entityCode));
            int idNumber = Integer.parseInt(takenIdNum[1]);
            numList.add(idNumber);
        }

        return numList;
    }

    public int generateIdNum(ArrayList<Integer> takenRaffleNums) {
        int taskNumber = (int) (8999 * Math.random() + 1000);

        while (takenRaffleNums.contains(taskNumber)) {
            taskNumber = (int) (8999 * Math.random() + 1000);  // recalculate
        }

        return taskNumber;
    }

}
