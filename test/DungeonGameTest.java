import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dungeon.Dungeon;
import dungeon.WrappingDungeon;
import enumeration.DungeonType;
import java.util.ArrayList;
import java.util.List;
import model.DungeonGame;
import model.DungeonGameImpl;
import org.junit.Before;
import org.junit.Test;
import random.RandomGenerator;

/**
 * This class is to test if all the functions supported by 
 * the dungeon game always work correctly.
 *
 */
public class DungeonGameTest {
  //the number of rows of the dungeon
  private int rows = 6;
  //the number of columns of the dungeon
  private int columns = 6;

  //the mock random value generators that generates values
  //what a wrapping dungeon with 0 degree needs
  private RandomGenerator randomPathW0; //choose paths randomly
  private RandomGenerator randomTreasureCaveW0; //choose which caves have treasures randomly
  private RandomGenerator randomStartAndEndW0; //choose the start and end cave randomly
  private int degreeW0 = 0;
  private int percentageW0 = 50;
  private DungeonGame gameW0;
  
  //the mock random value generators that generates values
  //what a wrapping dungeon with 20 degrees needs
  private RandomGenerator randomPathW1;
  private RandomGenerator randomTreasureCaveW1;
  private RandomGenerator randomStartAndEndW1;
  private int degreeW1 = 20;
  private int percentageW1 = 50;
  private DungeonGame gameW1;
  
  //the mock random value generators that generates values
  //what a non-wrapping dungeon with 0 degree needs
  private RandomGenerator randomPathN0;
  private RandomGenerator randomStartAndEndN0;
  private RandomGenerator randomTreasureCaveN0;
  private int degreeN0 = 0;
  private int percentageN0 = 50;
  private DungeonGame gameN0;
  
  //the mock random value generators that generates values
  //what a wrapping dungeon with 14 degrees needs
  private RandomGenerator randomPathN1;
  private RandomGenerator randomStartAndEndN1;
  private RandomGenerator randomTreasureCaveN1;
  private int degreeN1 = 14;
  private int percentageN1 = 50;
  private DungeonGame gameN1;
  //the dungeon with 100% treasure percentage
  private RandomGenerator randomPath100;
  private RandomGenerator randomStartAndEnd100;
  private RandomGenerator randomTreasureCave100;
  private int degree100 = 20;
  private int percentage100 = 100;
  private DungeonGame game100PerTreasure;

