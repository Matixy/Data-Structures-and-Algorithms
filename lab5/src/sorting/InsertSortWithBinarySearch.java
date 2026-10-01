package sorting;

import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class InsertSortWithBinarySearch<T> extends AbstractSwappingSortingAlgorithm<T> {

  public InsertSortWithBinarySearch(Comparator<? super T> comparator) {
    super(comparator);
  }

  private int binarySearch(List<T> list, T value, int startIndex, int endIndex) {
    int left = startIndex;
    int right = endIndex;

    while (left <= right) {
      int mid = (left + right) / 2;
      if (compare(value, list.get(mid)) > 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }

  @Override
  public List<T> sort(List<T> list) {
    int size = list.size();

    for (int i = size - 2; i >= 0; i--) {
      T value = list.get(i);

      int insertPos = binarySearch(list, value, i + 1, size - 1);

      for (int j = i; j < insertPos - 1; j++) {
        swap(list, j, j + 1);
      }
    }

    return list;
  }
}
