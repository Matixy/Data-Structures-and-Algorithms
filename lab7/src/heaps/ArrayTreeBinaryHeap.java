package heaps;

import exceptions.EmptyHeapException;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayTreeBinaryHeap<T> extends AbstractBinaryHeap implements Heap<T> {
  private int size;
  private final int H;
  private int arrayHeapMaxSize;
  private int treeHeapsSize;

  private ArrayBinaryHeap<T> arrayHeap;
  private ArrayList<TreeBinaryHeap<T>> heapTrees;

  public ArrayTreeBinaryHeap(Comparator comparator, int H) {
    super(comparator);

    if (H < 0) {
      throw new IllegalArgumentException("Height must be >= 0");
    }

    this.H = H;
    this.arrayHeap = new ArrayBinaryHeap<>(comparator);
    this.arrayHeapMaxSize = (int) Math.pow(2, H + 1) - 1;
    this.treeHeapsSize = (int) Math.pow(2, H + 1);
    this.heapTrees = new ArrayList<>(treeHeapsSize);
    this.size = 0;
  }

  public ArrayTreeBinaryHeap(Comparator comparator, int H, T... elements) {
    this(comparator, H);

    for (T element : elements) {
      this.add(element);
    }
  }

  // zatapianie elementu w strukturze
  private void heapifyDownGlobal() {
    int index = 1; // globalny numer korzenia
    while (true) {
      int left = index * 2;
      int right = index * 2 + 1;

      // jezeli petla przeszla do liscia zakoncz petle - kopiec jest naprawiony
      if (left > size) {
        break;
      }

      // wybierz dziecko o mniejszej wartosci
      int smallest = left;
      if (right <= size && compareGlobal(right, left) < 0) {
        smallest = right;
      }

      // sprawdź czy drzewo zostalo naprawione
      if (compareGlobal(index, smallest) <= 0) {
        break;
      }

      // zamień globalnie
      swapGlobal(index, smallest);
      index = smallest;

      if (index > arrayHeapMaxSize) {
        int subtreeIndex = findSubtreeIndex(smallest);
        TreeBinaryHeap<T> subtree = heapTrees.get(subtreeIndex);
        subtree.heapifyDown(null);

        break;
      }
    }
  }

  // wynoszenie elementu w gore struktury
  private void heapifyUpGlobal(int index) {
    while (index > 1) {
      int parent = index / 2;
      if (compareGlobal(index, parent) < 0) {
        swapGlobal(index, parent);
        index = parent;
      } else {
        break;
      }
    }
  }

  // metoda pomocnicza - wyszukujaca indeks podkopca w tablicy treeHeaps
  private int findSubtreeIndex(int i) {
    int depth = 31 - Integer.numberOfLeadingZeros(i); // glebokosc drzewa- ilosc bitow potrzebna do zapisania indexu
    int subtreeIndex = 0; // indeks danego podkopca (jest on budowany binarnie na podstawie sciezki i)
    for (int d = 1; d <= H + 1; d++) { // iterowanie od 1 glebokosci do h+1 bo do h struktura jest tablicowa
      int bit = ( (i) >> (depth - d)) & 1; // wybor czy w lewo (0) czy prawo(1)
      subtreeIndex = (subtreeIndex << 1) | bit; // dodanie bitu do indexu
    }
    return subtreeIndex;
  }

  // porownanie elementow
  private int compareGlobal(int i, int j) {
    return comparator.compare(getGlobal(i), getGlobal(j));
  }

  private T getGlobal(int i) {
    if (i <= arrayHeapMaxSize) {
      return arrayHeap.get(i - 1);
    } else {
      int subtreeIndex = findSubtreeIndex(i); // znajdź podkopiec
      return heapTrees.get(subtreeIndex).getByGlobalIndex(i, H); // znajdz wartosc w odpowiednim wezle w podkopcu i ja zwroc
    }
  }

  private void setGlobal(int i, T val) {
    if (i <= arrayHeapMaxSize) {
      arrayHeap.set(i - 1, val);
    } else {
      int subtreeIndex = findSubtreeIndex(i); // znajdź podkopiec
      heapTrees.get(subtreeIndex).setByGlobalIndex(i, H, val); // znajdz wartosc w odpowiednim wezele w podkopcu i go zamien
    }
  }

  private void swapGlobal(int i, int j) {
    T vi = getGlobal(i);
    T vj = getGlobal(j);
    setGlobal(i, vj);
    setGlobal(j, vi);
  }

  public static List<Integer> hotlevel(ArrayTreeBinaryHeap<Integer> heap) {
    if (heap.size() == 0) {
      return new ArrayList<>();
    }

    int firstElem = heap.getGlobal(1);

    List<Integer> mostSumResult = new ArrayList<>();
    mostSumResult.add(firstElem);
    int mostSum = firstElem; // suma na poczatku = korzen

    for (int i = 1; i <= heap.size(); i *= 2) {
      List<Integer> result = new ArrayList<>();
      int sum = 0;

      int j = i;
      while (j <= heap.size() && j <= i*2 - 1) {
        Integer elem = heap.getGlobal(j);
        result.add(elem);
        sum += elem;

        j++;
      }

      if (sum > mostSum) {
        mostSum = sum;
        mostSumResult = result;
      }
    }

    return mostSumResult;
  }

  @Override
  public void add(T element) {
    if (element == null) {
      throw new IllegalArgumentException("Element cannot be null");
    }

    if ( arrayHeap.size() < arrayHeapMaxSize ) {
      arrayHeap.add(element); // jezeli nie przekorczono jeszcze czesci tablicowej dodajemy za pomoca metody tablicowej
    } else {
      // jezeli przekorczono czesci tablicowej dodajemy sprawdzamy czy kopiec istnieje i do niego dodajemy
      if ( heapTrees.size() < treeHeapsSize ) {
        heapTrees.add(new TreeBinaryHeap<>(comparator, element));
      } else {
        int subtreeIndex = findSubtreeIndex(size + 1); // nalezy znalesc odpowiedni podkopiec jezli wszystkie sa utworzone (parametr size + 1 bo potrzebny jest indeks tego drzewa gdzie dodany bedzie element)
        heapTrees.get(subtreeIndex).add(element);
      }

    }

    size++;
    heapifyUpGlobal(size); // wynoszenie dodanego elementu
  }

  @Override
  public T minimum() {
    if ( size == 0 ) {
      throw new EmptyHeapException("You trying remove element from empty heap");
    }

    // globalne minimum struktury
    T minimum = arrayHeap.peek();

    // pobranie ostatniego globalnego elementu
    T lastValue;
    if (size <= arrayHeapMaxSize) {
      // z czesci tablicowej jezeli nie przekroczo
      lastValue = arrayHeap.removeLastElement();
    } else {
      // z odpowiedniego podkopca
      int subtreeIndex = findSubtreeIndex(size);
      TreeBinaryHeap<T> tree = heapTrees.get(subtreeIndex);
      lastValue = tree.removeLastElement();

      if (tree.size() == 0) {
        heapTrees.remove(subtreeIndex); // jezeli podkopiec jest pusty usun go ze struktury
      }
    }

    size--;
    if ( size > 0 ) {
      arrayHeap.set(0, lastValue); // zamien korzen
      heapifyDownGlobal(); // spusc kopiec
    }

    return minimum;
  }

  @Override
  public void clear() {
    arrayHeap.clear();
    heapTrees.clear();
    size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ArrayTreeBinaryHeap:\n");
    sb.append("Array part: ").append(arrayHeap).append("\n");
    for (int i = 0; i < heapTrees.size(); i++) {
      sb.append("Tree #").append(i).append(": ").append(heapTrees.get(i)).append("\n");
    }
    return sb.toString();
  }
}