  /**
   *  Setting up random generators for all tests.
   */
  @Before
  public void setup() {
    randomPathW0 = new RandomGenerator(new int[]{
        68, 28, 2, 0, 57, 8, 11, 7, 48, 66, 63,
        3, 16, 27, 51, 32, 4, 39, 29, 53, 34, 33,
        62, 19, 70, 54, 18, 59, 13, 67, 30, 37,
        42, 58, 46
    });
    //8, 24
    randomTreasureCaveW0 = new RandomGenerator(new int[] {7, 26, 16, 21, 11, 6, 1, 31, 24, 13, 15});
    randomStartAndEndW0 = new RandomGenerator(new int[] {0, 35});
    gameW0 = new DungeonGameImpl(DungeonType.WRAPPING, 
        rows, columns, degreeW0, percentageW0,
        randomPathW0, randomStartAndEndW0, randomTreasureCaveW0);
    gameW0.enterDungeon();
    
    randomPathW1 = new RandomGenerator(new int[]{
        36, 22, 5, 66, 59, 7, 54, 61, 11, 33, 29, 50, 
        43, 56, 48, 31, 44, 2, 19, 39, 17, 25, 16, 64,
        26, 62, 47, 0, 3, 38, 8, 71, 67, 35, 18, 14, 27,
        10, 36, 8, 7, 3, 35, 11, 26, 21, 0, 24, 16, 15, 
        6, 12, 25, 20, 17
    });
    randomTreasureCaveW1 = new RandomGenerator(new int[] {15, 27, 4, 13, 25, 7, 
        16, 31, 10, 33, 8, 32, 1});
    randomStartAndEndW1 = new RandomGenerator(new int[] {15, 30});
    gameW1 = new DungeonGameImpl(DungeonType.WRAPPING, 
        rows, columns, degreeW1, percentageW1,
        randomPathW1, randomStartAndEndW1, randomTreasureCaveW1);
    gameW1.enterDungeon();
    
    randomPathN0 = new RandomGenerator(new int[]{
        20, 17, 27, 11, 55, 24, 30, 8, 4, 42, 9,
        26, 56, 13, 54, 29, 6, 36, 39, 40, 58, 22,
        1, 19, 51, 47, 41, 33, 5, 28, 57, 38, 59, 2, 44
    });
    randomTreasureCaveN0 = new RandomGenerator(new int[] {30, 4, 18, 10, 21, 17,
        23, 11, 15, 5, 1, 2});
    //31,5
    randomStartAndEndN0 = new RandomGenerator(new int[] {0, 35});
    gameN0 = new DungeonGameImpl(DungeonType.NONWRAPPING, 
        rows, columns, degreeN0, percentageN0,
        randomPathN0, randomStartAndEndN0, randomTreasureCaveN0);
    gameN0.enterDungeon();
    
    randomPathN1 = new RandomGenerator(new int[]{
        0, 29, 56, 54, 3, 53, 6, 43, 37, 14, 31,
        40, 12, 26, 11, 51, 13, 52, 33, 39, 35,
        45, 10, 21, 57, 34, 9, 5, 42, 4, 19, 23,
        44, 38, 15, 20, 18, 1, 15, 10, 12, 14, 17,
        16, 13, 0, 5, 24, 23
    });
    randomTreasureCaveN1 = new RandomGenerator(new int[] {20, 18, 23, 11, 12, 32, 19, 1, 4, 24,
        17, 35, 2, 30, 25});
    randomStartAndEndN1 = new RandomGenerator(new int[] {1, 30});
    gameN1 = new DungeonGameImpl(DungeonType.NONWRAPPING, 
        rows, columns, degreeN1, percentageN1,
        randomPathN1, randomStartAndEndN1, randomTreasureCaveN1);
    gameN1.enterDungeon();
    
    randomPath100 = new RandomGenerator(new int[]{
        32, 2, 48, 44, 63, 55, 50, 15, 22, 35, 0, 31,
        54, 9, 45, 56, 68, 43, 17, 60, 37, 40, 27, 19,
        26, 39, 51, 20, 66, 4, 58, 24, 69, 11, 1, 25,
        4, 27, 32, 34, 5, 24, 31, 0, 17, 15, 21, 9, 
        35, 22, 26, 8, 28, 14, 11
    });

    randomTreasureCave100 = new RandomGenerator(new int[] {
        21, 31, 34, 14, 1,
        11, 4, 7, 26, 27,
        2, 5, 30, 16, 24,
        10, 23, 15, 25, 33,
        28, 18, 8, 32, 0,
        13, 22, 29, 20, 12});
    randomStartAndEnd100 = new RandomGenerator(new int[] {2, 22});
        
    game100PerTreasure = new DungeonGameImpl(DungeonType.WRAPPING, 
        rows, columns, degree100, percentage100,
        randomPath100, randomStartAndEnd100, randomTreasureCave100);
    game100PerTreasure.enterDungeon();
  }
  
  
  /**
   * Test every cave is reachable in the wrapping dungeon.
   */
  @Test 
  public void testEveryCaveReachableInWrappingDungeon() {
    String moves = "E,S,N,E,E,S,W,E,S,W,S,N,E,E,S,W,E,N,E,W,W,N,E,"
        + "W,N,N,E,N,W,E,S,S,E,S,N,W,N,W,S,W,W,N,"
        + "E,N,S,W,N,N,N,S,S,W,W,S,E,W,N,E,N,W,E,"
        + "S,E,S,S,E,E,S,S,E,E,E,N";
    String[] array = moves.split(",");
    for (int i = 0; i < array.length; i++) {
      gameW0.move(array[i]);
    }
    String info = gameW0.printPlayerInfo();
    String[] infoArr = info.split("\n");
    String reachedLocations = infoArr[4];
    reachedLocations = reachedLocations.replace("locations it has been: ", "");
    List<Integer> locations = new ArrayList<>();
    for (int i = 0; i < rows * columns; i++) {
      locations.add(i);
    }
    assertEquals(locations.toString(), reachedLocations);
  }
  
