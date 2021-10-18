# Project specification summary:

At phase0, the program should be able to allow the user to interact with the core components of giveaways. The user being the central piece should be able to take on different roles such as a participant or an organizer of a raffle, therefore allowing them to explore how the creation and joinning of raffles processes work, while also giving them the chance to see how a participant of a raffle gets to interact with what organizers create through the tasks objects. So basically we want to show the user how everything related to setting up a raffle as an organizer, and getting the participants started on the processes of becoming accomplished/valid participant, works.


## CRC summary:

**Note**: our project does not satisfy many of the specifications that it should in order to align with clean architecture, which is why some things that are listed as Use case classes are in phase0 just methods to the entities themselves. What's listed here is the way it should be after phase1, since we didn't want an extremely large phase0.

Entities: # the main pages in the program
- User (parent class to Organizer and Participant)
- Raffle
- Task

Use Case Classes: # just some examples of invoking entities
- CreateRaffle
- JoinRaffle
- AddTask
- OrganizerRaffle
- ParticipantRaffle

Controller and Command line interface along with Main:
- CommandLine class: which takes inputs from user and sends it to be processed to the other respective classes.

## Scenario Walkthrough Summary:
- User runs main file, is greeted with prompts from CLI to input details, creating and/or joining raffles
- Once Details have been input, program generates raffle object if user chose to create one, generates ID and displays details of created raffle object on the CLI
- User can then choose to join raffle themselves by referencing raffleIDs
- Once joined, user can complete tasks (answer questions in this case)
- Once Tasks are completed, user can terminate program

# Skeleton Program Summary:
- CommandLine.java: Contains the CommandLine class that takes inputs from a user and sends it onto relevant classes
- Main.java: File in charge of running the program
- Organizable.java: contains the interface that interacts with User objects for organizers of raffles
- OrganizerRaffle: contains the subclass of Raffle that is specifically created by organizers
- Participable: contains the interface that interacts with User objects for participants in raffles
- ParticipantRaffle: contains the subclass of Raffle that is specifically created for participants
- Raffle: Contains the Raffle class that is created by organizers for participants
- Task: contains the Task class that is set by organizers and completed by users
- User: an Entity that represents the end consumers who interact with the program

## Questions Group is Struggling With:
One of the main questions the group is struggling with is identifying a solution for having different participants take part in the contest via the console. The limitation at the moment is having only 1 participant take part in the raffle contest. Consequently, certain attributes like ‘numWinners’ and ‘prizes’ for the contest have not been put to good use. Another question the group is working to resolve is finding ways of reducing the dependencies of certain entities from one another. The current solution in mind is to have a class dedicated to manage these entities and have the different manager classes communicate with one another. We believe once we formalize the clean architecture principle, we can resolve this issue.

## What has worked well so far with current design:
Splitting up entities into different sub-entities like Participatable and Organizable has made it easier to implement and use these classes. For instance, the Raffle class depends on the Organizable entity, and thus making the decision to separate Participants and Organizers rather than having them in a single class has made design changes a lot easier for Organizable and Raffle class as there were lots of changes being made with respect to the CRC model

## What each group member has been working on and plans to work on next:
- Aakash: Contributed to questions on the project report and implemented relevant methods in User class from the Organizable interface. Plans to work on implementing the Organizer Manager class and make other extensions to the code.
- Khushaal: Finished the implementation and comments in the User class and Task class and worked on the interface Participable and Organizable. Plans to complete the implementation of Task.java. and make other extensions in the code.
- Michele: Mainly worked on the creation of the Raffle entity and its subclasses (along with Nischal), as well as taking part in combining everything when it comes to some of the methods in the CommandLine class. Trying to lighten the load for phase0, we ended up running over clean architecture and its principles, especially for this Raffle entity, so the main plan after this phase0 submission is to start working on dividing the Raffle objects and introducing proper use case classes and controllers to make the code easier to navigate and expand.
- Nischal: Worked on implementation of Raffle entity and its subclasses, its test cases and internal design of the interaction between classes. Also helped with CommandLine, organizable, User and written reports (ProgressReport etc). 
- Garv:  Worked on implementing the methods in the User.java and Task.java. Also helped to make the Participable interface and make comments on it. Plans to make various extensions in the interface and Task.java and incorporate the commented-out code in the program.
- Shih-Hua: Worked on the method signatures of Organizable interface. Plans to make implement the future extensions that are already added in the Organizable interface. Also need to work on reorganizaing some of the methods in Organizable interface to a higher-level class so the design follows the Dependency rule of Clean Architecture.
- Varun: Worked on some methods in the User.java class, helped to make the Participable.java interface and plans to work on the GUI of the project.

