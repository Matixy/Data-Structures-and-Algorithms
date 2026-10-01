package sorting;

import core.AbstractSortingAlgorithm;
import sorting.pivotStrategy.Pivot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> extends AbstractSortingAlgorithm<T> {
  public MergeSort(Comparator<? super T> comparator) {
    super(comparator);
  }

  @Override
  public List<T> sort(List<T> list) {
    int n = list.size();

    if (list == null || n < 2) {
      return list;
    }

    for (int width = 1; width < n; width *= 2) {
      for (int i = 0; i < n; i+= 2 * width) {
        int left = i;
        int mid = Math.min(i + width, n);
        int right = Math.min(i + 2 * width, n);
        merge(list, left, mid, right);
      }
    }

    return list;
  }

  private void merge(List<T> list, int left, int mid, int right) {
    List<T> leftList = new ArrayList<>(mid - left);
    List<T> rightList = new ArrayList<>(right - mid);

    for (int i = left; i < mid; i++) {
      leftList.add(list.get(i));
    }
    for (int i = mid; i < right; i++) {
      rightList.add(list.get(i));
    }

    int i = 0;
    int j = 0;
    int k = left;

    while (i < leftList.size() && j < rightList.size()) {
      if (compare(leftList.get(i), rightList.get(j)) <= 0) {
        list.set(k++, leftList.get(i++));
      } else {
        list.set(k++, rightList.get(j++));
      }
    }

    while (i < leftList.size()) {
      list.set(k++, leftList.get(i++));
    }

    while (j < rightList.size()) {
      list.set(k++, rightList.get(j++));
    }
  }
}
