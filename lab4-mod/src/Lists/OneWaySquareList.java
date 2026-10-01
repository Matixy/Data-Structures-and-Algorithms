package Lists;

import Exceptions.ListIsEmptyWhileRemovingElementException;
import interfaces.IList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OneWaySquareList<E> implements IList<E> {
  private int k;
  private Node<OneWayLinkedList<E>> head;
  private int size;

  public OneWaySquareList(E... elements) {
    for (E element : elements) {
      add(element);
    }
  }

  /**
   * Metoda zwracająca węzęł znajdujący się na określonym indeksie listy (potrzebna do meotdy add())
   * */
  private Node getNode(int index) {
    /**
     * obsługa wyjątku w przypadku złego indexu
     * */
    if (index < 0 || index >= size) { throw new IndexOutOfBoundsException("Index are out of bounds!"); };

    Node<OneWayLinkedList<E>> currentNode = head;
    int rowIndex = index / k;

    for (int i = 0; i < rowIndex; i++) {
      currentNode = currentNode.getNext();
    }

    return currentNode;
  }


  private void refresh(int startIndex) {
    int newK = (int) Math.floor(Math.sqrt(size));

    if (newK != k) {
      restructure(0, newK);
      k = newK;
    } else if (startIndex != size - 1) {
      restructure(startIndex, k);
    }
  }

  private void restructure(int startIndex, int newK) {
    if (head == null) {
      return;
    }

    OneWayLinkedList<E> tempList = new OneWayLinkedList<>();
    Node<OneWayLinkedList<E>> node = getNode(startIndex);
    Node<OneWayLinkedList<E>> prevNode = null;
    int count = 0;

    if (newK == k) {
      while (node != null) {
        OneWayLinkedList<E> nodeList = node.getElement();

        for (int i = 0; i < nodeList.size(); i++) {
          tempList.add(nodeList.get(i));
        }
        node = node.getNext();
      }


      node = getNode(startIndex);

      while (count < tempList.size()) {
        node.getElement().clear(); // Wyczyść aktualny węzeł
        for (int i = 0; i < k && count < tempList.size(); i++) {
          node.getElement().add(tempList.get(count++));
        }

        if (node.getNext() == null && count < tempList.size()) {
          node.setNext(new Node<>(new OneWayLinkedList<>()));
        }

        prevNode = node;
        node = node.getNext();
      }

      prevNode.setNext(null);

    } else {
      while (node != null) {
        OneWayLinkedList<E> nodeList = node.getElement();

        for (int i = 0; i < nodeList.size(); i++) {
          tempList.add(nodeList.get(i));
        }
        node = node.getNext();
      }

      head = null;
      Node<OneWayLinkedList<E>> newNode = new Node<>(new OneWayLinkedList<>());

      while (count != tempList.size()) {
        newNode.getElement().add(tempList.get(count));
        count++;

        if (count % newK == 0) {
          if (head == null) {
            head = newNode;
          } else {
            if (prevNode == null) {
              head.setNext(newNode);
            } else {
              prevNode.setNext(newNode);
            }

            prevNode = newNode;
          }

          newNode = new Node<>(new OneWayLinkedList<>());
        }
      }

      if (!newNode.getElement().isEmpty()) { prevNode.setNext(newNode); }
    }
  }


  /**
   * Metoda sprawdzająca czy lista jest palindromem
   * biore indeks srodkowy i o jeden wiekszy na nexty porownuje i odpowiedni warunek
   */

  // wersja nie liniowa
  public boolean isPalindrome() {
    if (head == null) { return true; }

    int startIndex = size / 2 - 1;
    int endIndex = size / 2;

    if (size % 2 == 1) {
      endIndex++;
    }

    List<E> tempListStart = new ArrayList<>();
    ArrayList<E> tempListEnd = new ArrayList<>();

    Node<OneWayLinkedList<E>> startNode = head;

    int i = 0;
    while (startNode != null) {
      OneWayLinkedList<E> nodeList = startNode.getElement();
      Node<E> nodeElem = startNode.getElement().getNode(0);

      for (int k = 0; k < nodeList.size(); k++) {
        if (i <= startIndex) {
          tempListStart.add(nodeElem.getElement());
        } else if (i >= endIndex) {
          tempListEnd.add(nodeElem.getElement());
        }
        nodeElem = nodeElem.getNext();
        i++;
      }

      startNode = startNode.getNext();
    }

    Collections.reverse(tempListEnd);

    for (int k = 0; k < tempListStart.size(); k++) {
      if (!Objects.equals(tempListStart.get(k), tempListEnd.get(k))) {
        return false;
      }
    }

    return true;
  }

  /**
   * nadpisanie metod z intefejsu
   * */

  @Override
  public boolean add(E e) {
    size++; // zwiekszamy rozmiar

    if (head == null) {
      head = new Node<>(new OneWayLinkedList<E>(e));
      k = 1;
      return true;
    }

    Node<OneWayLinkedList<E>> tail = head;
    while (tail.getNext() != null) {
      tail = tail.getNext();
    }

    if (tail.getElement().size() == k) {
      tail.setNext(new Node<>(new OneWayLinkedList<E>()));
      tail = tail.getNext();
    }

    tail.getElement().add(e);
    refresh(size-1);
    return true;
  }

  @Override
  public void add(int index, E element) {
    /**
     * obsługa wyjątku w przypadku złego indexu
     * */
    if (index < 0 || index > size) { throw new IndexOutOfBoundsException("Index out of bounds!"); };

    Node<OneWayLinkedList<E>> currentNode = getNode(index);
    currentNode.getElement().add(index % k, element);

    size++;
    refresh(index);
  }

  @Override
  public void clear() {
    head = null;
    size = 0;
    k = 0;
  }

  @Override
  public boolean contains(E element) {
    return indexOf(element) > -1;
  }

  @Override
  public E get(int index) {
    Node<OneWayLinkedList<E>> currentNode = getNode(index);
    return currentNode.getElement().get(index % k);
  }

  @Override
  public E set(int index, E element) {
    Node<OneWayLinkedList<E>> currentNode = getNode(index);
    E previousElement = currentNode.getElement().get(index % k);
    currentNode.getElement().set(index % k, element);

    return previousElement;
  }

  @Override
  public int indexOf(E element) {
    int rowPos = 0;
    int colPos;

    Node<OneWayLinkedList<E>> currentNode = head;

    while (currentNode != null) {
      colPos = currentNode.getElement().indexOf(element);

      if (colPos != -1) {
        return rowPos * k + colPos;
      }

      rowPos++;
      currentNode = currentNode.getNext();
    }

    return -1;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public E remove(int index) {
    /**
     * obsługa wyjątku w przypadku złego indexu lub gdy lista jest pusta
     * */
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index are out of bounds!");
    } else if (head == null) {
      throw new ListIsEmptyWhileRemovingElementException("You trying remove element form empty list!");
    };

    Node<OneWayLinkedList<E>> currentNode = getNode(index);
    E removedElement = currentNode.getElement().remove(index % k);

    size--; // zmniejszamy rozmiar
    refresh(index - 1);

    return removedElement;
  }

  @Override
  public boolean remove(E element) {
    int index = indexOf(element);
    if (index == -1) {
      return false;
    }
    remove(index);
    return true;
  }

  @Override
  public int size() {
    return size;
  }

}
