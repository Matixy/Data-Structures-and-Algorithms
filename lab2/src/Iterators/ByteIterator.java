package Iterators;

import Execptions.BaseIteratorIsNullException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ByteIterator<T> implements Iterator<Integer> {
  Iterator<T> baseIterator;
  private int[] currentBytes;
  private int currentByteIndex;

  public ByteIterator(Iterator baseIterator) {
    if (baseIterator == null) {
      throw new BaseIteratorIsNullException("Base iterator at Iterators.ByteIterator cannot be null");
    }

    this.baseIterator = baseIterator;
    this.currentBytes = null;
    this.currentByteIndex = 0;
  }

  private int[] getBytes(int number) {
    if (number == 0) {
      return new int[]{0};
    }

    int num = number;
    int byteCount = 0;
    while (num != 0) {
      byteCount++;
      num >>= 8;
    }

    int[] bytes = new int[byteCount];
    for (int i = byteCount - 1; i >= 0; i--) {
      bytes[i] = (number & 0xFF);
      number >>= 8;
    }

    return bytes;
  }

  @Override
  public boolean hasNext() {
    return (currentBytes != null && currentByteIndex < currentBytes.length) || baseIterator.hasNext();
  }

  @Override
  public Integer next() {
    if (currentBytes != null && currentByteIndex < currentBytes.length) {
      return currentBytes[currentByteIndex++];
    }

    if (!baseIterator.hasNext()) {
      throw new NoSuchElementException("No more elements!");
    }

    int num = (int) baseIterator.next();
    currentBytes = getBytes(num);
    currentByteIndex = 0;
    return currentBytes[currentByteIndex++];
  }
}
