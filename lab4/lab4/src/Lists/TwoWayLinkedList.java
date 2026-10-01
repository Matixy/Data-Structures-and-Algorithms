package Lists;

import interfaces.IList;

import java.util.Objects;

/**
 * STRUKTURA <br/>
 * head i tail sa straznikami
 * head ma wartosc null i na nexcie ma 1 elem z listy a na prev ma nulla
 * tail ma wartosc null i nexcie ma nulla a na previousie ma ostani elem z listy
 * */
public class TwoWayLinkedList<E> implements IList<E> {
  private Node<E> headSentinel = null;
  private Node<E> tailSentinel = null;

  private int size = 0;

  public TwoWayLinkedList() {
    // ustawienie strażników
    headSentinel = new Node<E>(null);
    tailSentinel = new Node<E>(null);
    headSentinel.setNext(tailSentinel);
    tailSentinel.setPrev(headSentinel);
  }

  public TwoWayLinkedList(E... elements) {
    this();

    for (E element : elements) {
      add(element);
    }
  }

  private Node<E> getNode(int index) {
    // przypadek index poza przedziałem
    if ( index < 0 || index >= size ) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }

    // przypadek ostatniego elementu
    if ( index == size-1) { return tailSentinel.getPrev(); }

    Node<E> currentNode = headSentinel.getNext();

    int position = 0;
    while (currentNode.getNext() != null && position < index) {
      position += 2;
      currentNode = currentNode.getNext();

      // przypadek kiedy next() przeskoczy szukany index (minie się z indexem)
      if (position - 1 == index) {
        return currentNode.getPrev();
      }
    }

    return currentNode;
  }

  private void swapNextReferencesWhileAdding(Node<E> newNode, Node<E> prevNode, int index) {
    if (index > 1) {
      newNode.getPrev().getPrev().setNext(newNode);
    }

    if (index > 0) {
      newNode.getPrev().setNext(prevNode);
    } else {
      headSentinel.setNext(newNode);
    }

    if (prevNode.getNext() != null) {
      newNode.setNext(prevNode.getNext().getPrev());
    } else if (index == size - 2) {
      newNode.setNext(tailSentinel.getPrev());
    } else if (index == size - 1) {
      prevNode.setNext(tailSentinel.getPrev());
    }
  }


  /**
   * Nadpisane Metody z interfejsu IList
   * */

  @Override
  public boolean add(E e) {
    size++; // zwiekszamy rozmiar
    Node<E> newNode = new Node<>(e);


    newNode.setPrev(tailSentinel.getPrev()); // dla nowego wezla ustawiamy poprzednika - ostatni wezel przed ogonem

    // przypadek dla pustej listy
    if (headSentinel.getNext() == tailSentinel) {
      headSentinel.setNext(newNode);
      newNode.setPrev(headSentinel);
    }

    tailSentinel.setPrev(newNode);


    if (size > 2) {
      Node<E> nextRefNodeToNewNode = newNode.getPrev().getPrev();
      nextRefNodeToNewNode.setNext(newNode);
    }

    return true;
  }

  @Override
  public void add(int index, E element) {
    if (index < 0 || index > size) { throw new IndexOutOfBoundsException("Index out of bounds!"); };

    // dodanie na ostatnim indexie
    if (index == size) {
      add(element);
      return;
    }

    Node<E> newNode = new Node<>(element);
    Node<E> currentNode = getNode(index);

    newNode.setPrev(currentNode.getPrev());
    currentNode.setPrev(newNode);

    swapNextReferencesWhileAdding(newNode, currentNode, index);

    size++; // zwiekszamy rozmiar
  }

  @Override
  public void clear() {
    headSentinel.setNext(tailSentinel);
    tailSentinel.setPrev(headSentinel);
    size = 0;
  }

  @Override
  public boolean contains(E element) {
    return indexOf(element) > -1;
  }

  @Override
  public E get(int index) {
    Node<E> currentNode = getNode(index);

    return currentNode.getElement();
  }

  @Override
  public E set(int index, E element) {
    Node<E> currentNode = getNode(index);
    E prevElement = currentNode.getElement();
    currentNode.setElement(element);

    return prevElement;
  }

  @Override
  public int indexOf(E element) {
    Node<E> currentNode = headSentinel.getNext();

    int position = 0;
    while (currentNode.getNext() != null && !Objects.equals(currentNode.getElement(), element)) {
      currentNode = currentNode.getNext();
      position += 2;

      if (Objects.equals(currentNode.getPrev().getElement(), element)) {
        return position - 1;
      }
    }

    if (Objects.equals(tailSentinel.getPrev().getElement(), element)) { return size-1; }

    if (currentNode.getNext() == null) { return -1; }

    return position;
  }

  @Override
  public boolean isEmpty() {
    return headSentinel.getNext() == tailSentinel;
  }

  @Override
  public E remove(int index) {
    Node<E> removedNode = getNode(index);
    Node<E> prevNode = removedNode.getPrev();

    // usuwanie ostatniego elementu
    if (index == size - 1) {
      tailSentinel.setPrev(tailSentinel.getPrev().getPrev());
    } else if (index == 0) { // usuwanie pierwszego elementu
      if (size < 3) {
        headSentinel.setNext(tailSentinel.getPrev());
      } else {
        headSentinel.setNext(headSentinel.getNext().getNext().getPrev());
      }
    } else {
      // jezeli wezel mial do siebie referencje z wczesniejszego wezla
      if ( removedNode.getPrev().getPrev() != headSentinel){
        removedNode.getPrev().getPrev().setNext(prevNode.getNext());
      }

      // zamiana wezlow
      Node<E> nextNode = prevNode.getNext();
      prevNode.setNext(removedNode.getNext());
      nextNode.setPrev(prevNode);
    }

    size--;
    E removedElement = removedNode.getElement();
    return removedElement;
  }

  @Override
  public boolean remove(E element) {
    remove(indexOf(element));
    return true;
  }

  @Override
  public int size() {
    return size;
  }

  /**
   * Klasa pomocnicza (Węzęł)
   * */
  class Node<E> {
    private E element;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element) {
      this.element = element;
      this.next = null;
      this.prev = null;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(Node<E> next) {
      this.next = next;
    }

    public Node<E> getPrev() {
      return prev;
    }

    public void setPrev(Node<E> prev) {
      this.prev = prev;
    }

    public E getElement() {
      return element;
    }

    public void setElement(E element) {
      this.element = element;
    }

    @Override
    public String toString() {
      return "Node{" + "element=" + element + "}\n";
    }
  };

}
