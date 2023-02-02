# Chess
* Trello: https://trello.com/b/AfxsN1Nz/chess
* Aufgabenstellung : https://www.htl-steyr.ac.at/intern/wiki/doku.php?id=el-it:itp2:umsetzung_it_projekte:exercises:javafxspiel&s[]=itp&s[]=projekt

## Create Docker Container/Import Database
1. Open Command Line in Root Folder of this Project and execute "docker-compose up -d"

![image](https://user-images.githubusercontent.com/83589343/208859426-0a6d2a25-7402-43be-a7ba-09f9a009d615.png)

2. Open the Docker Container in Datagrip with the following settings (Password: '4ahitn'):

![image](https://user-images.githubusercontent.com/83589343/208865770-c06325ad-6bfc-4dd8-90b3-3fa250f65b2a.png)

3. Right Click the 4ahitn database and click on "Run SQL Script"

![image](https://user-images.githubusercontent.com/83589343/208866265-91ea7e6f-35b4-42d5-a3bb-ad54739a91b5.png)

4. Select the "init.sql" file

![image](https://user-images.githubusercontent.com/83589343/208866833-1c3acb60-68cc-4b20-8872-8015040bec51.png)

5. Even if you get an error, the table should be added to the database

![image](https://user-images.githubusercontent.com/83589343/214510529-daf33236-0d10-442a-9ea2-442eee16d23c.png)



## Game instructions:
First player 1 has to log in or register, then the same scene is called again for player 2. If the name or password is wrong or does not exist, the game is stopped immediately, also it is checked if two different players log in, if this is not the case, then the scene is called until there are two different players. 

Then the time and theme are selected. 

The player who logs in first always starts the game and always plays with white pieces. Player 2 plays with black pieces. As soon as one clicks on a piece the marked fields appear, on which a move would be possible.

If resign is pressed, the player who is currently on the move has given up and thus lost. If a draw is offered, the other player must also accept the draw, otherwise the game continues. 

The winning message is announced with an alert, which can then be used to either end the game or start a new game.



