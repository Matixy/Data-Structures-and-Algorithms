package trees;

import interfaces.Executor;

import java.util.*;

public class BST<T> {
  private final Comparator<T> comparator;
  private Node root;

  public BST(Comparator<T> comparator) {
    this.comparator = comparator;
    root = null;
  }

  /**
   * Prywatne metody pomocnicze
   */
  private Node getMinNode(Node node) {
    if (node.left == null) {
      return node;
    }

    node = getMinNode(node.left);

    return node;
  }

  private Node getMaxNode(Node node) {
    if (node.right == null) {
      return node;
    }

    node = getMaxNode(node.right);

    return node;
  }

  private Node getNodeRec(Node node, T value) {
    if (node == null) {
      return null;
    }

    if (comparator.compare(node.value, value) > 0) {
      node = getNodeRec(node.left, value);
    } else if (comparator.compare(node.value, value) < 0) {
      node = getNodeRec(node.right, value);
    }

    return node;
  }

  private Node getNodeIter(Node node, T value) {
    while (node != null) {
      if (comparator.compare(node.value, value) > 0) {
        node = node.left;
      } else if (comparator.compare(node.value, value) < 0) {
        node = node.right;
      } else {
        return node;
      }
    }

    return null;
  }

  private Node findPredecessorNode(Node stratNode, T value) {
    Node findPredFrom = getNodeIter(stratNode, value);
    Node predNode = null;

    if (findPredFrom.left == null) {
      Node current = root;

      while (current != null && findPredFrom.compareTo(current) != 0) {
        if (findPredFrom.compareTo(current) > 0) {
          predNode = current;
          current = current.right;
        } else if (findPredFrom.compareTo(current) < 0) {
          current = current.left;
        }
      }

    } else {
      predNode = findPredFrom.left;

      while (predNode.right != null) {
        predNode = predNode.right;
      }
    }

    return predNode;
  }

  private Node getParentNode(Node stratNode, Node node) {
    Node current = stratNode;

    while (current != null && current.left != node && current.right != node) {
      if (node.compareTo(current) < 0) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    if (current == null) {
      return null;
    } else {
      return current;
    }
  }

  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    }

