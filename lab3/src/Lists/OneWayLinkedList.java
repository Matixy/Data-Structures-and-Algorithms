package Lists;

import Exceptions.ListIsEmptyWhileRemovingElementException;
import interfaces.IList;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E> {
  private Node<E> head;
  private int size;

  public OneWayLinkedList(E... elements) {
    for (E element : elements) {
      add(element);
    }
  }

  /**
   * Metoda zwracająca węzęł znajdujący się na określonym indeksie listy (potrzebna do meotdy add())
   * */
  Node getNode(int index) {
    /**
     * obsługa wyjątku w przypadku złego indexu
     * */
    if (index < 0) { throw new IndexOutOfBoundsException("Index cannot be negative!"); };

    Node<E> currentNode = head;
    while (index > 0 && currentNode != null) {
      index--;
      currentNode = currentNode.getNext();
    }
    if (currentNode == null) { throw new IndexOutOfBoundsException("Index are out of bounds!"); }

    return currentNode;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean remove(E element) {
    if (head == null) { return false; }

    size--; // zmniejszamy rozmiar

    if (head.getElement() == element) {
      head = head.getNext();
      return true;
    }

    Node currentNode = head;
    while (currentNode.getNext() != null && !(currentNode.getNext().getElement() == element)) {
      currentNode = currentNode.getNext();
    }

    if (currentNode.getNext() == null) { return false; }

    currentNode.setNext(currentNode.getNext().getNext());

    return true;
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

    size--; // zmniejszamy rozmiar

    if (index == 0) {
      E removedElement = head.getElement();
      head = head.getNext();
      return removedElement;
    }

    Node<E> nodeBeghindRemovedNode = getNode(index - 1);

    E removedNodeValue = nodeBeghindRemovedNode.getNext().getElement();
    nodeBeghindRemovedNode.setNext(nodeBeghindRemovedNode.getNext().getNext());

    return removedNodeValue;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int indexOf(E element) {
    int position = 0;
    Node currentNode = head;
    while (currentNode != null) {
      if (currentNode.getElement() == element) {
        return position;
      }

      position++;
      currentNode = currentNode.getNext();
    }

    return -1;
  }

  @Override
  public E set(int index, E element) {
    Node<E> currentNode = getNode(index);
    E previousElement = currentNode.getElement();
    currentNode.setElement(element);

    return previousElement;
  }

  @Override
  public E get(int index) {
    Node<E> currentNode = getNode(index);
    return currentNode.getElement();
  }

  @Override
  public boolean contains(E element) {
    return indexOf(element) > -1;
  }

  @Override
  public void clear() {
    head = null;
    size = 0;
  }

  @Override
  public void add(int index, E element) {
    /**
     * obsługa wyjątku w przypadku złego indexu
     * */
    if (index < 0 || index > size) { throw new IndexOutOfBoundsException("Index out of bounds!"); };

    Node<E> newNode = new Node<>(element);
    size++; // zwiększamy rozmiar

    if (index == 0) {
      newNode.setNext(head);
      head = newNode;
    } else {
      Node<E> nodeBehindNewNode = getNode(index - 1);
      newNode.setNext(nodeBehindNewNode.getNext());
      nodeBehindNewNode.setNext(newNode);
    }
  }

  @Override
  public boolean add(E e) {
    Node<E> newNode = new Node<>(e);
    size++; // zwiększamy rozmiar

    if (head == null) {
      head = newNode;
      return true;
    }

    Node<E> tail = head;
    while (tail.getNext() != null) {
      tail = tail.getNext();
    }
    tail.setNext(newNode);
    return true;
  }

  @Override
  public String toString() {
    String str = "OneWayLinkedList[";
    if (head == null) {
      return str + "]";
    } else {
      str += head.getElement();
      for (int i = 1; i < size(); i++) {
        str += ", " + get(i);
      }
    }

    return str + "]";
  }
}
