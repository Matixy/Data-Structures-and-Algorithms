package sorting.pivotStrategy;

import java.util.List;

public interface Pivot<T> {
  T getPivot(List<T> list, int fromIndex, int toIndex);
}
