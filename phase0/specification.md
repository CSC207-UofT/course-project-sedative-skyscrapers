Domain: Giveaway application

Brief Descriptiton:
Online raffle platform allowing for brands/organizers to put up items for prizes and organize raffles to have other users (participants) complete a set of actions to have a chance to enter a random draw in which their tickets can be selected to win a prize.

Main Features: Login page for users and organizers (same login for all users, you just see a special tag with the raffle pages if you are the organizer of that specific raffle); check your raffles page (MainPage) both as organizer and participant; enter raffle page (SubPage) both as organizer and participant; create raffle; and within raffle modifications for organizers (setting up raffle details).

Inside raffle page: 

- As a participant: enter raffle page by entering raffle code, complete action (displays action to be completed), enter answer (to complete action), check answer (displays if answer submitted was correct).
- As an organizer: enter raffle creator by creating raffle, add raffle rules text file, add raffle actions to complete and their weight (select a specific method and it automatically generates a pool of prompts related to it), change weight of action, change end date, generate random winners, notify winners (add new page on MainPage page and create a message to notify a user if they won a giveaway).
- System (functionalities within raffle page not precisely for either type of user): calculate all entries in a raffle, calculate the amount of entries from one user, display time left before raffle ends.

*Note on "actions" completed by users: We plan to start off with simple actions like solving equations, or typing text displayed on the console, and as we progress through the project, we will try to extpand the range of actions that can be performed.

Entities: 	# the main pages in the program
- User (parent class to Organizer and Participant)
- MainPage (where user is thrown in after login)
- SubPage [accessed through the main page, is a parent to RafflePage (manages everything within a raffle) and ResultPage (controls the series of steps from the moment winners are drawn) classes]
- Raffle

Use Case Classes: 	# just some examples of invoking entities
- Login (for MainPage)
- CreateRules, CreateActions, CompleteActions, and other similar functions that can be completed within a SubPage
- CreateUser (for MainPage)
- CreateRaffle, JoinRaffle (for MainPage)

Controller: 	# calling the use cases
- ReceiveAction (getting text from command line and calling use cases like Login or JoinRaffle based on that keyboard input)

Basic command line interface: after prompt for login user generated input through keyboard, typing functions like EnterSubpage(SubpageName), or CreateRaffle(raffleName, raffleEndDate), to enter the various subpages and then perform actions specific to those subpages there.
