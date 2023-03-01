#Dungeon

####Overview
   
This is an adventure game based on a dungeon, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them. 

####Features

  * Support two types of dungeon represented on a 2-D grid, wrapping dungeon and non-wrapping dungeon. In wrapping dungeons, locations may "wrap" to the one on the other side of the grid.
  
  * A location can further be classified as tunnel (which has exactly 2 entrances) or a cave (which has 1, 3 or 4 entrances).
  
  * Support clients to create different sizes of dungeons with different degrees of interconnectivity.
  
  * There may be three types of treasure in the caves: diamonds, rubies, and sapphires.
 
  * The client is able to ask the model to add treasure to specified percentage of caves. A cave may have more than one treasure.
  
  * The game will randomly select two caves as start and end points respectively. The path between the start and the end locations is at least of length 5.
  
  * A player to enter the dungeon at the start.
  
  * The dungeon in the game has at least one path that from every cave in the dungeon to every other cave in the dungeon.
  
  * The game provides a description of the player that, includes a description of what treasure the player has collected, the origin point, the end point and the current point of the player and the locations the player has been passed through.
  
  * The game provides a description of the player's location that includes a description of treasure in the room and the possible moves (north, east, south, west) that the player can make from their current location.
  
  * Support the player to move from one location to another.
  
  * Support the player to pick up the treasure that is located in their same location.
  
  
####How To Run

java -jar /res/project3.jar

 - java -jar $path$/$name$.jar
 
####How to Use the Program

 - start playing the game by inputing the command including dungeon type, the number of dungeon rows, the number of dungeon columns, the degree, the percentage of treasure. For example: wp 6 6 20 20 (create a wrapping dungeon with the size of 6 * 6 and the degree of 20 and the treasure percentage of 20%) 
or inputing: nwp 6 6 0 100(create a non-wrapping dungeon with the size of 6 * 6 and the degree of 0 and the treasure percentage of 100%).
 
 - input 'enter' to enter the dungeon.
 
 - input 'N','S','W','E' to move towards north, south, west and east respectively
 (notice that the moving command should be in uppercase)
 
 - input 'p' to pick up treasure in the cave.
 
 - input 'q' to quit the game. 

###Description of Examples

  Run1-NonWrappingDungeon(0 degree).txt
  * this example run shows a 8 * 8 non-wrapping dungeon with 0 degree and 90% percent of the caves have treasure
  * it shows the player is able to move from the start location to the end location
  * introduction information
  * read a message from the user via keyboard to create a dungeon, 'nwp 8 8 0 90', which means creating a non-wrapping dungeon with the size of 8 * 8 and the degree is 0 and there will be 90% of the caves that have treasure in them.
  * print the dungeon without player in it
  * told you that the player should start from location 3 to location 59
  * read a message from the user via keyboard to enter the dungeon,'enter'
  * print the dungeon on the screen with the player at the start point 3
  * print the player's location description, including the position of the player(at the row 1 and column 4), the location no(3), the type of the location(a cave or a tunnel), the treasure information in the location and possible moves(it can be north,south,west,east)
  * print player's description including its origin point(start point), and its destination(end point) and its current location, and the treasure it has collected and the locations it has passed through
  * read a message from the user via keyboard to move:'E'
  * print the dungeon with the latest location of the player
  ...
  * at last it shows the game is over and print the latest player's description and its location description
  
   Run2-NonWrappingDungeon(20 degree).txt
       
    
    
  * this example run shows a 6 * 6 dungeon with 20 degrees and 20% of the caves have treasure in them 
  
  * it shows the player is able to move to every location in the dungeon
  
  * it has  almost the same commands with the last one but it shows how a non-wrapping dungeon with 20 degrees looks like. It is much easier for the player to reach the end location than the last dungeon with 0 degree
  * at line 803, it shows that what would happen if the client enter an invalid move
  
  
  Run3-WrappingDungeon(0 degree).txt
  
  * it shows a 6 * 6 wrapping dungeon with 0 degree and 100% of the caves in the dungeon have treasure in them
  
  * it shows a player is able to move from start location to end location
  
  * at line 59, it shows how to pick up treasure in the room and line 53 shows the player's treasure bag is empty and after picking up treasure, it has treasure in it(at line 85), and since the treasure has been pick up so in the location info you can see the treasure bag has been empty(at line 78)
  
  Run4-WrappingDungeon(20 degree).txt
  
  * it shows a 6 * 6 wrapping dungeon with 10 degrees and 20% of the caves in the dungeon have treasure in them
  
  * it shows the player is able to move to every location in the dungeon
  
  
####Design/Model Changes
 
  In my first design, I only considered the dungeon and I never thought things a bout the player. In my second design, it includes a DungeonGame interface and its implementation class and a Player interface and its implementation class and the client can only interact with DungeonGame interface. It includes Player and Dungeon as its private fields.
  
####Assumptions

  * I assumed that once the player reaches the end location and if there is no treasure in the cave, the game would be over, if there is treasure, the game would be over after the player picks up the treasure.
  
####Limitations

  * The client should create a dungeon with each side bigger than 4, in case of the program cannot find two locations that have at least five length between them.

####Citations

Labuladong. (2022, February). Labuladong's algorithm: How to use union find algorithm. https://labuladong.github.io/algo/2/22/53/





  
