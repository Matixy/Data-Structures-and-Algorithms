package testingForSorting;

import core.AbstractSwappingSortingAlgorithm;
import sorting.pivotStrategy.FirstElemPivotStrategy;
import sorting.pivotStrategy.Pivot;
import sorting.pivotStrategy.RandomElemPivotStrategy;
import sorting.QuickSort;
import testing.MarkedValue;
import testing.comparators.IntegerComparator;
import testing.comparators.MarkedValueComparator;

import java.util.Comparator;

public class QuickSortTesting extends AbstractSortingTesting {
  public static void quickSortFirstElemTesting() {
    System.out.println("QuickSort pivot First Element tests");

    Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

    Pivot firstElemPivot = new FirstElemPivotStrategy();

    AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new QuickSort<>(markedComparator, firstElemPivot);

    doTestForArrays(algorithm);
  }

  public static void quickSortRandomElemTesting() {
    System.out.println("QuickSort pivot Random Element tests");
    Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

    Pivot firstElemPivot = new RandomElemPivotStrategy();

    AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new QuickSort<>(markedComparator, firstElemPivot);

    doTestForArrays(algorithm);
  }
}
