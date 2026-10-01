package sorting;

import core.AbstractSwappingSortingAlgorithm;
import sorting.pivotStrategy.Pivot;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> extends AbstractSwappingSortingAlgorithm<T> {
  private Pivot<T> pivotStrategy;

  public QuickSort(Comparator<? super T> comparator, Pivot pivotStrategy) {
    super(comparator);
    this.pivotStrategy = pivotStrategy;
  }

  @Override
  public List<T> sort(List<T> list) {
    if (list.isEmpty() || list.size() < 2) {
      return list;
    }

    quicksort(list, 0, list.size() - 1);
    return list;
  }

  private void quicksort(List<T> list, int startIndex, int endIndex) {
    if (endIndex > startIndex) {
      int partition = partition(list, startIndex, endIndex);
      quicksort(list, startIndex, partition);
      quicksort(list, partition + 1, endIndex);
    }
  }

  private int partition(List<T> list, int nFrom, int nTo) {
    //wybor piota
    T pivot = pivotStrategy.getPivot(list, nFrom, nTo);

    int idxBigger = nTo + 1;
    int idxLower = nFrom - 1;
    while (true) {
      do {
        idxLower++;
      } while (compare(list.get(idxLower), pivot) < 0);

      do {
        idxBigger--;
      } while (compare(list.get(idxBigger), pivot) > 0);

      if (idxBigger <= idxLower) {
        return idxBigger;
      }

      swap(list, idxLower, idxBigger);
    }
  }
}
