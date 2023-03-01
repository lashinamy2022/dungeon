package random;

import java.util.Random;

/**
 * This class is to generate random numbers and provides fixed 
 * values for testing.
 *
 */
public class RandomGenerator {
  private Random random;
  private int[] values;
  private int generatorType;
  private int position;
  
  /**
   * Construct a random generator that provides real random values.
   */
  public RandomGenerator() {
    this.random = new Random();
    this.generatorType = 1;
    this.position = 0;
  }
  
  /**
   * Construct a mock random generator that provides given values one by one.
   * @param randomValues the fixed values given by the client
   */
  public RandomGenerator(int...randomValues) {
    this.values = randomValues;
    this.generatorType = 2;
    this.position = 0;
  }
  
  /**
   * Generate real random values within the lower and upper boundaries.
   * @param lower the lower boundary
   * @param upper the upper boundary
   * @return the random value
   */
  public int getInt(int lower, int upper) {
    if (generatorType == 2) {
      throw new IllegalArgumentException("the method is not compatible"
          + " with the generator having fixed values");
    }
    return  random.nextInt(upper - lower + 1) + lower;
  }
  
  /**
   * Give the given fixed values one by one.
   * @return a fixed value at the recorded position
   */
  public int getInt() {
    if (generatorType == 1) {
      throw new IllegalArgumentException("the method is not compatible"
          + " with the generator generating random values");
    }
    if (position >= values.length) {
      throw new IllegalArgumentException("index out of bound");
    }
    return values[position++];
  }
  
  /**
   * Get the generator type.
   * @return 1 if it is a real random value generator, 
   *         2 if it is the mock one
   */
  public int getGeneratorType() {
    return generatorType;
  }
  
}
