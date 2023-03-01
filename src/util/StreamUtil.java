package util;

import enumeration.TreasureType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



/**
 * This class provides methods dealing with stream-related problems.
 *
 */
public class StreamUtil {
  
  /**
   * Conveys treasure information from list to string.
   * @param list the treasure list
   * @return string formatting with "treasure:[diamond * 3, ruby * 1, sapphire * 2]"
   */
  public static String organizeTreasure(List<TreasureType> list) {
    if (list == null) {
      return "treasure:[]";
    }
    StringBuilder sb = new StringBuilder();
    Map<TreasureType, Long> counted = list.stream()
        .collect(Collectors.groupingBy(Function.identity(),
                 Collectors.counting()));
    List<String> tlist = new ArrayList<>();
    for (TreasureType type : counted.keySet()) {
      String result = type.toString() + " * " + counted.get(type);
      tlist.add(result);
    }
    sb.append("treasure:");
    sb.append(tlist.toString());
    return sb.toString();
  }
}
