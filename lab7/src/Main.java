import heaps.ArrayBinaryHeap;
import heaps.ArrayTreeBinaryHeap;
import heaps.TreeBinaryHeap;

import java.util.Comparator;

public class Main {
  /**
   *     TESTY KOPIEC:
   *     <br>- PUSTY
   *     <br>- JEDEN ELEMENT
   *     <br>- PARZYSTA ILOSC ELEM
   *     <br>- NIEPARZYSTA ILOSC ELEM
   *     <br>- NULLE
   */
  public static void doTests() {
    // pusta
    System.out.println("Pusta:");
    ArrayTreeBinaryHeap<Integer> arrayTreeBinaryHeap = new ArrayTreeBinaryHeap<>(Comparator.naturalOrder(), 1);
    System.out.println(arrayTreeBinaryHeap);
    System.out.println("Poziom o najwyzej sumie priorytetow: " + ArrayTreeBinaryHeap.hotlevel(arrayTreeBinaryHeap));
    System.out.println();

    // jeden elem
    System.out.println("Jeden element:");
    arrayTreeBinaryHeap = new ArrayTreeBinaryHeap<>(Comparator.naturalOrder(), 1, 0);
    System.out.println(arrayTreeBinaryHeap);
    System.out.println("Poziom o najwyzej sumie priorytetow: " + ArrayTreeBinaryHeap.hotlevel(arrayTreeBinaryHeap));
    System.out.println();
    arrayTreeBinaryHeap.minimum();
    System.out.println(arrayTreeBinaryHeap);

    // paryszta ilosc
    System.out.println("Paryszta ilosc:");
    arrayTreeBinaryHeap = new ArrayTreeBinaryHeap<>(Comparator.naturalOrder(), 2, 10,2,38,20,7,40,45,19,22,18,19, 54);
    System.out.println(arrayTreeBinaryHeap);
    System.out.println("Poziom o najwyzej sumie priorytetow: " + ArrayTreeBinaryHeap.hotlevel(arrayTreeBinaryHeap));
    System.out.println();
    arrayTreeBinaryHeap.minimum();
    System.out.println(arrayTreeBinaryHeap);

    // nieparzysta ilosc
    System.out.println("Nieparzysta ilosc:");
    arrayTreeBinaryHeap = new ArrayTreeBinaryHeap<>(Comparator.naturalOrder(), 1, 10,2,38,20,7,40,45,19,22,18,19);
    System.out.println(arrayTreeBinaryHeap);
    System.out.println("Poziom o najwyzej sumie priorytetow: " + ArrayTreeBinaryHeap.hotlevel(arrayTreeBinaryHeap));
    System.out.println();
    arrayTreeBinaryHeap.minimum();
    System.out.println(arrayTreeBinaryHeap);

    // z przykladu
    System.out.println("Z przykladu:");
    arrayTreeBinaryHeap = new ArrayTreeBinaryHeap<>(Comparator.naturalOrder(), 1, 0,1,5,2,7,7,9,5,5,8,12,9,20,10,11, 9, 6, 7, 8, 10);
    System.out.println(arrayTreeBinaryHeap);
    System.out.println("Poziom o najwyzej sumie priorytetow: " + ArrayTreeBinaryHeap.hotlevel(arrayTreeBinaryHeap));
    System.out.println();
    arrayTreeBinaryHeap.minimum();
    System.out.println(arrayTreeBinaryHeap);


    // kopiec ujemny
    System.out.println("Ujemny kopiec:");
    arrayTreeBinaryHeap = new ArrayTreeBinaryHeap<>(Comparator.naturalOrder(), 1, -3, -2, -2, -1, -1, -1, -1);
    System.out.println(arrayTreeBinaryHeap);
    System.out.println("Poziom o najwyzej sumie priorytetow: " + ArrayTreeBinaryHeap.hotlevel(arrayTreeBinaryHeap));

  }

  public static void main(String[] args) {
    doTests();
  }
}