  /**
   * Test every cave is reachable in the non-wrapping dungeon.
   */
  @Test 
  public void testEveryCaveReachableInNonWrappingDungeon() {
    String moves = "S,E,E,N,W,E,E,E,E,W,S,W,E,E,W,S,E,W,W,W,W,W,E,E,S,S,"
        + "N,N,E,S,E,S,N,E,W,S,N,W,S,S,E,E,N,S,W,W,W,W,W,E,"
        + "N,W,E,N,W";
    String[] array = moves.split(",");
    for (int i = 0; i < array.length; i++) {
      gameN0.move(array[i]);
    }

    String info = gameN0.printPlayerInfo();
    String[] infoArr = info.split("\n");
    String reachedLocations = infoArr[4];
    reachedLocations = reachedLocations.replace("locations it has been: ", "");
    List<Integer> locations = new ArrayList<>();
    for (int i = 0; i < rows * columns; i++) {
      locations.add(i);
    }
    assertEquals(locations.toString(), reachedLocations);
  }
  
  /**
   * Test if treasure is added to the current percentage of caves.
   */
  @Test
  public void testTreasurePercentage() {
    DungeonGame game = new DungeonGameImpl(DungeonType.NONWRAPPING, 
        9, 9, 0, 100, new RandomGenerator(), 
        new RandomGenerator(), new RandomGenerator());
    double caveCount = (double) game.countCaves();
    double treasureMapSize = (double) game.getTreasureMapSize();
    assertEquals(1, treasureMapSize / caveCount, 0.001);
  }
  
  /**
   * Test if the length of the path between the start and the end is at least 5.
   */
  @Test
  public void testMinimumLengthBetweenStartAndEnd() {
    randomPathW1 = new RandomGenerator(new int[]{
        36, 22, 5, 66, 59, 7, 54, 61, 11, 33, 29, 50, 
        43, 56, 48, 31, 44, 2, 19, 39, 17, 25, 16, 64,
        26, 62, 47, 0, 3, 38, 8, 71, 67, 35, 18, 14, 27,
        10, 36, 8, 7, 3, 35, 11, 26, 21, 0, 24, 16, 15, 
        6, 12, 25, 20, 17
    });
    randomTreasureCaveW1 = new RandomGenerator(new int[] {15, 27, 4, 13, 25, 7, 
        16, 31, 10, 33, 8, 32, 1});
    randomStartAndEndW1 = new RandomGenerator(new int[] {15, 30});
    RandomGenerator realRandomStartAndEnd = new RandomGenerator();
    Dungeon dungeon = new WrappingDungeon(rows, columns, degreeW1, percentageW1,
        randomPathW1, realRandomStartAndEnd, randomTreasureCaveW1);
    for (int i = 0; i < 100; i++) {
      int[] startAndEnd = dungeon.setStartAndEndCaves();
      int minimumLen = dungeon.bfs(startAndEnd[0], startAndEnd[1], new boolean[rows * columns]);
      assertTrue(minimumLen >= 5);
    }
  }
  
  /**
   * This is to test he player starts at the starting location.
   */
  @Test
  public void testStartAtStartingCave() {
    randomPathW1 = new RandomGenerator(new int[]{
        36, 22, 5, 66, 59, 7, 54, 61, 11, 33, 29, 50, 
        43, 56, 48, 31, 44, 2, 19, 39, 17, 25, 16, 64,
        26, 62, 47, 0, 3, 38, 8, 71, 67, 35, 18, 14, 27,
        10, 36, 8, 7, 3, 35, 11, 26, 21, 0, 24, 16, 15, 
        6, 12, 25, 20, 17
    });
    randomTreasureCaveW1 = new RandomGenerator(new int[] {15, 27, 4, 13, 25, 7, 
        16, 31, 10, 33, 8, 32, 1});
    randomStartAndEndW1 = new RandomGenerator(new int[] {15, 30});
    RandomGenerator realRandomStartAndEnd = new RandomGenerator();
    gameW1 = new DungeonGameImpl(DungeonType.WRAPPING, 
        rows, columns, degreeW1, percentageW1,
        randomPathW1, realRandomStartAndEnd, randomTreasureCaveW1);
    //before entering the dungeon
    String[] playerInfo = gameW1.printPlayerInfo().split("\n");
    String origin = playerInfo[0].replace("origin: ", "");
    String current = playerInfo[2].replace("current: ", "");
    assertTrue(!origin.equals(current));
    //enter the dungeon
    gameW1.enterDungeon();
    //after entering the dungeon
    playerInfo = gameW1.printPlayerInfo().split("\n");
    origin = playerInfo[0].replace("origin: ", "");
    current = playerInfo[2].replace("current: ", "");
    assertTrue(origin.equals(current));
  }
  
