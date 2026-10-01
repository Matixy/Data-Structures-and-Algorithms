package sorting;

import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SelectSortWithMax<T> extends AbstractSwappingSortingAlgorithm<T> {
  public SelectSortWithMax(Comparator<? super T> comparator) {
    super(comparator);
  }

  @Override
  public List<T> sort(List<T> list) {
    int size = list.size();
    for (int slot = size - 1; slot > 0; --slot) {
      int biggest = 0; // pozycja wartości maksymalnej

      for (int check = 1; check <= slot; ++check)
        if (compare(list.get(check), list.get(biggest)) > 0){
          biggest = check;
        }

      // swapujemy jezeli faktycznie trzeba cos zmienic
      if (biggest != slot) {
        swap(list, slot, biggest);
      }
    }

    return list;
  }
}
