package main.java.TaskComponent;
import main.java.Task;
//import main.java.Helpers.TaskIdGenerator;

public class CreateTaskUseCase {

        private Task task;
        //private RaffleIdGenerator idGenerator;
        //private static final char entityCode = 'R';
        //private ArrayList<String> takenIds;

        /*public enum CreationResult {
            SUCCESS, FAILURE
        }

         */

        public CreateTaskUseCase(String name, String description, String link){
            this.task = new Task(name, description, link);
            //this.takenIds = takenIds;
            //this.idGenerator = new RaffleIdGenerator(this.takenIds);
            // save the raffle object along with the raffleID in database
        }

//    public void StoreCreatedRaffle(){
//
//        // store in database
//    }

        /*public CreationResult runRaffleCreation(){
            ArrayList<Integer> takenRaffleIdNums = idGenerator.takenNumList(CreateRaffleUseCase.entityCode);
            // generate id from use case
            String raffleId = idGenerator.generateEntityId(CreateRaffleUseCase.entityCode, takenRaffleIdNums);

            if (!this.takenIds.contains(raffleId)){
                this.raffle.setRaffleId(raffleId);  // always true based on RaffleIdGenerator
                // update  takenIds
                this.takenIds.add(raffleId);
                return CreationResult.SUCCESS;
            }

            return CreationResult.FAILURE;

        }
*/
        public Task getTask() {
            return task;
        }
    }
}