  /**
   * This is to test if the player can reach the ending location in the dungeon.
   */
  @Test
  public void testIfEndPointReachable() {
    String[] playerInfo = gameW1.printPlayerInfo().split("\n");
    String destination = playerInfo[1].replace("destination: ", "");
    String current = playerInfo[2].replace("current: ", "");
    assertTrue(!destination.equals(current));
    String moves = "S,W,S,S,S,S";
    String[] array = moves.split(",");
    for (int i = 0; i < array.length; i++) {
      gameN1.move(array[i]);
    }
    playerInfo = gameN1.printPlayerInfo().split("\n");
    destination = playerInfo[1].replace("destination: ", "");
    current = playerInfo[2].replace("current: ", "");
    assertTrue(destination.equals(current));
  }
  
  /**
   * This is to test the player's description.
   */
  @Test
  public void testPlayerDescription() {
    String start = getPlayerInfo(gameW1, "start");
    assertEquals("15", start);
    String end = getPlayerInfo(gameW1, "end");
    assertEquals("30", end);
    String current = getPlayerInfo(gameW1, "current");
    assertEquals(start, current);
    String treaPicked = getPlayerInfo(gameW1, "treaPicked");
    assertEquals("[]", treaPicked);
    String passedLocation = getPlayerInfo(gameW1, "passedLocation");
    assertEquals("[15]", passedLocation);
    
    //pick up treasure
    String treaInLocation = getLocationInfo(gameW1, "treasure");
    gameW1.pickUpTreasure();
    treaPicked = getPlayerInfo(gameW1, "treaPicked");
    assertEquals(treaInLocation, treaPicked);
    gameW1.move("S");
    current = getPlayerInfo(gameW1, "current");
    assertEquals("21", current);
    passedLocation = getPlayerInfo(gameW1, "passedLocation");
    assertEquals("[15, 21]", passedLocation);
  }
  
  /**
   * This is to test player's current location description.
   */
  @Test
  public void testLocationDescription() {
    String start = getPlayerInfo(game100PerTreasure, "start");
    String location = getLocationInfo(game100PerTreasure, "no");
    assertEquals(start, location);
    String locType = getLocationInfo(game100PerTreasure, "locationType");
    assertEquals("cave", locType);
    String treasure = getLocationInfo(game100PerTreasure, "treasure");
    assertTrue(!"[]".equals(treasure));
    String possibleMoves = getLocationInfo(game100PerTreasure, "moves");
    assertEquals("[east, north, west]", possibleMoves);
    //after picking up the treasures in the cave
    //there won't be any treasures 
    game100PerTreasure.pickUpTreasure();
    treasure = getLocationInfo(game100PerTreasure, "treasure");
    assertTrue("[]".equals(treasure));
    
    game100PerTreasure.move("W");
    String current = getPlayerInfo(game100PerTreasure, "current");
    location = getLocationInfo(game100PerTreasure, "no");
    assertEquals(current, location);
    locType = getLocationInfo(game100PerTreasure, "locationType");
    assertEquals("cave", locType);
    treasure = getLocationInfo(game100PerTreasure, "treasure");
    assertTrue(!"[]".equals(treasure));
    possibleMoves = getLocationInfo(game100PerTreasure, "moves");
    assertEquals("[east, north, south, west]", possibleMoves);
    
  }
  
  /**
   * This is to test the player can move in each of the four (4) possible directions.
   */
  @Test
  public void testIfPlayerCanMove() {
    DungeonGame game = createGame();
    String possibleMoves = getLocationInfo(game, "moves");
    possibleMoves = possibleMoves.replace("[", "").replace("]", "");
    String[] moveArr = possibleMoves.split(",");
    for (String m : moveArr) {
      m = m.trim();
      String command = String.valueOf(m.charAt(0)).toUpperCase();

      game = createGame();
      game.move(command);
      String current = getPlayerInfo(game, "current");
      String locationNo = getLocationInfo(game, "no");
      assertEquals(current, locationNo);
    }
  }
  
