package model;

import dungeon.Dungeon;
import dungeon.NonWrappingDungeon;
import dungeon.WrappingDungeon;
import enumeration.DungeonType;
import player.Player;
import player.PlayerImpl;
import random.RandomGenerator;

/**
 * This class implements the {@link DungeonGame} interface.
 *
 */
public class DungeonGameImpl implements DungeonGame {
  
  private Dungeon dungeon;
  private Player player;
  
  /**
   * Construct a dungeon game with a dungeon and a player.
   * @param type  dungeon type : wrapping or non-wrapping
   * @param rows  the number of rows of the dungeon
   * @param cols  the number of columns of the dungeon
   * @param degree             the interconnectivity between the locations
   * @param treasurePercentage treasure to be added to a specified percentage of caves
   * @param randomPath         used to choose a path randomly 
   * @param randomStartAndEnd  used to choose a start and end cave randomly
   * @param randomTreasureCave used to choose caves randomly
   */
  public DungeonGameImpl(DungeonType type, int rows, int cols,
      int degree, int treasurePercentage, RandomGenerator randomPath, 
      RandomGenerator randomStartAndEnd, RandomGenerator randomTreasureCave) {
    if (type.toString().equals(DungeonType.WRAPPING.toString())) {
      this.dungeon = new WrappingDungeon(rows, cols, degree, treasurePercentage, 
          randomPath, randomStartAndEnd, randomTreasureCave);
    } else {
      this.dungeon = new NonWrappingDungeon(rows, cols, degree, treasurePercentage, 
          randomPath, randomStartAndEnd, randomTreasureCave);
    }
    this.player = new PlayerImpl(this.dungeon);
  }

  @Override
  public void enterDungeon() {
    this.player.enterDungeon();
  }

  @Override
  public void move(String direction) {
    this.player.move(direction);
  }

  @Override
  public boolean isGameOver() {
    return this.player.isAtEndCave();
  }

  @Override
  public void pickUpTreasure() {
    this.player.pickUpTreasure();
  }

  @Override
  public String printLocationInfo() {
    return this.player.printLocationInfo();
  }

  @Override
  public String printPlayerInfo() {
    return this.player.printPlayerInfo();
  }

  @Override
  public String printDungeon() {
    return this.player.printDungeon();
  }

  @Override
  public String printInitInfo() {
    return this.player.printInitInfo();
  }

  @Override
  public boolean hasTreasure() {
    return this.player.hasTreasure();
  }

  @Override
  public boolean canMove(String direction) {
    return this.player.canMove(direction);
  }

  @Override
  public int countCaves() {
    return this.dungeon.countCaves();
  }

  @Override
  public int getTreasureMapSize() {
    return this.dungeon.getTreasureMapSize();
  }
}
