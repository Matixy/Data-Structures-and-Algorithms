package Iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ExtractingIterator<T> implements Iterator<Iterator<T>> {
  private T[] array;
  private int index;
  private ArrayList<T> usedElementsArray;

  public ExtractingIterator(T[] array) {
    if (array == null) {
      throw new NullPointerException("array at ExtractingIterator is null");
    }

    this.array = array;
    this.usedElementsArray = new ArrayList<T>();
    this.index = 0;
  }

  // liczymy ile razy wartosc wystapila w tablicy i dodajemy ja do tablicy wykorzystanych elementow
  private int countElementInArray(T element) {
    int counter = 0;

    usedElementsArray.add(element);

    for (int i = 0; i < array.length; i++) {
      if (array[i] == element) {
        counter++;
      }
    }

    return counter;
  }

  @Override
  public boolean hasNext() {
    return index < array.length ;
  }

  @Override
  public Iterator<T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements at ExtractingIterator");
    }

    while (usedElementsArray.contains(array[index]) && (index + 1) < array.length) {
      index++;
    }

    T element = array[index];
    index++;

    Iterator<T> countIterator = new CountIterator(element, countElementInArray(element));

    return countIterator;
  }

  // prywatna klasa- ktora jest zwracana
  class CountIterator<T> implements Iterator<T> {
    private int length;
    private int index;
    private T element;

    public CountIterator(T element, int length) {
      this.length = length;
      this.element = element;
      this.index = 0;
    }

    @Override
    public boolean hasNext() {
      return index < length;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements in countIterator");
      }

      index++;
      return element;
    }
  }
}
