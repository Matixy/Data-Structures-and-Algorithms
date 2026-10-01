package trees;

import exceptions.ValueOutOfAlfabetException;
import interfaces.Executor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrieDictionary<V> {
  private final ArrayList<Character> alphabet;
  private final Comparator<Character> comparator;
  private Node root;

  public TrieDictionary(ArrayList<Character> alphabet, Comparator<Character> comparator) {
    this.alphabet = alphabet;
    this.comparator = comparator;
    root = new Node('\0');
  }

  /**
   * Prywatne metody pomocnicze
   */
  private void switchSiblingNode(Node node, Node newSiblingNode) {
    Node oldAlternativeNode = node.nextSibling;
    node.nextSibling = newSiblingNode;
    newSiblingNode.nextSibling = oldAlternativeNode;
  }

  private Node searchSiblingNodes(Node node, char searchKey) {
    while (comparator.compare(searchKey, node.key) > 0 && node.nextSibling != null) {
      node = node.nextSibling;
    }

    return node;
  }

  private void addSiblingNodeBefore(Node parent, Node node, Node newSiblingNode) {
    parent.nextNode = newSiblingNode;
    newSiblingNode.nextSibling = node;
  }

  private Node findPreviousSiblingNode(Node node, Node child) {
    while (node.nextSibling != child) {
      node = node.nextSibling;
    }

    return node;
  }

  private RemovedResult removeNode(Node node, String key, int index) {
    if (node == null) {
      return null;
    }

    char c = key.charAt(index);
    node = searchSiblingNodes(node, c);

    // nie ma takiej sciezki- zwroc null
    if (node.key != c) {
      return null;
    }

    // dotarto do koncowego wezla klucza
    if (index == key.length() - 1) {
      RemovedResult res = new RemovedResult(node, node.value);
      node.value = null;
      return res;
    }

    // na poziomie rodzica sprawdzenie czy wezel jest potrzebny
    RemovedResult res = removeNode(node.nextNode, key, index + 1);

    // jezeli w rekunrencji nie bylo sciezki= nie ma wezla zwroc null
    if (res == null) {
      return null;
    }

    Node child = res.removedNode;

    // jezeli dziecko nie ma zadnego potomka i wartosci to jest niepotrzebne w drzewie
    if (child.nextNode == null && child.value == null) {
      // jezeli wezel ma tylko usuwanego potomka to ustaw wskaznik na nastepna sekwencje na null
      if (node.nextNode.nextSibling == null ) {
        node.nextNode = null;
      } else {
        // jezeli dziecko to 1 wezel w sekwencji ustaw jako 1 wezel w sekwencji nastepnik dziecka
        if (node.nextNode == child) {
          node.nextNode = child.nextSibling;
        } else {
          // jezeli istnieje inny wezel w sekwencji i dziecko nie jest 1 trzeba odpowiednio ustawic skazniki
          Node prev = findPreviousSiblingNode(node.nextNode, child);
          prev.nextSibling = child.nextSibling;
        }
      }
    }

    res.removedNode = node;
    return res;
  }

  /**
   * Zaimplementowane operacje
   */
  public V insert(String key, V value) {
    if (key == null || value == null) {
      throw new NullPointerException("key or value is null");
    }

    Node current = root;
    for (char c : key.toCharArray()) {
      // sprawdz czy znak nalezy do alfabetu
      if (!alphabet.contains(c)) {
        throw new ValueOutOfAlfabetException("Char " + c + " at key " + key + " is not in alphabet");
      }

      if (current.nextNode == null) {
        current.nextNode = new Node(c);
      }

      Node parent = current; // zapamietaj rodzica
      current = current.nextNode;

      if (current.key != c) {
        // jezeli znak powinien byc przed aktualnym 1 znakiem dla tej sekwnecji
        if (comparator.compare(c, current.key) < 0) {
          Node newNode = new Node(c);
          addSiblingNodeBefore(parent, current, newNode);

          current = newNode;
        } else {
          // jezeli znak jest lub powinien byc za 1 znakiem dla tej sekwencji
          current = searchSiblingNodes(current, c);

          // jezeli nie ma takiego welza dodaj go
          if (comparator.compare(current.key, c) != 0) {
            switchSiblingNode(current, new Node(c));
            current = current.nextSibling;
          }
        }
      }
    }

    V oldValue = current.value;
    current.value = value;

    return oldValue;
  }

  public V search(String key) {
    if (key == null) {
      throw new NullPointerException("key is null");
    }

    Node current = root;
    for (char c : key.toCharArray()) {
      // jezeli nie ma juz sciezki w drzewie nie ma juz elementu
      if (current.nextNode == null) {
        return null;
      }

      current = current.nextNode; // przejdz w dol

      // jezeli nie jest na 1 poziomie w sekwencji poszukaj go wsrod wezlow na tym poziomie (jezeli nie ma zwroc null)
      if (current.key != c) {
        current = searchSiblingNodes(current, c);

        if (comparator.compare(current.key, c) != 0) {
          return null;
        }
      }
    }

    return current.value;
  }

  public V remove(String key) {
    if (key == null) {
      throw new NullPointerException("key is null");
    }

    V value;

    if (key.isEmpty()) {
      value = root.value;
      root.value = null;

      return value;
    }

    key = "\0" + key; // zeby przeszukac korzen nalezy dodac wartosc korzenia na poczatku klucza
    RemovedResult removedNodeResult = removeNode(root, key, 0);
    value = removedNodeResult == null ? null : removedNodeResult.removedValue;

    return value;
  }

  public List<String> highestValueKeys(Comparator<? super V> comparator) {
    HighestValueKeysResult highestValueKeysResult = findHighestValueKeys(root, comparator, "");

    return highestValueKeysResult.highestValueKeys;
  }

  public HighestValueKeysResult findHighestValueKeys(Node node, Comparator<? super V> comparator, String prefix) {
    if (node == null) {
      return new HighestValueKeysResult();
    }

    String key = prefix + node.key;
    ArrayList<String> keyArray = new ArrayList<String>();
    keyArray.add(key);

    HighestValueKeysResult highestValueKeysResult = new HighestValueKeysResult(keyArray , node.value);

//    if (node.value != null) {
//      if (maxValue == null || highestValueKeysResult.maxValue == null || comparator.compare(maxValue, node.value) < 0) {
//        maxValue = node.value;
//        highestValueKeysResult.maxValue = maxValue;
//        highestValueKeysResult.highestValueKeys.clear();
//      }
//
//      if (comparator.compare(maxValue, node.value) == 0) {
//        highestValueKeysResult.highestValueKeys.add(prefix);
//      }
//    }

    Node current = node;
    while (current != null) {
      HighestValueKeysResult siblingHighestValueKeysResult = findHighestValueKeys(current.nextNode, comparator, prefix + current.key);

      if (siblingHighestValueKeysResult.maxValue != null) {
        if (highestValueKeysResult.maxValue == null || comparator.compare(siblingHighestValueKeysResult.maxValue,
            highestValueKeysResult.maxValue) > 0) {
          highestValueKeysResult.maxValue = siblingHighestValueKeysResult.maxValue;
          highestValueKeysResult.highestValueKeys.clear();
        }

        if (comparator.compare(siblingHighestValueKeysResult.maxValue, highestValueKeysResult.maxValue) == 0) {
          highestValueKeysResult.highestValueKeys.addAll(siblingHighestValueKeysResult.highestValueKeys);
        }
      }

      current = current.nextSibling;
    }

    return highestValueKeysResult;
  }

  /**
   * prywatna klasa wewnetrzna reprezentujaca wezel
   */
  private class Node {
    public V value;
    public char key;
    Node nextSibling;
    Node nextNode;

    public Node(char key) {
      this.key = key;
    }
  }

  /**
   * prywatna klasa wewnetrzna reprezentujaca wynik usuwania wezla
   */
  private class RemovedResult {
    public Node removedNode;
    public V removedValue;

    public RemovedResult(Node removedNode, V removedValue) {
      this.removedNode = removedNode;
      this.removedValue = removedValue;
    }
  }

  /**
   * prywatna klasa wewnetrzna reprezentujaca wynik usuwania wezla
   */
  private class HighestValueKeysResult {
    public List<String> highestValueKeys;
    public V maxValue;

    public HighestValueKeysResult() {
      highestValueKeys = new ArrayList<>();
    }

    public HighestValueKeysResult(List<String> highestValueKeys, V maxValue) {
      this.highestValueKeys = highestValueKeys;
      this.maxValue = maxValue;
    }
  }

  public void printTree(Executor<String, ?> executor) {
    printSubtree(root, "", true, executor);
  }

  private void printSubtree(Node node, String prefix, boolean isTail, Executor<String, ?> executor) {
    while (node != null) {
      String nodeLabel = node.key == '\0' ? "KORZEN" : String.valueOf(node.key);
      String line = prefix + (node == root ? "" : (isTail ? "└── " : "├── ")) + nodeLabel;

      if (node.value != null) {
        line += " => " + node.value;
      }

      executor.execute(line);

      if (node.nextNode != null) {
        String childPrefix = prefix + (node == root ? "" : (isTail ? "    " : "│   "));
        printSubtree(node.nextNode, childPrefix, true, executor);
      }

      node = node.nextSibling;
      isTail = false; // tylko pierwszy alternatywny wezel jest traktowany jako ostatni
    }
  }

}
