package testingForSorting;

import core.AbstractSwappingSortingAlgorithm;
import sorting.ShakerSort;
import testing.MarkedValue;
import testing.Tester;
import testing.comparators.IntegerComparator;
import testing.comparators.MarkedValueComparator;
import testing.generation.*;
import testing.generation.conversion.MarkingGenerator;

import java.util.Comparator;

public class ShakerSortTesting extends AbstractTesting {
  public static void doShakerSortTest() {
    System.out.println("Shaker sort tests started\n");

    doShakerSortOrderedArrayTest();
    doShakerSortReversedArrayTest();
    doShakerSortShuffledArrayTest();
    doShakerSortRandomArrayTest();
  }

  public static void doShakerSortOrderedArrayTest() {
    System.out.println("Shaker sort tests for OrderedIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new OrderedIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ShakerSort<>(markedComparator);

      System.out.print("Shaker sort tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doShakerSortReversedArrayTest() {
    System.out.println("Shaker sort tests for ReversedIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ReversedIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ShakerSort<>(markedComparator);

      System.out.print("Shaker sort tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doShakerSortShuffledArrayTest() {
    System.out.println("Shaker sort tests for ShuffledIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ShuffledIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ShakerSort<>(markedComparator);

      System.out.print("Shaker sort tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doShakerSortRandomArrayTest() {
    System.out.println("Shaker sort tests for RandomIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(MAX_VALUE_FOR_INT_GENERATOR));

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ShakerSort<>(markedComparator);

      System.out.print("Shaker sort tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }
}