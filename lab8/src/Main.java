import executors.IntegerExecutor;
import interfaces.Executor;
import trees.BST;

import java.util.List;

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

    BST<Integer> bst = new BST<Integer>(Integer::compare);

    System.out.println("Szukanie elementu 5: " + bst.search(5));
    System.out.println("Szukanie elementu 90: " + bst.search(90));

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));

    System.out.println("Przejscie inOrder: " + bst.inOrderWalk(new IntegerExecutor()));
    System.out.println();
  }

  public static void doTestOneElem() {
    System.out.println("Test one elem");

    BST<Integer> bst = new BST<Integer>(Integer::compare);
    bst.insert(5);

    System.out.println("Minimum: " + bst.getMinimum());
    System.out.println("Maximum: " + bst.getMaximum());
    System.out.println("Szukanie elementu 5: " + bst.search(5));
    System.out.println("Szukanie elementu 90: " + bst.search(90));
    System.out.println("Poprzednik 5: " + bst.findPredecessor(5));

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));

    System.out.println("Usuniecie 5: " + bst.delete(5));
    System.out.println("Przejscie inOrder: " + bst.inOrderWalk(new IntegerExecutor()));
    System.out.println();
  }

  public static void doTestEven() {
    System.out.println("Test even");

    BST<Integer> bst = new BST<Integer>(Integer::compare);
    bst.insert(5);
    bst.insert(3);
    bst.insert(7);
    bst.insert(11);
    bst.insert(4);
    bst.insert(2);
    bst.insert(12);
    bst.insert(10);
    

    System.out.println("Minimum: " + bst.getMinimum());
    System.out.println("Maximum: " + bst.getMaximum());
    System.out.println("Szukanie elementu 5: " + bst.search(5));
    System.out.println("Szukanie elementu 90: " + bst.search(90));

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));

    System.out.println("Poprzednik 5: " + bst.findPredecessor(5));
    System.out.println("Usuniecie 7: " + bst.delete(7));
    System.out.println("Przejscie inOrder: " + bst.inOrderWalk(new IntegerExecutor()));
    System.out.println();
  }

  public static void doTestOdd() {
    System.out.println("Test odd");

    BST<Integer> bst = new BST<Integer>(Integer::compare);
    bst.insert(5);
    bst.insert(3);
    bst.insert(7);
    bst.insert(11);
    bst.insert(4);
    bst.insert(12);
    bst.insert(10);

    System.out.println("Minimum: " + bst.getMinimum());
    System.out.println("Maximum: " + bst.getMaximum());
    System.out.println("Szukanie elementu 5: " + bst.search(5));
    System.out.println("Szukanie elementu 90: " + bst.search(90));
    System.out.println("Poprzednik 10: " + bst.findPredecessor(10));

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));

    System.out.println("Usuniecie 5: " + bst.delete(5));
    System.out.println("Przejscie inOrder: " + bst.inOrderWalk(new IntegerExecutor()));
    System.out.println();
  }

  public static void doTestLab() {
    System.out.println("Test lab");
    BST<Integer> bst = new BST<>(Integer::compare);
    bst.insert(6);
    bst.insert(1);
    bst.insert(5);
    bst.insert(3);
    bst.insert(4);
    bst.insert(10);
    bst.insert(7);
    bst.insert(11);

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));

    System.out.println();
  }

  public static void doTestLab2() {
    System.out.println("Test wyklad");
    BST<Integer> bst = new BST<>(Integer::compare);

    bst.insert(7);
    bst.insert(10);
    bst.insert(12);
    bst.insert(8);
    bst.insert(9);
    bst.insert(6);
    bst.insert(3);
    bst.insert(4);
    bst.insert(5);

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));
    System.out.println();
  }

  public static void doTestLab3() {
    System.out.println("Test lab2");
    BST<Integer> bst = new BST<>(Integer::compare);

    bst.insert(100);
    bst.insert(90);
    bst.insert(80);
    bst.insert(81);
    bst.insert(82);
    bst.insert(92);
    bst.insert(95);
    bst.insert(91);
    bst.insert(110);
    bst.insert(105);
    bst.insert(106);

    BST<Integer> subTree =  bst.mostImbalancedSubtree();
    System.out.println("Najbardziej niezbalansowane poddrzewo: " + subTree.inOrderWalk(new IntegerExecutor()));
    System.out.println();
  }

  public static void main(String[] args) {
    doTestEmpty();
    doTestOneElem();
    doTestEven();
    doTestOdd();
    doTestLab();
    doTestLab2();
    doTestLab3();
  }
}