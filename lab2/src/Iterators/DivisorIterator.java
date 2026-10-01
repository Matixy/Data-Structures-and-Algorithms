package Iterators;

import Execptions.InfinityDividerExeption;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DivisorIterator implements Iterator<Integer>, Iterable<Integer> {
  private final int num;
  private Integer nextDivisor;
  private boolean isDivisorSmallerThanSqrt = true;
  private int i; // indeks potrzebny dla wyszukiwania dzielnikow mniejszych od pierwiastka
  private int j; // indeks potrzebny dla wyszukiwania dzielnikow wiekszych od pierwiastka

  public DivisorIterator(int num) {
    if (num == 0) {
      throw new InfinityDividerExeption("0 has infinite divisors");
    }

    this.num = Math.abs(num);
    this.i = 1;
    this.nextDivisor = findNextDivisor();
  }

  private Integer findNextDivisor() {
    if (isDivisorSmallerThanSqrt) {
      while (i * i <= num ) {
        if (num % i == 0) {
          return i++;
        }

        i++;
      }

      isDivisorSmallerThanSqrt = false;
      j = (int) Math.sqrt(num);
    }

    while (j >= 1) {
      if (num % j == 0) {
        int findingNextDivisor = num / j;
        j--;
        if (findingNextDivisor != j + 1) {
          return findingNextDivisor;
        }
      } else {
        j--;
      }
    }

    return null; // brak dzielnikow
  }

  @Override
  public boolean hasNext() {
    return nextDivisor != null;
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    int res = nextDivisor;
    nextDivisor = findNextDivisor();
    return res;
  }

  @Override
  public Iterator<Integer> iterator() {
    return this;
  }
}