    return 1 + Math.max(getHeight(node.left), getHeight(node.right));
  }

  private int countDeltaHeight(Node node) {
    if (node == null) {
      return 0;
    }

    if ((node.left != null && node.right != null) || (node.left == null && node.right == null)) {
      return Math.abs(countDeltaHeight(node.left) - countDeltaHeight(node.right));
    } else {
      return 1 + Math.abs(countDeltaHeight(node.left) - countDeltaHeight(node.right));
    }
  }

  /**
  * Metody zaimplementowane rekurencyjnie
   */

  public int ostImbalancedSubtree(Node root) {
    int mostDeltaHeight = 0;
    BST<T> mostImbalancedSubtree = new BST<>(comparator);
    mostImbalancedSubtree.root = root;

    Node current = root;

    while (current != null) {
      int leftHeight = getHeight(current.left);
      int rightHeight = getHeight(current.right);

      int deltaHeight = Math.abs(leftHeight - rightHeight);

      int deltaHeightLeft = Math.abs(leftHeight - rightHeight);
      int deltaHeightRight = Math.abs(leftHeight - rightHeight);

      if (deltaHeight > mostDeltaHeight) {
        mostDeltaHeight = deltaHeight;
        mostImbalancedSubtree.root = current;
      }

      if (leftHeight > rightHeight) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    return 0;
  }

  public BST<T> mostImbalancedSubtree() {
    BST<T> mostImbalancedSubtree = new BST<>(comparator);
    int mostDeltaHeight = countDeltaHeight(root);
    mostImbalancedSubtree.root = root;

    int mosted = mostImbalanced(root, mostDeltaHeight);



    System.out.println(mosted);

    return mostImbalancedSubtree;
  }

  private int mostImbalanced(Node node, int mostImbalancedHeight) {
    if (node == null) {
      return 0;
    }

    int deltaHeightLeftNum = countDeltaHeight(node.left);
    int deltaHeightRightNum = countDeltaHeight(node.right);

    int deltaHeightLeft = mostImbalanced(node.left, deltaHeightLeftNum);
    int deltaHeightRight = mostImbalanced(node.right, deltaHeightRightNum);

    if (deltaHeightLeftNum > mostImbalancedHeight) {
      return deltaHeightLeft;
    } else if (deltaHeightRightNum > mostImbalancedHeight) {
      return deltaHeightRight;
    }

    return mostImbalancedHeight;
  }


  public T search(T value) {
    if (root == null) {
      return null;
    }

    Node searchedNode = getNodeRec(root, value);

    if (searchedNode == null) {
      return null;
    }

    return searchedNode.value;
  }

  public T getMinimum() {
    if (root == null) {
      throw new NoSuchElementException("The tree is empty");
    }

    Node min = getMinNode(root);

    return min.value;
  }

  public T getMaximum() {
    if (root == null) {
      throw new NoSuchElementException("The tree is empty");
    }

    Node max = getMaxNode(root);

    return max.value;
  }

  private <R> void inOrderWalk(Node node, Executor<T, R> exec) {
    if (node == null) {
      return;
    }

    inOrderWalk(node.left, exec);
    exec.execute(node.value);
    inOrderWalk(node.right, exec);
  }

  public <R> R inOrderWalk(Executor<T, R> executor) {
    inOrderWalk(root, executor);
    return executor.getResult();
  }

  /**
   * Metody zaimplementowane itreacyjnie
   */
  public T findPredecessor(T value) {
    if (search(value) == null) {
      throw new NoSuchElementException("This element does not exist");
    }

    Node predecessorNode = findPredecessorNode(root, value);

    if (predecessorNode == null) {
      return null;
    }

    return predecessorNode.value;
  }

  public void insert(T value) {
    Node newNode = new Node(value);

    if (root == null) {
      root = newNode;
      return;
    }

    Node parent = root;
    Node current = null;

    while (parent != null) {
      current = parent;

      if (newNode.compareTo(parent) < 0) {
        parent = current.left;
      } else {
        parent = current.right;
      }
    }

    if (newNode.compareTo(current) < 0) {
      current.left = newNode;
    } else {
      current.right = newNode;
    }
  }

  public T delete(T value) {
    if (root == null) {
      throw new NoSuchElementException("The tree is empty");
    }

    Node deleteNode = getNodeIter(root, value);

    if (deleteNode == null) {
      throw new NoSuchElementException("The element does not exist");
    }

    Node parent = getParentNode(root, deleteNode);

    // wezel z dwoma dziecmi
    if (deleteNode.left != null && deleteNode.right != null) {
      Node predecessorNode = findPredecessorNode(deleteNode, deleteNode.value); // znajdz poprzednik
      Node predecessorParent = getParentNode(deleteNode, predecessorNode); // znajdz rodzica poprzednika

      deleteNode.value = predecessorNode.value; // zamien wartosc elem do usuniecia z wartoscia poprzednika

      deleteNode = predecessorNode; // wezel do usuniecia to teraz wezel poprzednika
      parent = predecessorParent; // nowy rodzic- lewy wezel usuwanego elementu
    }

    // wezel do usuniecia ma max jedno dziecko
    Node child = (deleteNode.left != null) ? deleteNode.left : deleteNode.right;

    if (parent == null) {
      root = child; // usuniecie korzenia
    } else if (parent.left == deleteNode) {
      parent.left = child;
    } else {
      parent.right = child;
    }

    return value;
  }

  /**
   * Wewnetrzna klasa reprezentujaca wezel drzewa
   */
  private class Node {
    public T value;
    public Node left;
    public Node right;

    private Node(T value) {
      this.value = value;
    }

    private Node(T value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    private int compareTo(Node nodeToCompare) {
      return comparator.compare(this.value, nodeToCompare.value);
    }
  }
}
