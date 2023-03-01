package player;

import dungeon.Dungeon;
import enumeration.TreasureType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import util.StreamUtil;

/**
 * This class represents a player and provides functions for a player to play the 
 * dungeon game.
 *
 */
public class PlayerImpl implements Player {
  private int startCave;
  private int endCave;
  private int curLocation;
  private Dungeon dungeon;
  private List<TreasureType> treasureBag;
  private Set<Integer> route;
  
  /**
   * Construct a player with its start and end caves.
   * @param dungeon represent the dungeon object
   */
  public PlayerImpl(Dungeon dungeon) {
    this.dungeon = dungeon;
    int[] startAndEnd  = this.dungeon.setStartAndEndCaves();
    this.startCave = startAndEnd[0];
    this.endCave = startAndEnd[1];
    this.curLocation = -111;
    this.treasureBag = new ArrayList<>();
    this.route = new TreeSet<>((a, b) -> (a - b));
    this.route.add(startCave);
  }
  
  @Override
  public void enterDungeon() {
    this.curLocation = startCave;
  }

  @Override
  public void move(String direction) {
    this.curLocation = dungeon.move(curLocation, direction.charAt(0));
    this.route.add(curLocation);
  }

  @Override
  public String printLocationInfo() {
    return dungeon.getLocationInfo(curLocation);
  }

  @Override
  public String printPlayerInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("origin: ").append(startCave).append("\n");
    sb.append("destination: ").append(endCave).append("\n");
    sb.append("current: ").append(curLocation).append("\n");
    sb.append(StreamUtil.organizeTreasure(treasureBag)).append("\n");
    sb.append("locations it has been: ").append(route.toString());
    return sb.toString();
  }

  @Override
  public boolean isAtEndCave() {
    return curLocation == endCave;
  }

  @Override
  public void pickUpTreasure() {
    List<TreasureType> treasureList = this.dungeon.getTreasure(curLocation);
    if (treasureList != null) {
      treasureBag.addAll(treasureList);
    }
  }

  @Override
  public String printDungeon() {
    return this.dungeon.printDungegon(this.curLocation);
  }

  @Override
  public String printInitInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("player should start from ");
    sb.append(startCave);
    sb.append(" to ");
    sb.append(endCave);
    sb.append("\n");
    return sb.toString();
  }

  @Override
  public boolean hasTreasure() {
    return this.dungeon.hasTreasure(curLocation);
  }

  @Override
  public boolean canMove(String direction) {
    return this.dungeon.canMove(curLocation, direction);
  }
}
