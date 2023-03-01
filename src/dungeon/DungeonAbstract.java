package dungeon;

import enumeration.DungeonType;
import enumeration.TreasureType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import random.RandomGenerator;
import util.StreamUtil;

/**
 * An abstract class that contains all of the code that is shared by all types
 * of dungeon. 
 *
 */
public class DungeonAbstract implements Dungeon {
  //the number of rows of the dungeon
  protected final int rows;
  //the number of columns of the dungeon
  protected final int columns;
  //interconnectivity between the locations
  protected final int degree;
  //treasure to be added to a specified percentage of caves
  protected final int treasurePercentage;
  //potential paths between locations
  protected List<int[]> potentialPaths;
  //UnionFind object helps to connect two locations and create exactly one path 
  //that could cover all the locations
  protected UnionFind uf;
  //paths that have been connected with each other
  protected Map<Integer, List<Integer>> connectedPaths;
  //other possible paths that haven't been connected togther
  protected List<int[]> leftPaths;
  protected RandomGenerator randomPath;
  protected RandomGenerator randomStartAndEnd;
  protected RandomGenerator randomTreasureCave;
  //the minimum length between start and end caves
  protected final int minPathsFromStartToEnd;
  //the treasure map
  protected Map<Integer, List<TreasureType>> treasureMap;
  protected int[][] memo;
  private int caveCount;
  private int treasureMapSize;
  
  /**
   * Protected constructor for using by subclasses. 
   * 
   * @param dungeonType        the type of dungeon: wrapping or non-wrapping
   * @param rows               the rows of dungeon
   * @param columns            the columns of dungeon
   * @param degree             the interconnectivity between the locations
   * @param treasurePercentage treasure to be added to a specified percentage of caves
   * @param randomPath         used to choose a path randomly 
   * @param randomStartAndEnd  used to choose a start and end cave randomly
   * @param randomTreasureCave used to choose caves randomly
   */
  protected DungeonAbstract(DungeonType dungeonType, int rows, int columns, 
        int degree, int treasurePercentage, RandomGenerator randomPath, 
        RandomGenerator randomStartAndEnd, RandomGenerator randomTreasureCave) {
    if (rows < 4 || columns < 4) {
      throw new IllegalArgumentException("make the dungeon much bigger");
    }
    if (degree < 0) {
      throw new IllegalArgumentException("the degree cannot be negative");
    }
    if (treasurePercentage < 0 || treasurePercentage > 100) {
      throw new IllegalArgumentException("the treasure percentage is too small or too large");
    }
    this.rows = rows;
    this.columns = columns;
    this.degree = degree;
    this.treasurePercentage = treasurePercentage;
    this.randomPath = randomPath;
    this.randomStartAndEnd = randomStartAndEnd;
    this.randomTreasureCave = randomTreasureCave;
    this.uf = new UnionFind(rows * columns);
    this.potentialPaths = new ArrayList<>();
    this.connectedPaths = new HashMap<>();
    this.leftPaths = new ArrayList<>();
    this.treasureMap =  new HashMap<>();
    potentialPaths(dungeonType);
    connectLocations();
    throwTreasureInCaves();
    this.minPathsFromStartToEnd = 5;
    this.memo = new int[rows * columns][rows * columns];
    for (int i = 0; i < memo.length; i++) {
      Arrays.fill(memo[i], -1);
    }
  }
  
