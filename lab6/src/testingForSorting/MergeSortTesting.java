package testingForSorting;

import core.AbstractSortingAlgorithm;
import sorting.MergeSort;
import testing.MarkedValue;
import testing.comparators.IntegerComparator;
import testing.comparators.MarkedValueComparator;

import java.util.Comparator;

public class MergeSortTesting extends AbstractSortingTesting {
  public static void mergeSortArraysTesting() {
    System.out.println("Merge sort with arrays tests");

    Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

    AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm = new MergeSort<>(markedComparator);

    doTestForArrays(algorithm);
  }

  public static void mergeSortListsTesting() {
    System.out.println("Merge sort with lists tests");

    Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

    AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm = new MergeSort<>(markedComparator);

    doTestForLists(algorithm);
  }
}
