package player;

/**
 * This interface supports a player moving through the dungeon.
 *
 */
public interface Player {
  
  /**
   * Make the player enter the dungeon.
   */
  void enterDungeon();
  
  /**
   * Player moves from current location in the dungeon towards the given direction. 
   * @param direction represents which direction the player moves towards 
   */
  void move(String direction);
  
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
   * Check if the player is at the end cave.
   * @return true if the player is at the end cave, otherwise false
   */
  boolean isAtEndCave();
  
  /**
   * Pick up treasure in the current location.
   */
  void pickUpTreasure();
  
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
   * @return true if it can move towards the given 
   *          direction, otherwise false
   */
  boolean canMove(String direction);
}
