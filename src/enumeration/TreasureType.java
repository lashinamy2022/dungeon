package enumeration;

/**
 * Represents the types of treasures in the game.
 *
 */
public enum TreasureType {

  DIAMOND("diamond", 1),
  RUBY("ruby", 2),
  SAPPHIRE("sapphire", 3);

  private final String name;
  private final int index;
  
  private TreasureType(String name, int index) {
    this.name = name;
    this.index = index;
  }
  
  public int getIndex() {
    return index;
  }
  
  public String toString() {
    return name;
  }

}
