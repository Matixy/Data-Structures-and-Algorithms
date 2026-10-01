package heaps;

import exceptions.EmptyHeapException;
import interfaces.Heap;

import java.util.*;

public class TreeBinaryHeap<T> extends AbstractBinaryHeap implements Heap<T> {
  private Node<T> root;
  private int size;

  public TreeBinaryHeap(Comparator comparator) {
    super(comparator);
  }

  public TreeBinaryHeap(Comparator comparator, T... arrayarrayElements) {
    this(comparator);

    for (T element : arrayarrayElements) {
      this.add(element);
    }
  }

  private void swapKeys(Node<T> node1, Node<T> node2) {
    T tmp = node1.key;
    node1.key = node2.key;
    node2.key = tmp;
  }

  // metoda szukajaca sciezki od korzenia do miejsca gdzie jest wolny liscia
  private List<Node<T>> findPathToParentOfNextLeaf() {
    Node<T> currentNode = root;
    List<Node<T>> path = new ArrayList<>();
    int msb = 31 - Integer.numberOfLeadingZeros(size); // oblicz numer najbardziej znaczacego ustawionego bitu 31 - size (31 bo int ma 32 bity wiec max index to 31)

    for (int i = msb - 1; i >= 1; i--) { // iterowanie po kolejnych bitach sciezki do rodzica
      path.add(currentNode);
      int bit = (size >> i) & 1;

      // jezeli bit == 1 idz w prawo jezeli nie to w lewo
      if (bit == 1) {
        currentNode = currentNode.right;
      } else {
        currentNode = currentNode.left;
      }
    }

    path.add(currentNode); // dodaj ostatni wezel
    return path;
  }

  // metoda szukajaca sciezki od korzenia do miejsca gdzie jest wolny liscia
  private List<Node<T>> findPathToLastNode() {
    Node<T> currentNode = root;
    List<Node<T>> path = new ArrayList<>();
    int msb = 31 - Integer.numberOfLeadingZeros(size); // oblicz numer najbardziej znaczacego ustawionego bitu 31 - size (31 bo int ma 32 bity wiec max index to 31)

    for (int i = msb - 1; i >= 0; i--) { // nalezy isc do ostaniego wezla dlatego iteracja jest do ostaniego bitu
      path.add(currentNode);
      int bit = (size >> i) & 1;

      // jezeli bit == 1 idz w prawo jezeli nie to w lewo
      if (bit == 1) {
        currentNode = currentNode.right;
      } else {
        currentNode = currentNode.left;
      }
    }

    path.add(currentNode); // dodaj ostani lisc- ten ktory nalezy usunac
    return path;
  }

  // wynoszenie elementu
  private void heapifyUp(List<Node<T>> path) {
    for (int i = path.size() - 1; i > 0; i--) {
      Node<T> child = path.get(i);
      Node<T> parent = path.get(i - 1);
      if (comparator.compare(child.key, parent.key) < 0) {
        swapKeys(parent, child);
      } else {
        break;
      }
    }
  }

  // zatapainie elementu
  public void heapifyDown(Node<T> node) {
    if ( node == null ) {
      node = root;
    }

    while (node.left != null) {
      Node<T> smallerChild = node.left;

      if (node.right != null && comparator.compare(node.right.key, smallerChild.key) < 0) {
        smallerChild = node.right;
      }

      if (comparator.compare(smallerChild.key, node.key) < 0) {
        swapKeys(node, smallerChild);
        node = smallerChild;
      } else {
        break;
      }
    }
  }

  // metoda usuwa ostatni wezel i zwraca jego klucz
  private T removeLastNode() {
    T key;

    if (size == 1) {
      key = root.key;
      root = null;
      return key;
    } else {
      List<Node<T>> path = findPathToLastNode();
      Node<T> currentNode = path.get(path.size() - 1);
      key = currentNode.key;

      Node<T> parent = path.get(path.size() - 2); // wez rodzica

      // usun referencje z rodzica na usuwany wezel
      if ( parent.right == currentNode ) {
        parent.right = null;
      } else {
        parent.left = null;
      }
    }

    return key;
  }

  // metoda pomocnicza dla ArrayTreeBinaryHeap- zwracajaca wezel kopca po globalnym indexie w strukturze
  private Node<T> getNodeByGlobalIndex(int index, int H) {
    if ( root == null ) {
      throw new EmptyHeapException("Heap is empty");
    }

    int depth = 31 - Integer.numberOfLeadingZeros(index);

    // pomin H + 1 bitow prowadzacych to tego kopca
    int skip = depth - (H + 1);
    Node<T> current = root;

    // iteruj po kolejnych bitach zeby zejsc w dol drzewa
    for (int i = skip - 1; i >= 0; i--) {
      int bit = (index >> i) & 1;

      if ( bit == 1 ) {
        current = current.right;
      } else {
        current = current.left;
      }

      // gdy podano zly indeks - wykroczono poza zakres drzewa
      if (current == null) {
        throw new IllegalArgumentException("Invalid global index");
      }
    }

    return current;
  }

  // metoda pomocnicza dla ArrayTreeBinaryHeap- zwracajaca wartosc wezla kopca po globalnym indexie w strukturze
  public T getByGlobalIndex(int index, int H) {
    Node<T> node = getNodeByGlobalIndex(index, H);
    return node.key;
  }

  // metoda pomocnicza dla ArrayTreeBinaryHeap- ustawiajaca wartosc wezla kopca po globalnym indexie w strukturze
  public void setByGlobalIndex(int index, int H, T val) {
    Node<T> node = getNodeByGlobalIndex(index, H);
    node.key = val;
  }

  public T removeLastElement() {
    if (size == 0) {
      throw new EmptyHeapException("You trying remove element from empty heap");
    }

    T val = removeLastNode();
    size--;
    return val;
  }

  @Override
  public T minimum() {
    if (root == null) {
      throw new EmptyHeapException("You trying remove element from empty heap");
    }

    T minimum = root.key;


    if (size == 1) {
      root = null;
    } else {
      T last = removeLastNode();
      root.key = last;
      heapifyDown(root);
    }

    size--;
    return minimum;
  }

  @Override
  public void add(T element) {
    Node<T> newNode = new Node(element);
    size++;

    if (root == null) {
      root = newNode;
      return;
    }

    List<Node<T>> path = findPathToParentOfNextLeaf();
    Node<T> parent = path.get(path.size() - 1); // wez rodzica

    // dodaj referencje do nowego wezla w rodzicu
    if (parent.left == null) {
      parent.left = newNode;
    } else {
      parent.right = newNode;
    }

    path.add(newNode); // dodaj do sciezki od korzenia do rodzica ostatni wezel
    heapifyUp(path); // odpowiednio wynies element od liscia do korzenia (napraw kopiec)
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    root = null;
    size = 0;
  }

  public String printTree() {
    if (root == null) {
      return "<empty>";
    }

    List<Node<T>> q = new ArrayList<>();
    q.add(root);
    String str = "";

    for (int i = 0; i < q.size(); i++) {
      Node<T> n = q.get(i);
      str += n.key + " ";

      if (n.left != null) q.add(n.left);
      if (n.right != null) q.add(n.right);
    }

    return str;
  }

  @Override
  public String toString() {
    return "TreeBinaryHeap{"
            + printTree() +
            "}";
  }

  // klasa wewnetrzna
  private class Node<T> {
    public T key;
    public Node<T> left;
    public Node<T> right;

    Node(T key) {
      this.key = key;
    }

    @Override
    public String toString() {
      return "Node{" +
              "key=" + key +
              '}';
    }
  }
}
