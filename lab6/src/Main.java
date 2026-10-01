import testingForSorting.MergeSortTesting;
import testingForSorting.QuickSortTesting;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		MergeSortTesting.mergeSortArraysTesting();

		MergeSortTesting.mergeSortListsTesting();

		QuickSortTesting.quickSortFirstElemTesting();

		QuickSortTesting.quickSortRandomElemTesting();

	}
}
