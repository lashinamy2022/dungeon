package dungeon;

import enumeration.TreasureType;
import java.util.List;

/**
 * This interface provides all the functions a dungeon game should support.
 */
public interface Dungeon {
  
  /**
   * Set a start and a end cave randomly, and the path between them should be at
   * least of length 5. 
   * @return the start cave no. and the end cave no. formatting with {startNo., endNo.}
   */
  int[] setStartAndEndCaves();
  
  /**
   * Get the treasure in the given location.
   * @param location the current location of the player
   * @return the treasure listed in the location
   */
  List<TreasureType> getTreasure(int location);
  
  /**
   * Get the type of the location(tunnel or cave), and  a description of 
   * treasure at that location and the possible moves (north, east, south, west) 
   * that the player can make from their current location.
   * 
   * @param location the number of location to be searched
   * @return information of the location
   */
  String getLocationInfo(int location);
  
  /**
   * Move from the current location towards the given direction.
   * @param curLocation the original location
   * @param direction the direction the client wants to move to  
   * @return -1 if it is impossible, otherwise return the destination location.
   */
  int move(int curLocation, char direction);
  
  /**
   * Print the dungeon.
   * @param location represents where the player is
   * @return the dungeon string
   */
  String printDungegon(int location);
  
  /**
   * Check if there is treasure in the cave.
   * @param location to be checked
   * @return true if there is treasure, otherwise false
   */
  boolean hasTreasure(int location);
  
  /**
   * Check if it can move from the current location
   *  towards the given direction.
   * @param location represents the current location 
   * @param direction represents the move direction 
   * @return true if it can move towards the given 
   *          direction, otherwise false
   */
  boolean canMove(int location, String direction);
  
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
  
  /**
   Find the path with minimum length between the start and end caves.
   * @param start the start cave
   * @param end   then end cave
   * @param visited used to ensure that record each node would just been
   *        visited once time
   * @return the minimum length
   */
  int bfs(int start, int end, boolean[] visited);
}
