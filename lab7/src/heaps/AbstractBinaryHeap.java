package heaps;

import java.util.Comparator;

public abstract class AbstractBinaryHeap {
  protected final Comparator comparator;

  public AbstractBinaryHeap(Comparator comparator) {
    this.comparator = comparator;
  }
}
