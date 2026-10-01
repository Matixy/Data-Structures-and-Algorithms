package testingForSorting;

import core.AbstractSwappingSortingAlgorithm;
import sorting.InsertSortWithBinarySearch;
import testing.MarkedValue;
import testing.Tester;
import testing.comparators.IntegerComparator;
import testing.comparators.MarkedValueComparator;
import testing.generation.*;
import testing.generation.conversion.MarkingGenerator;

import java.util.Comparator;

public class InsertSortWithBinarySearchTesting extends AbstractTesting {
  public static void doInsertSortWithBinarySearchTest() {
    System.out.println("Insert sort with binary search tests started\n");

    doInsertSortWithBinarySearchArrayTest();
    doInsertSortWithBinarySearchReversedArrayTest();
    doInsertSortWithBinarySearchShuffledArrayTest();
    doInsertSortWithBinarySearchRandomArrayTest();
  }

  public static void doInsertSortWithBinarySearchArrayTest() {
    System.out.println("InsertSortWithBinarySearch tests for OrderedIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new OrderedIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new InsertSortWithBinarySearch<>(markedComparator);

      System.out.print("Insert sort with binary search tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doInsertSortWithBinarySearchReversedArrayTest() {
    System.out.println("Insert sort with binary search tests for ReversedIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ReversedIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new InsertSortWithBinarySearch(markedComparator);

      System.out.print("Insert sort with binary search tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doInsertSortWithBinarySearchShuffledArrayTest() {
    System.out.println("Insert sort with binary search tests for ShuffledIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ShuffledIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new InsertSortWithBinarySearch(markedComparator);

      System.out.print("Insert sort with binary search tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doInsertSortWithBinarySearchRandomArrayTest() {
    System.out.println("Insert sort with binary search tests for RandomIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(MAX_VALUE_FOR_INT_GENERATOR));

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new InsertSortWithBinarySearch(markedComparator);

      System.out.print("Insert sort with binary search tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }
}