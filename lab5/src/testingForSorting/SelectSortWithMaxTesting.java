package testingForSorting;

import core.AbstractSwappingSortingAlgorithm;
import sorting.SelectSortWithMax;
import testing.MarkedValue;
import testing.Tester;
import testing.comparators.IntegerComparator;
import testing.comparators.MarkedValueComparator;
import testing.generation.*;
import testing.generation.conversion.MarkingGenerator;
import testingForSorting.AbstractTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SelectSortWithMaxTesting extends AbstractTesting {
  public static void doSelectSortWithMaxTest() {
    System.out.println("Select sort with max tests started\n");

    doSelectSortWithMaxArrayTest();
    doSelectSortWithMaxReversedArrayTest();
    doSelectSortWithMaxShuffledArrayTest();
    doSelectSortWithMaxRandomArrayTest();
  }

  public static void doSelectSortWithMaxArrayTest() {
    System.out.println("SelectSortWithMax tests for OrderedIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new OrderedIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new SelectSortWithMax<>(markedComparator);

      System.out.print("Select sort with max tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doSelectSortWithMaxReversedArrayTest() {
    System.out.println("Select sort with max tests for ReversedIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ReversedIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new SelectSortWithMax(markedComparator);

      System.out.print("Select sort with max tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doSelectSortWithMaxShuffledArrayTest() {
    System.out.println("Select sort with max tests for ShuffledIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ShuffledIntegerArrayGenerator());

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new SelectSortWithMax(markedComparator);

      System.out.print("Select sort with max tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }

  public static void doSelectSortWithMaxRandomArrayTest() {
    System.out.println("Select sort with max tests for RandomIntegerArrayGenerator\n");

    for (int size : ARRAYS_SIZES) {
      Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

      Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(MAX_VALUE_FOR_INT_GENERATOR));

      AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new SelectSortWithMax(markedComparator);

      System.out.print("Select sort with max tests for size= " + size + "\n");
      testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, VALUE_FOR_REPETITIONS);
      printResults(result);
    }
  }
}