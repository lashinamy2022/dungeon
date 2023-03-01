package dungeon;

import enumeration.DungeonType;
import random.RandomGenerator;

/**
 * This class represents a non-wrapping dungeon and contains all the 
 * functions a non-wrapping dungeon should support.
 *
 */
public class NonWrappingDungeon extends DungeonAbstract {

  /**
   * Construct a non-wrapping dungeon.
   * 
   * @param rows               the rows of dungeon
   * @param columns            the columns of dungeon
   * @param degree             the interconnectivity between the locations
   * @param treasurePercentage treasure to be added to a specified percentage of caves
   * @param randomPath         used to choose a path randomly 
   * @param randomStartAndEnd  used to choose a start and end cave randomly
   * @param randomTreasureCave used to choose caves randomly
   */
  public NonWrappingDungeon(int rows, int columns, int degree,
      int treasurePercentage, RandomGenerator randomPath, 
      RandomGenerator randomStartAndEnd, RandomGenerator randomTreasureCave) {
    
    super(DungeonType.NONWRAPPING, rows, columns, degree, treasurePercentage, randomPath, 
        randomStartAndEnd, randomTreasureCave);
  }
  
  @Override
  public int move(int curLocation, char direction) {
    checkMove(curLocation, direction);
    int row = curLocation / columns;
    int col = curLocation % columns;
    if (direction == 'N') {
      row = row - 1;
    } else if (direction == 'S') {
      row = row + 1;
    } else if (direction == 'W') {
      col = col - 1;
    } else if (direction == 'E') {
      col = col + 1;
    }
    if (row < 0 || row >= this.rows || col < 0 || col >= this.columns) {
      return -100;
    }
    int newLocation = row * this.columns + col;
    Integer l = connectedPaths.get(curLocation).stream() // Convert to steam
        .filter(x -> x == newLocation)       
        .findAny()     // If 'findAny' then return found
        .orElse(null);  
    if (l == null) {
      throw new IllegalArgumentException("illegal move");
    }
    return l;
  }
  
  @Override
  public String printDungegon(int location) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int x = i * columns + j;
        int y = x + 1;
        String xs = x < 10 ? " " + x + " " : "" + x + " ";
        if (x == location) {
          xs = " P ";
        }
        if (connectedPaths.get(x).contains(y)) {
          sb.append(xs + "===");
        }  else {
          sb.append(xs + "   ");
        }
      }
      sb.append("\n");
      for (int j = 0; j < columns; j++) {
        int x = i * columns + j;
        int y = x + columns;
        if (connectedPaths.get(x).contains(y)) {
          sb.append("||    ");
        } else {
          sb.append("      ");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
