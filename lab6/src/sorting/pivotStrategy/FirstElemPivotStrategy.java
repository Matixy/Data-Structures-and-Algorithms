package sorting.pivotStrategy;

import java.util.List;

public class FirstElemPivotStrategy<T> implements Pivot<T> {
  @Override
  public T getPivot(List<T> list, int fromIndex, int toIndex) {
    return list.get(fromIndex);
  }
}
