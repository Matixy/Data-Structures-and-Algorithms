import Lists.TwoWayCycledListWithSentinel;
import Lists.TwoWayLinkedList;
import interfaces.IList;

/**
 * TESTY </br>
 * - PUSTA </br>
 * - JEDNOELEMNT </br>
 * - PARZYSTA </br>
 * - NIEPARZYSTA </br>
 * - NULLE W LISCIE </br>
 * */
public class Main {
  public static void printList(IList list) {
    String str = "";
    for (int i = 0; i < list.size(); i++) {
      str += list.get(i) + ", ";
    }

    if (str.length() > 2) {
      str = str.substring(0, str.length() - 2);
    }

    System.out.println(str);
  }

  public static void homeTaskTest(){
    System.out.println("\n---------------TESTY LISTY---------------\n");
    TwoWayLinkedList<Integer> twoWayLinkedList = new TwoWayLinkedList();

    //pusta
    System.out.print("Pusta");
    printList(twoWayLinkedList);
    System.out.println("Czy pusta: " + twoWayLinkedList.isEmpty());

    //jednoelement
    System.out.println("Jednoelementowa");
    twoWayLinkedList.add(1);
    printList(twoWayLinkedList);
    System.out.println("podamiana 0 indexu na 10");
    twoWayLinkedList.set(0, 10);
    printList(twoWayLinkedList);
    System.out.println("Czy zawiera 10?: " + twoWayLinkedList.contains(10));

    //parzysta
    System.out.println("Parzysta");
    twoWayLinkedList.add(2);
    printList(twoWayLinkedList);
    System.out.println("Czy zawiera 20?: " + twoWayLinkedList.contains(20));


    //nieparzysta
    System.out.println("Nieparzysta");
    twoWayLinkedList.add(1,30);
    printList(twoWayLinkedList);

    //nulle w liscie
    System.out.println("Null w liscie");
    twoWayLinkedList.remove(1);
    twoWayLinkedList.add(1, null);
    twoWayLinkedList.add(1, 50);
    twoWayLinkedList.add(1, 60);
    printList(twoWayLinkedList);

    // usniecie dla funkcji remove(E element)
    System.out.println("Usuniecie nulla");
    twoWayLinkedList.remove(null);
    printList(twoWayLinkedList);
  }

  public static void modificationTests() {
    /**
     * lista pusta jest palindromem
     * jednoelement jest palindromem
     *
     * */

    System.out.println("\n--------------MODYFIKACJA LISTY-----------------\n");

    // parzysta z nullami- palindrom
    TwoWayCycledListWithSentinel<Integer> twoWayCycledListWithSentinel = new TwoWayCycledListWithSentinel<>(2,null,
        null,2);
    System.out.println(twoWayCycledListWithSentinel);
    System.out.println(twoWayCycledListWithSentinel.isPalindrome());

    // parzysta z nullami- nie palindrom
    twoWayCycledListWithSentinel = new TwoWayCycledListWithSentinel<>(2,
        null,2, null);
    System.out.println(twoWayCycledListWithSentinel);
    System.out.println(twoWayCycledListWithSentinel.isPalindrome());

    // pusta- palindrom
    twoWayCycledListWithSentinel = new TwoWayCycledListWithSentinel<>();
    System.out.println(twoWayCycledListWithSentinel);
    System.out.println(twoWayCycledListWithSentinel.isPalindrome());

    // jeden elem- palindrom
    twoWayCycledListWithSentinel = new TwoWayCycledListWithSentinel<>(1);
    System.out.println(twoWayCycledListWithSentinel);
    System.out.println(twoWayCycledListWithSentinel.isPalindrome());

    // nieparzysta- nie palindrom
    twoWayCycledListWithSentinel = new TwoWayCycledListWithSentinel<>(1,null,3,
        null,2);
    System.out.println(twoWayCycledListWithSentinel);
    System.out.println(twoWayCycledListWithSentinel.isPalindrome());

    // nieparzysta- palindrom
    twoWayCycledListWithSentinel = new TwoWayCycledListWithSentinel<>(1,null, 2, 3, 2, null,1);
    System.out.println(twoWayCycledListWithSentinel);
    System.out.println(twoWayCycledListWithSentinel.isPalindrome());
  }

  public static void main(String[] args) {
    homeTaskTest();

    modificationTests();
  }
}