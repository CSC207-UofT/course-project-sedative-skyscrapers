Readme:

Before running the code:
Since the Email notification requires the external javax.mail and activation libraries, these need to be added to the project structure
After cloning the repo, please open the file under course-project-sedative-skyscrapers -> phase2 -> src -> main -> Helpers -> SendEmail.java
Check import statements of the file. If the imports are not being resolved, please do the following
1. In the top right hand corner of IntelliJ click on file
2. In the drop down menu that opens, click on project structure
3. In the window that opens, on the top left hand side, click on the Modules tab under project settings
4. Find the + symbol below the Module SDK field text and click on it
5. Select JARs or Directories
6. Navigate to the files course-project-sedative-skyscrapers -> phase2 -> RuntimeRequirements -> and click on activation.jar
7. Repeat to find the file javax.mail.jar in the same location and add it
8. Once both appear in the modules window, click apply, and the import issue should be resolved. In SendEmail.java, the code should not show any warnings of unresolved libraries

To run the code:
Navigate to course-project-sedative-skyscrapers -> phase2 -> main.java and run the main.main method. The GUI should open in a few seconds

Working with the program
Part 1 - Becoming an Organizer
1. After creating an account with an organizer, click on create raffles from the main page
2. Create raffles with any specifications. Please keep in mind the following
Req. 1 - Raffle End Date field must be later than the current date
Req. 2 - Any task weblink should be a properly working URL of the form https://www.youtube.com. Directly inputting youtube.com will not work
3. Once raffles are created, logout as an organizer.

Part 2 - Becoming a Participant
1. From the main login screen, create a new account via Don't have an account as a participant. Please keep in mind the following
Req 1. - the email address of a participant must be a properly working email address, ideally an email address you can access to view notifications
2. Once account creation is done, please search the raffle you created as an organizer. Keep in mind that the search input names need to match exactly to the raffle name. 
3. Once the raffle appears as a search option, click on it and then click on the join raffle page.
4. you can now click on the individual complete tasks buttons that appear next to the tasks once you've joined. Clicking on these buttons should open the links in your default web browser
5. once all tasks are completed (links opened), logout as a participant

Part 3 - Generating winners
1. log in as the organizer account previously made
2. open the raffle you created
3. the participant profile you created should show as an ID who is enrolled in the raffle
4. click on the find winners button.
5. Winners declared should have the particpant ID of the only participant in the raffle, if all tasks were completed.
6. Check your email (participant email that was registered in part 2) for a message on your winning of prizes!

A video link of the entire functionality can be found here: https://drive.google.com/file/d/1ewA8Y_ZuNIfmhO1-dEv5ztD45jfnGwCv/view?usp=sharing

Overall notes:
If required, multiple particiapnt profiles can be created to join the raffle. If more than one participant completes all tasks, winners will be generated at random from those participants who have completed all tasks
Multiple raffles can be created by the same organizer
Participants can join multiple raffles

We hope you enjoy using our program! Thank you!