  /**
   * Find all the potential paths without duplicates.
   * @param dungeonType the type of dungeon
   */
  private void potentialPaths(DungeonType dungeonType) {
    int[][] grid = new int[rows][columns];
    int index = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = index;
        index++;
      }
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (j + 1 >= columns 
            && dungeonType.toString().equals(DungeonType.WRAPPING.toString())) {
          potentialPaths.add(new int[] {grid[i][j], grid[i][0]});
        }
        if (i + 1 >= rows
            && dungeonType.toString().equals(DungeonType.WRAPPING.toString())) {
          potentialPaths.add(new int[] {grid[i][j], grid[0][j]});
        }
        if (j + 1 < columns) {
          potentialPaths.add(new int[] {grid[i][j], grid[i][j + 1]});
        }
        if (i + 1 < rows) {
          potentialPaths.add(new int[] {grid[i][j], grid[i + 1][j]});
        }
      }
    }
  }
  
  /**
   * Connect locations together according to the potential paths and
   * the given degree.
   */
  private void connectLocations() {
    while (uf.getCount() > 1) {
      int i = 0;
      if (randomPath.getGeneratorType() == 1) {
        i = randomPath.getInt(0, potentialPaths.size() - 1);
      } else {
        i = randomPath.getInt();
      }
      int p = potentialPaths.get(i)[0];
      int q = potentialPaths.get(i)[1];
      if (p != -1 && q != -1) {
        potentialPaths.get(i)[0] = -1;
        potentialPaths.get(i)[1] = -1;
        if (!uf.isConnected(p, q)) {
          connectedPaths.computeIfAbsent(p, k -> new ArrayList<>()).add(q);
          connectedPaths.computeIfAbsent(q, k -> new ArrayList<>()).add(p);
          uf.union(p, q);
        } else {
          leftPaths.add(new int[] {p, q});
        }
      }
    }
    
    for (int i = 0; i < potentialPaths.size(); i++) {
      int p = potentialPaths.get(i)[0];
      int q = potentialPaths.get(i)[1]; 
      if (p != -1 && q != -1) {
        leftPaths.add(new int[] {p, q});
      }
    }
    
    if (degree > leftPaths.size()) {
      throw new IllegalArgumentException("degree is too large");
    }
    
    int interconnect = degree;
    while (interconnect > 0) {
      int i = 0;
      if (randomPath.getGeneratorType() == 1) {
        i = randomPath.getInt(0, leftPaths.size() - 1);
       
      } else {
        i = randomPath.getInt();
      }
      int p = leftPaths.get(i)[0];
      int q = leftPaths.get(i)[1];
      if (p != -1 && q != -1) {
        leftPaths.get(i)[0] = -1;
        leftPaths.get(i)[1] = -1;
        connectedPaths.computeIfAbsent(p, k -> new ArrayList<>()).add(q);
        connectedPaths.computeIfAbsent(q, k -> new ArrayList<>()).add(p);
        interconnect--;
      }
    }
  }

  @Override
  public int[] setStartAndEndCaves() {
    int start = -1;
    int end = -1;
    List<Integer> caves = connectedPaths.entrySet().stream()
        .filter(e -> e.getValue().size() != 2)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    if (randomStartAndEnd.getGeneratorType() == 1) {
      start = randomStartAndEnd.getInt(0, caves.size() - 1);
      start = caves.get(start);
      end = randomStartAndEnd.getInt(0, caves.size() - 1);
      end = caves.get(end);
    } else {
      start = randomStartAndEnd.getInt();
      end = randomStartAndEnd.getInt();
    }
    
    if (start == end) {
      if (randomStartAndEnd.getGeneratorType() == 1) {
        return setStartAndEndCaves();
      } else {
        throw new IllegalArgumentException("the start and end cave cannot be the same");
      }
    } 
    
    if (!connectedPaths.containsKey(start) || !connectedPaths.containsKey(end)) {
      return setStartAndEndCaves();
    } 
    
    int length = memo[start][end];
    if (length != -1 && length >= minPathsFromStartToEnd) {
      return new int[] {start, end};

    } else {
      int minLength = bfs(start, end, new boolean[rows * columns]);
      if (minLength >= minPathsFromStartToEnd) {
        return new int[] {start, end};
      } else {
        return setStartAndEndCaves();
      }
    }
  }
  
  @Override
  public int bfs(int start, int end, boolean[] visited) {
    int depth = 0;
    Queue<Integer> queue =  new LinkedList<>();
    queue.offer(start);
    visited[start] = true;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Integer cur = queue.poll();
        if (cur == end) {
          return depth;
        }
        List<Integer> neighbors = connectedPaths.get(cur);
        for (int n : neighbors) {
          if (!visited[n]) {
            queue.offer(n);
            visited[n] = true;
          }
        }
      }
      depth++;
    }
    memo[start][end] = depth;
    return depth;
  }
  
  /**
   * Choose which caves have treasure in it randomly.
   */
  private void throwTreasureInCaves() {
    //find all the caves (which have 1, 3, 4 entrances)
    List<Integer> caves = connectedPaths.entrySet().stream()
        .filter(e -> e.getValue().size() != 2)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    this.caveCount = caves.size();
    //calculate how many caves should have treasure in them
    int count = (int) (caves.size() * (treasurePercentage / 100.0));
    while (count > 0) {
      int index = -1;
      if (randomTreasureCave.getGeneratorType() == 1) {
        index = randomTreasureCave.getInt(0, caves.size() - 1);
        treasureMap.put(caves.get(index), generateTreasure());
        caves.remove(index);
      } else {
        index = randomTreasureCave.getInt();
        treasureMap.put(index, generateTreasure());
      }
      count--;
    }
    this.treasureMapSize = treasureMap.size();
  }
  
  /**
   * Generate a treasure list containing random types of treasure.
   * @return the treasure list
   */
  private List<TreasureType> generateTreasure() {
    List<TreasureType> treasure = new ArrayList<>();
    Random random = new Random();
    int times = random.nextInt(10) + 1;
    while (times > 0) {
      int index = random.nextInt(3) + 1;
      if (index == TreasureType.DIAMOND.getIndex()) {
        treasure.add(TreasureType.DIAMOND);
      } else if (index == TreasureType.RUBY.getIndex()) {
        treasure.add(TreasureType.RUBY);
      } else if (index == TreasureType.SAPPHIRE.getIndex()) {
        treasure.add(TreasureType.SAPPHIRE);
      } 
      times--;
    }
    return treasure;
  }
    

  @Override
  public String getLocationInfo(int location) {
    if (!connectedPaths.containsKey(location)) {
      throw new IllegalArgumentException("wrong location");
    }
    int x = (location / columns) + 1;
    int y = (location % columns) + 1;
    List<Integer> paths = connectedPaths.get(location);
    StringBuilder sb = new StringBuilder();
    sb.append("position:(" + x + "," + y + ")").append("\n");
    sb.append("location : ").append(location).append("\n");
    if (paths.size() == 2) {
      sb.append("type:tunnel").append("\n");
    } else {
      sb.append("type:cave").append("\n");
    }
    sb.append(StreamUtil.organizeTreasure(treasureMap.getOrDefault(location, null)));
    sb.append("\n");
    
    sb.append("possible moves:");
    List<String> directions = getDirectionsByLocation(location);
    sb.append(directions.toString());
    return sb.toString();
  }

  @Override
  public int move(int curLocation, char direction) {
    checkMove(curLocation, direction);
    return -1;
  }
  
  /**
   * Check the validity of the move.
   * @param curLocation the current location
   * @param direction   the direction the move towards
   */
  protected void checkMove(int curLocation, char direction) {
    if (direction != 'N' && direction != 'S' 
        && direction != 'W' && direction != 'E') {
      throw new IllegalArgumentException("wrong direction");
    }
    if (!connectedPaths.containsKey(curLocation)) {
      throw new IllegalArgumentException("wrong location");
    }
  }

  @Override
  public List<TreasureType> getTreasure(int location) {
    if (treasureMap.containsKey(location)) {
      List<TreasureType> list = treasureMap.get(location);
      treasureMap.remove(location);
      return list;
    }
    return null;
  }

  @Override
  public String printDungegon(int location) {
    return "";
  }

  @Override
  public boolean hasTreasure(int location) {
    return this.treasureMap.containsKey(location);
  }
  
  /**
   * Get the direction list by the location.
   * @param location to be checked move directions
   * @return direction list
   */
  private List<String> getDirectionsByLocation(int location) {
    List<String> directions = new ArrayList<>();
    for (int o : connectedPaths.get(location)) {
      if (o == location - 1 
          || o == location + columns - 1) {
        directions.add("west");
      } else if (o == location + 1 
          || o == location - columns + 1) {
        directions.add("east");
      } else if (o == location - columns
          || o == location + (rows - 1) * columns) {
        directions.add("north");
      } else if (o == location + columns 
          || o == location - (rows - 1) * columns) {
        directions.add("south");
      }
    }
    Collections.sort(directions);
    return directions;
  }

  @Override
  public boolean canMove(int location, String direction) {
    List<String> list = getDirectionsByLocation(location);
    if ("N".equals(direction)) {
      direction = "north";
    } else if ("S".equals(direction)) {
      direction = "south";
    } else if ("W".equals(direction)) {
      direction = "west";
    } else if ("E".equals(direction)) {
      direction = "east";
    }
    return list.contains(direction);
  }

  @Override
  public int countCaves() {
    return caveCount;
  }

  @Override
  public int getTreasureMapSize() {
    return treasureMapSize;
  }

}
