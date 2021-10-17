# Project specification summary:

At phase0, the program should be able to allow the user to interact with the core components of giveaways. The user being the central piece should be able to take on different roles such as a participant or an organizer of a raffle, therefore allowing them to explore how the creation and joinning of raffles processes work, while also giving them the chance to see how a participant of a raffle gets to interact with what organizers create through the tasks objects. So basically we want to show the user how everything related to setting up a raffle as an organizer, and getting the participants started on the processes of becoming accomplished/valid participant, works.


#CRC summary:

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

#Scenario Walkthrough Summary:
- User runs main file, is greeted with prompts from CLI to input details, creating and/or joining raffles
- Once Details have been input, program generates raffle object if user chose to create one, generates ID and displays details of created raffle object on the CLI
- User can then choose to join raffle themselves by referencing raffleIDs
- Once joined, user can complete tasks (answer questions in this case)
- Once Tasks are completed, user can terminate program

# Skeleton Program Summary:
- CommandLine

#Questions Group is Struggling With:
One of the main questions the group is struggling with is identifying a solution for having different participants take part in the contest via the console. The limitation at the moment is having only 1 participant take part in the raffle contest. Consequently, certain attributes like ‘numWinners’ and ‘prizes’ for the contest have not been put to good use. Another question the group is working to resolve is finding ways of reducing the dependencies of certain entities from one another. The current solution in mind is to have a class dedicated to manage these entities and have the different manager classes communicate with one another. We believe once we formalize the clean architecture principle, we can resolve this issue.

What has worked well so far with current design:
Splitting up entities into different sub-entities like Participatable and Organizable has made it easier to implement and use these classes. For instance, the Raffle class depends on the Organizable entity, and thus making the decision to separate Participants and Organizers rather than having them in a single class has made design changes a lot easier for Organizable and Raffle class as there were lots of changes being made with respect to the CRC model
