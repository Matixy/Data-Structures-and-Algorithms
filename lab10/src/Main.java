import heaps.MaxBinomialHeap;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
  /**
   * Testy:
   * <br/> Pusta tablica
   * <br/> Jeden element
   * <br/> Parzysta liczba elementow
   * <br/> Nieparzysta liczba elementow
   */
  public static void doTestEmpty() {
    System.out.println("Test empty");

    MaxBinomialHeap<Integer> maxBinomialHeap = new MaxBinomialHeap<>(Integer::compareTo);

    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
    System.out.println("Maksymalny element: " + maxBinomialHeap.maximum());
    System.out.println("Usuniecie max element: " + maxBinomialHeap.extractMax());
    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
  }

  public static void doTestOneElem() {
    System.out.println("Test one elem");

    MaxBinomialHeap<Integer> maxBinomialHeap = new MaxBinomialHeap<>(Integer::compareTo);

    maxBinomialHeap.insert(1);

    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
    System.out.println("Maksymalny element: " + maxBinomialHeap.maximum());
    System.out.println("Usuniecie max element: " + maxBinomialHeap.extractMax());
    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
  }

  public static void doTestEven() {
    System.out.println("Test even");

    MaxBinomialHeap<Integer> maxBinomialHeap = new MaxBinomialHeap<>(Integer::compareTo);

    maxBinomialHeap.insert(1);
    maxBinomialHeap.insert(2);
    maxBinomialHeap.insert(3);
    maxBinomialHeap.insert(4);
    maxBinomialHeap.insert(5);
    maxBinomialHeap.insert(6);

    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
    System.out.println("Maksymalny element: " + maxBinomialHeap.maximum());
    System.out.println("Usuniecie max element: " + maxBinomialHeap.extractMax());
    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
  }

  public static void doTestOdd() {
    System.out.println("Test odd");

    MaxBinomialHeap<Integer> maxBinomialHeap = new MaxBinomialHeap<>(Integer::compareTo);

    maxBinomialHeap.insert(1);
    maxBinomialHeap.insert(2);
    maxBinomialHeap.insert(3);
    maxBinomialHeap.insert(8);
    maxBinomialHeap.insert(5);
    maxBinomialHeap.insert(6);
    maxBinomialHeap.insert(7);

    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
    System.out.println("Maksymalny element: " + maxBinomialHeap.maximum());
    System.out.println("Usuniecie max element: " + maxBinomialHeap.extractMax());
    System.out.println(maxBinomialHeap);
    System.out.println("Czytanie poziomami: " + maxBinomialHeap.levels());
  }

  public static void main(String[] args) {
    doTestEmpty();
    doTestOneElem();
    doTestEven();
    doTestOdd();
  }
}