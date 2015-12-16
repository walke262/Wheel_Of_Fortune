Million Dollar Wheel is an app inspired by the classic TV show Wheel of Fortune. It supports three players per game and it communicates via Sockets and TCP/IP. Registered players can keep track of scores and total winnings. 

In order to play this game, 2 servers must be running prior to launching the game: 
*Authentication Server (AuthenticationServer.java)
*Game Server (GameServer.java)

The authentication server reads a text file located in the root directory to parse registered user’s profiles and logins. 
The game server supports the logic behind the game. It supports multiple clients to connect. 


Login Screen:
Once the two servers are running, the main application, MainMenu.java can be launched. From there, you will have the options to login, play as a guest, or to create a server. To create a new registered user, click on “Create a new user” and fill out a username and a password. From there, that newly created account can be authenticated with by entering the username or password into the text boxes. If you wish to play as a guest and to not create an account, simply press “Play as Guest”. 

Main Menu: 
At the main menu, you have 3 options as a registered user and two as a guest user. 
* Connect to Game - Allows you to connect to an existing game
* View Leaderboards - Allows you to view the top scores for registered users
* View Personal stats (Not available for guest users) - Allows registered users to view stats about themselves. 

Connecting to a new game:
Enter the IP address of the server, followed by the port number which the game is being hosted on. An example of this would be 127.0.0.1:1337

