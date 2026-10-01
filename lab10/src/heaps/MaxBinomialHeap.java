package heaps;

import java.util.*;

public class MaxBinomialHeap<T> {
  private final Comparator<T> comparator;
  private Node head;

  public MaxBinomialHeap(Comparator<T> comparator) {
    this.comparator = comparator;
    head = null;
  }

  public void insert(T element) {
    MaxBinomialHeap<T> newRoot = new MaxBinomialHeap<>(comparator);
    newRoot.head = new Node(element);
    this.union(newRoot);
  }

  public T maximum() {
    FindRootResult maxRoot = findMaxRoot();

    if (maxRoot == null) {
      return null;
    }

    return maxRoot.root.key;
  }

  public T extractMax() {
    if (head == null) {
      return null;
    }

    // znajdz maxymalny korzen
    FindRootResult maxRoot = findMaxRoot();
    T max = maxRoot.root.key;

    Node newHeapRoot = detachAndReverseChildren(maxRoot.root, maxRoot.previous);

    // zbuduj kopiec z dzieci i scal go
    MaxBinomialHeap<T> childHeap = new MaxBinomialHeap<>(comparator);
    childHeap.head = newHeapRoot;
    union(childHeap);

    return max;
  }

  public void union(MaxBinomialHeap<T> other) {
    // stworz pomocnicza liste korzeni wraz z nowym krozeniem posortowanej wedlug stopni
    LinkedList<Node> merged = mergeRootList(head, other.head);

    if (merged.isEmpty()) {
      head = null;
      return;
    }
    if (merged.size() == 1) {
      head = merged.getFirst();
      return;
    }

    // laczenie drzew tego samego stopnia
    ListIterator<Node> it = merged.listIterator();
    Node previous = null;
    Node current = it.next();
    head = current;

    while (it.hasNext()) {
      Node next = it.next();
      // podejrzenie, czy istnieje nextNext
      Node nextNext = null;
      if (it.hasNext()) {
        nextNext = it.next();
        it.previous();
      }

      if (current.degree != next.degree
              || (nextNext != null && nextNext.degree == current.degree)) {
        current.sibling = next;
        previous = current;
        current = next;
      } else {
        // Łączymy current i next
        if (comparator.compare(current.key, next.key) >= 0) {
          // next staje się dzieckiem current
          current.sibling = next.sibling;
          linkTrees(next, current);
        } else {
          // current staje się dzieckiem next
          if (previous == null) head = next;
          else previous.sibling = next;
          linkTrees(current, next);
          current = next;
        }
      }
    }

    // odciecie ogonu
    current.sibling = null;
  }

  private void linkTrees(Node y, Node z) {
    y.parent = z;
    y.sibling = z.child;
    z.child = y;
    z.degree++;
  }

  private LinkedList<Node> mergeRootList(Node head1, Node head2) {
    LinkedList<Node> mergedList = new LinkedList<>();

    while (head1 != null || head2 != null) {
      if (head2 == null || (head1 != null && head1.degree <= head2.degree)) {
        mergedList.add(head1);
        head1 = head1.sibling;
      } else {
        mergedList.add(head2);
        head2 = head2.sibling;
      }
    }

    return mergedList;
  }

  private FindRootResult findMaxRoot() {
    if (head == null) {
      return null;
    }

    Node maxRoot = head;
    Node current = head;

    Node prevMaxRoot = null;
    Node prev = null;

    while (current != null) {
      if (comparator.compare(current.key, maxRoot.key) > 0) {
        maxRoot = current;
        prevMaxRoot = prev;
      }

      prev = current;
      current = current.sibling;
    }

    return new FindRootResult(maxRoot, prevMaxRoot);
  }

  private Node detachAndReverseChildren(Node root, Node prevRoot) {
    // usuniecie wezla z listy korzeni
    if (prevRoot == null) {
      head = root.sibling;
    } else {
      prevRoot.sibling = root.sibling;
    }

    // odwrocenie listy dzieci wezla
    Node child = root.child;
    Node reversed = null;
    while (child != null) {
      Node next = child.sibling;
      child.sibling = reversed;
      child.parent  = null;
      reversed = child;
      child = next;
    }

    return reversed;
  }

  public List<T> levels() {
    List<T> result = new ArrayList<>();

    if (head == null) {
      return result;
    }


    Queue<Node> queue = new LinkedList<>(); // kolejka poziomow
    queue.add(head);

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      while (current != null) {
        result.add(current.key);

        if (current.child != null) {
          queue.add(current.child);
        }

        current = current.sibling;
      }
    }

    return result;
  }

  /**
   * wewnetrzna klasa pomocniczna reprezentujaca wezel drzewa dwumianowego
   */
  public class Node {
    public T key;
    int degree;
    public Node parent;
    public Node child;
    public Node sibling;

    public Node() {
      this.key = null;
      this.degree = 0;
      this.parent = null;
      this.child = null;
      this.sibling = null;
    }

    public Node(T key) {
      this();
      this.key = key;
    }
  }

  /**
   * wewnetrzna klasa pomocniczna reprezentujaca wyszukany wezel wraz z jego poprzednim wezlem
   */
  public class FindRootResult {
    public Node previous;
    public Node root;

    public FindRootResult(Node root, Node previous) {
      this.previous = previous;
      this.root = root;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("MaxBinomialHeap{\n");

    traverse(head, sb, 0);

    return sb + "}";
  }

  /**
   * Pomocnicza metoda rekurencyjna wypisująca drzewo z wcięciami.
   */
  private void traverse(Node node, StringBuilder sb, int indent) {
    if (node == null) return;
    // dodaj wcięcie
    for (int i = 0; i < indent; i++) sb.append("  ");
    // wypisz węzeł
    sb.append(node.key)
            .append(" (deg=").append(node.degree).append(")\n");
    // najpierw dzieci, potem rodzeństwo na tym samym poziomie
    traverse(node.child, sb, indent + 1);
    traverse(node.sibling, sb, indent);
  }

}
