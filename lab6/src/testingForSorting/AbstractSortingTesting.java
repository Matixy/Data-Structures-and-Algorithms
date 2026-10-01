package testingForSorting;

import core.AbstractSortingAlgorithm;
import core.AbstractSwappingSortingAlgorithm;
import testing.MarkedValue;
import testing.Tester;
import testing.generation.*;
import testing.generation.conversion.LinkedListGenerator;
import testing.generation.conversion.MarkingGenerator;

public abstract class AbstractSortingTesting {
  protected static final int MAX_VALUE_FOR_INT_GENERATOR = 10;
  protected static final int VALUE_FOR_REPETITIONS = 20;
  protected static final int[] ARRAYS_SIZES = {10, 20, 30, 45, 50, 80, 100, 150, 200, 250, 500, 1000, 2000, 5000, 10000};
  protected static final Generator[] GENERATORS = {new OrderedIntegerArrayGenerator(),
      new ReversedIntegerArrayGenerator(), new ShuffledIntegerArrayGenerator(), new RandomIntegerArrayGenerator(MAX_VALUE_FOR_INT_GENERATOR)};


  private static void printStatistic(String label, double average, double stdDev) {
    System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
  }

  private static String double2String(double value) {
      return String.format("%.12f", value);
  }

  protected static void printResults(testing.results.Result result) {

    printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
    printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());

    System.out.println("always sorted: " + result.sorted());
    System.out.println("always stable: " + result.stable());
    System.out.println();
  }

  protected static void printResults(testing.results.swapping.Result result) {

    printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
    printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
    printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

    System.out.println("always sorted: " + result.sorted());
    System.out.println("always stable: " + result.stable());
    System.out.println();
  }

  protected static void doTestForArrays(AbstractSortingAlgorithm algorithm) {
    System.out.println("Tests for algorithm: " + algorithm.getClass().getName() + " started\n");

    AbstractSortingAlgorithm<MarkedValue<Integer>> sortingAlgorithm = algorithm;

    for (Generator generator : GENERATORS) {
      Generator<MarkedValue<Integer>> generatorAlgorithm = new MarkingGenerator<Integer>(generator);

      System.out.print(algorithm.getClass().getName() + " tests for: " + generator.getClass().getName() + " started\n");

      for (int size : ARRAYS_SIZES) {
        System.out.print(algorithm.getClass().getName() + " tests: size= " + size + " array= " + generator.getClass().getName() + "\n");

        testing.results.Result result = Tester.runNTimes(sortingAlgorithm, generatorAlgorithm, size, VALUE_FOR_REPETITIONS);

        printResults(result);
      }
    }
  }

    protected static void doTestForArrays(AbstractSwappingSortingAlgorithm algorithm) {
      System.out.println("Tests for algorithm: " + algorithm.getClass().getName() + " started\n");

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> sortingAlgorithm = algorithm;


      for (Generator generator : GENERATORS) {
        Generator<MarkedValue<Integer>> generatorAlgorithm = new MarkingGenerator<Integer>(generator);

        System.out.print(algorithm.getClass().getName() + " tests for: " + generator.getClass().getName() + " started\n");

        for (int size : ARRAYS_SIZES) {
          System.out.print(algorithm.getClass().getName() + "tests: size= " + size + " array= " + generator.getClass().getName() + "\n");

          testing.results.swapping.Result result = Tester.runNTimes(sortingAlgorithm, generatorAlgorithm, size,
              VALUE_FOR_REPETITIONS);

          printResults(result);
        }
      }
    }

    protected static void doTestForLists(AbstractSortingAlgorithm algorithm) {
      System.out.println("Tests for algorithm: " + algorithm.getClass().getName() + " started\n");

      AbstractSortingAlgorithm<MarkedValue<Integer>> sortingAlgorithm = algorithm;

      for (Generator generator : GENERATORS) {
        Generator<MarkedValue<Integer>> markingGenerator = new MarkingGenerator<>(generator);
        Generator<MarkedValue<Integer>> generatorAlgorithm = new LinkedListGenerator<>(markingGenerator);

        System.out.print(algorithm.getClass().getName() + " tests for: " + generator.getClass().getName() + " started\n");

        for (int size : ARRAYS_SIZES) {
          System.out.print(algorithm.getClass().getName() + "tests: size= " + size + " array= " + generator.getClass().getName() + " (wrapped in list)\n");

          testing.results.Result result = Tester.runNTimes(sortingAlgorithm, generatorAlgorithm, size,
              VALUE_FOR_REPETITIONS);

          printResults(result);
        }
      }
    }
  }
