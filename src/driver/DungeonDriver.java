package driver;

import enumeration.DungeonType;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.DungeonGame;
import model.DungeonGameImpl;
import random.RandomGenerator;

/**
 * This is a driver class to show how to play this game.
 *
 */
public class DungeonDriver {
  private final Readable in;
  private final Appendable out;
  
  /**
   * Construct a battle driver with input and output parameters.
   * @param in input parameter
   * @param out output parameter
   */
  public DungeonDriver(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }
  
  /**
   * To start a battle by reading user's input command.
   * @throws IOException when there is a input or output exception
   */
  public void startGame() throws IOException {
    Scanner scan = new Scanner(this.in);
    DungeonGame game = null;
    DungeonType type = null;
    int row = 0;
    int column = 0;
    int degree = 0;
    int treasurePercent = 0;
    List<Integer> numberList = new ArrayList<>();
    this.out.append("Please enter 'wp' to start a wrapping dungeon game,\n"
        + "    or enter 'nwp' to start a non-wrapping one,\n"
        + "    and enter two numbers to set the size of the dungeon,\n"
        + "    and then enter a number to set the degree of the dungeon,\n"
        + "    then enter another number to set the treasure percentage,\n"
        + "for example : wp 6 5 3 20 \n ");
    this.out.append("start game : ");
    while (true) {
      String command = scan.next();
      if (isNumber(command)) {
        numberList.add(Integer.parseInt(command));
      } else {
        if ("q".equals(command)) {
          this.out.append("====================================================\n");
          this.out.append("Game quit!\n");
          if (game != null) {
            this.out.append(" Ending game state:\n");
            this.out.append("====================================================\n");
            this.out.append(game.printDungeon());
            this.out.append("====================================================\n");
            this.out.append(playerInfo(game));
          }
          scan.close();
          return;
        } else if ("wp".equals(command)) {
          type = DungeonType.WRAPPING;
        } else if ("nwp".equals(command)) {
          type = DungeonType.NONWRAPPING;
        } else if ("enter".equals(command)) {
          game.enterDungeon();
          this.out.append("====================================================\n");
          this.out.append(game.printDungeon());
          this.out.append("====================================================\n");
          numberList = new ArrayList<>();
          this.out.append("====================================================\n");
          this.out.append(locationInfo(game));
          this.out.append("====================================================\n");
          this.out.append(playerInfo(game));
          this.out.append("====================================================\n");
          this.out.append(hints());
        } else if ("N".equals(command) 
            || "S".equals(command)
            || "E".equals(command)
            || "W".equals(command)) {
          if (!game.canMove(command)) {
            this.out.append("====================================================\n");
            this.out.append("invalid move: cannot move towards " + command + "\n");
          } else {
            game.move(command);
            printInfoForEachStep(game);
          }
          if (!game.hasTreasure() && game.isGameOver()) {
            this.out.append("====================================================\n");
            this.out.append("The game is over!\n");
            this.out.append(playerInfo(game));
            scan.close();
            return;
          }
          this.out.append("====================================================\n");
          this.out.append(hints());
        } else if ("p".equals(command)) {
          if (!game.hasTreasure()) {
            this.out.append("====================================================\n");
            this.out.append("invalid pick: there is no treasure\n");
          } else {
            game.pickUpTreasure();
            printInfoForEachStep(game);
          }
          if (game.isGameOver()) {
            this.out.append("====================================================\n");
            this.out.append("The game is over!\n");
            this.out.append(playerInfo(game));
            scan.close();
            return;
          } 
          this.out.append("====================================================\n");
          this.out.append(hints());
        } else {
          numberList = new ArrayList<>();
          this.out.append(command + " is unsupported!\nplease enter valid command: ");
        }
      }
      if (numberList.size() == 4) {
        if (numberList.get(0) < 5 || numberList.get(1) < 5) {
          numberList = new ArrayList<>();
          this.out.append("====================================================\n");
          this.out.append("please reset dungeon much bigger: ");
        } else {
          row = numberList.get(0);
          column = numberList.get(1);
          degree = numberList.get(2);
          treasurePercent = numberList.get(3);
          game = new DungeonGameImpl(type, row, column, degree, treasurePercent, 
              new RandomGenerator(), new RandomGenerator(), new RandomGenerator());
          this.out.append("====================================================\n");
          this.out.append(game.printDungeon());
          this.out.append("====================================================\n");
          this.out.append(game.printInitInfo());
          this.out.append("====================================================\n");
          this.out.append("please enter 'enter' to enter the dungeon:");
        }
      }
    }
  }
  
  /**
   * Print information of each step.
   * @param game  the dungeon game
   * @throws IOException if there is something wrong with the output
   */
  private void printInfoForEachStep(DungeonGame game) throws IOException {
    this.out.append(game.printDungeon());
    this.out.append("====================================================\n");
    this.out.append(locationInfo(game));
    this.out.append("====================================================\n");
    this.out.append(playerInfo(game));
  }
  
  /**
   * Get the hint string.
   * @return the hint string
   */
  private String hints() {
    StringBuilder sb = new StringBuilder();
    sb.append("enter 'N','S','W','E' to move towards").append("\n");
    sb.append("north, south, west, east respectively").append("\n");
    sb.append("or 'p' to pick up treasure").append("\n");
    sb.append("or enter 'q' to quit the game: ");
    return sb.toString();
  }
  
  /**
   * Get the player's current location information.
   * @param game the dungeon game
   * @return the player's current location information
   */
  private String locationInfo(DungeonGame game) {
    StringBuilder sb = new StringBuilder();
    sb.append("location info:").append("\n");
    sb.append(game.printLocationInfo()).append("\n");
    return sb.toString();
  }
  
  /**
   * Get the player information.
   * @param game the dungeon game
   * @return the player information
   */
  private String playerInfo(DungeonGame game) {
    StringBuilder sb = new StringBuilder();
    sb.append("player info:").append("\n");
    sb.append(game.printPlayerInfo()).append("\n");
    return sb.toString();
  }
  
  /**
   * Check if the string consists of numbers.
   * @param str to be checked
   * @return true if it is a number, otherwise false
   */
  private boolean isNumber(String str) {
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Driver program for Dungeon game to show how it works.
   * @param args not used
   */
  public static void main(String[] args) {
    Readable in = new InputStreamReader(System.in);
    DungeonDriver driver = new DungeonDriver(in, System.out);
    try {
      driver.startGame();
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }
  
}
