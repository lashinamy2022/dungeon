package enumeration;

/**
 * Represents the types of dungeon.
 *
 */
public enum DungeonType {
  WRAPPING("wrapping"),
  NONWRAPPING("non-wrapping");
  
  private final String name;
  
  private DungeonType(String name) {
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
