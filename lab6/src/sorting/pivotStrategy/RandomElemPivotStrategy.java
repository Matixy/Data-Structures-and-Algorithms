package sorting.pivotStrategy;

import java.util.List;
import java.util.Random;

public class RandomElemPivotStrategy<T> implements Pivot<T> {
  private static Random random = new Random();

  @Override
  public T getPivot(List<T> list, int fromIndex, int toIndex) {
    if (fromIndex > toIndex) {
      throw new IndexOutOfBoundsException("toIndex jest mniejszy od fromIndex!");
    }

    return list.get(random.nextInt(toIndex - fromIndex) + fromIndex);
  }
}
