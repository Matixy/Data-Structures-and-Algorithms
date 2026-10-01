package heaps;
import exceptions.EmptyHeapException;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayBinaryHeap<T> extends AbstractBinaryHeap implements Heap<T> {
  private ArrayList<T> elements;

  public ArrayBinaryHeap(Comparator comparator) {
    super(comparator);
    this.elements = new ArrayList<>();
  }

  public ArrayBinaryHeap(Comparator comparator, T... arrayarrayElements) {
    this(comparator);

    for (T element : arrayarrayElements) {
      this.add(element);
    }
  }

  private void swap(int index1, int index2) {
    T temp = elements.get(index1);
    elements.set(index1, elements.get(index2));
    elements.set(index2, temp);
  }

  // opuszczanie elementu w dół dla tablicy
  private void heapifyDownArray(int index) {
    int size = elements.size();

    while (index < size) {
      int left = 2 * index + 1;
      int right = 2 * index + 2;
      int smallestIndex = index;

      if (left < size && comparator.compare(elements.get(left), elements.get(smallestIndex)) < 0) {
        smallestIndex = left;
      }

      if (right < size && comparator.compare(elements.get(right), elements.get(smallestIndex)) < 0) {
        smallestIndex = right;
      }

      if (smallestIndex != index) {
        swap(index, smallestIndex);
        index = smallestIndex;
      } else {
        break;
      }
    }

  }

  //wynoszenie elementu w górę dla tablicy
  private void heapifyUpArray(int index) {
    while (index > 0) {
      int parentIndex = (index - 1) / 2;
      if (comparator.compare(elements.get(index), elements.get(parentIndex)) < 0) {
        swap(index, parentIndex);
        index = parentIndex;
      } else {
        break;
      }
    }
  }

  public T get(int index) {
    if (index < 0 || index >= elements.size()) {
      throw new IllegalArgumentException("Index out of bounds");
    }

    return elements.get(index);
  }

  public void set(int index, T value) {
    if (index < 0 || index >= elements.size()) {
      throw new IllegalArgumentException("Index out of bounds");
    }

    elements.set(index, value);
  }

  public T peek() {
    if (elements.isEmpty()) {
      throw new EmptyHeapException("Heap is empty");
    }

    return elements.get(0);
  }

  public T removeLastElement() {
    if (elements.isEmpty()) {
      throw new EmptyHeapException("You trying remove element from empty heap");
    }

    return elements.remove(elements.size() - 1);
  }

  @Override
  public T minimum() {
    if ( elements.size() == 0 ) {
      throw new EmptyHeapException("You trying remove element from empty heap");
    }

    T minimum = elements.get(0);

    if (elements.size() > 1) {
      elements.set(0, elements.get(elements.size() - 1));
      heapifyDownArray(0);
    }

    elements.remove(elements.size() - 1);
    return minimum;
  }

  @Override
  public void add(T element) {
    elements.add(element);
    heapifyUpArray(elements.size() - 1);
  }

  @Override
  public void clear() {
    elements.clear();
  }

  @Override
  public int size() {
    return elements.size();
  }

  @Override
  public String toString() {
    return "ArrayBinaryHeap{" +
            "arrayElements=" + elements +
            '}';
  }
}
