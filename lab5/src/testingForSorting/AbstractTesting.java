package testingForSorting;

public abstract class AbstractTesting {
  protected static final int MAX_VALUE_FOR_INT_GENERATOR = 10;
  protected static final int VALUE_FOR_REPETITIONS = 20;
  protected static final int[] ARRAYS_SIZES = {10, 20, 30, 45, 50, 80, 100, 150, 200, 250, 500, 1000, 2000, 5000, 10000};

  private static void printStatistic(String label, double average, double stdDev) {
    System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
  }

  private static String double2String(double value) {
      return String.format("%.12f", value);
  }

  protected static void printResults(testing.results.swapping.Result result) {
    printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
    printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
    printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

    System.out.println("always sorted: " + result.sorted());
    System.out.println("always stable: " + result.stable());
    System.out.println();
  }


}
