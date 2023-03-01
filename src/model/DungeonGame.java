package model;

/**
 * The interface provides all the functions a dungeon game should support.
 *
 */
public interface DungeonGame {
  /**
   * Make the player enter the dungeon.
   */
  void enterDungeon();
  
  /**
   * Make the player moving from current location towards the given direction.
   * @param direction represents which direction the player moves towards 
   */
  void move(String direction);
  
  /**
   * Check whether the game is over or not.
   * @return true if the game is over, otherwise false
   */
  boolean isGameOver();
  
  /**
   * Pick up treasure in the current location where the play is.
   */
  void pickUpTreasure();
  
  /**
   * Print information of the current location of the player.
   * @return the current location information
   */
  String printLocationInfo();
  
  /**
   * Print information of the player.
   * @return the information of the player
   */
  String printPlayerInfo();
  
  /**
   * Print the dungeon.
   * @return the dungeon string
   */
  String printDungeon();
  
  /**
   * Print initial information including start and end points.
   * @return initial information
   */
  String printInitInfo();
  
  /**
   * Check if there is treasure in the cave.
   * @return true if there is treasure, otherwise false
   */
  boolean hasTreasure();
  
  /**
   * Check if it can move towards the given direction.
   * @param direction represents the move direction
   * @return true if it can move towards the given direction, otherwise false
   */
  boolean canMove(String direction);
  
  /**
   * Count how many caves there are in the dungeon.
   * @return the number of caves
   */
  int countCaves();
  
  /**
   * Get the size of treasure map, which count means how 
   * many caves have treasures in it.
   * @return the size of treasure map
   */
  int getTreasureMapSize();
}