  /**
   * This is to test the player is able to pick up treasure.
   */
  @Test
  public void testIfPlayerCanPickUp() {
    //before picking up treasure
    String treaPicked = getPlayerInfo(game100PerTreasure, "treaPicked");
    assertEquals("[]", treaPicked);
    String treaInLocation = getLocationInfo(game100PerTreasure, "treasure");
    assertTrue(!"[]".equals(treaInLocation));

    game100PerTreasure.pickUpTreasure();
    treaPicked = getPlayerInfo(game100PerTreasure, "treaPicked");
    assertTrue(!"[]".equals(treaPicked));
    treaInLocation = getLocationInfo(game100PerTreasure, "treasure");
    assertEquals("[]", treaInLocation);
  }
  
  /**
   * This is to get the player's information from the description.
   * @param game the dungeon game
   * @param type the type of the information
   * @return the specified information
   */
  private String getPlayerInfo(DungeonGame game, String type) {
    String playerInfo = game.printPlayerInfo();
    String[] playerArr = playerInfo.split("\n");
    String start = playerArr[0].replace("origin: ", "");
    String end = playerArr[1].replace("destination: ", "");
    String current = playerArr[2].replace("current: ", "");
    String treaPicked = playerArr[3].replace("treasure:", "");
    String passedLocation = playerArr[4].replace("locations it has been: ", "");
    if (("start").equals(type)) {
      return start;
    } else if (("end").equals(type)) {
      return end;
    } else if (("current").equals(type)) {
      return current;
    } else if (("treaPicked").equals(type)) {
      return treaPicked;
    } else if (("passedLocation").equals(type)) {
      return passedLocation;
    }
    return "";
  }
  
  /**
   * This is to create new {@link DungeonGame} Object for testing purpose.
   * @return the {@link DungeonGame} Object
   */
  private DungeonGame createGame() {
    randomPath100 = new RandomGenerator(new int[]{
        32, 2, 48, 44, 63, 55, 50, 15, 22, 35, 0, 31,
        54, 9, 45, 56, 68, 43, 17, 60, 37, 40, 27, 19,
        26, 39, 51, 20, 66, 4, 58, 24, 69, 11, 1, 25,
        4, 27, 32, 34, 5, 24, 31, 0, 17, 15, 21, 9, 
        35, 22, 26, 8, 28, 14, 11
    });

    randomTreasureCave100 = new RandomGenerator(new int[] {
        21, 31, 34, 14, 1,
        11, 4, 7, 26, 27,
        2, 5, 30, 16, 24,
        10, 23, 15, 25, 33,
        28, 18, 8, 32, 0,
        13, 22, 29, 20, 12});
    randomStartAndEnd100 = new RandomGenerator(new int[] {2, 22});
        
    game100PerTreasure = new DungeonGameImpl(DungeonType.WRAPPING, 
        rows, columns, degree100, percentage100,
        randomPath100, randomStartAndEnd100, randomTreasureCave100);
    game100PerTreasure.enterDungeon();
    return game100PerTreasure;
  }
  
  /**
   * This is to get the player's location information from the description.
   * @param game the dungeon game
   * @param type the type of the information
   * @return the specified information
   */
  private String getLocationInfo(DungeonGame game, String type) {
    String locationInfo = game.printLocationInfo();
    String[] locationArr = locationInfo.split("\n");
    String position = locationArr[0].replace("position:", "");
    String no = locationArr[1].replace("location : ", "");
    String locationType = locationArr[2].replace("type:", "");
    String treasure = locationArr[3].replace("treasure:", "");
    String moves = locationArr[4].replace("possible moves:", "");
    if (("position").equals(type)) {
      return position;
    } else if (("no").equals(type)) {
      return no;
    } else if (("locationType").equals(type)) {
      return locationType;
    } else if (("treasure").equals(type)) {
      return treasure;
    } else if (("moves").equals(type)) {
      return moves;
    }
    return "";
  }
}
