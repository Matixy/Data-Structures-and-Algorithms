import Exceptions.ListIsNullWhileTryIterateException;
import Lists.OneWayLinkedList;
import Lists.OneWaySquareList;

import java.util.ArrayList;

public class Main {
  /**
   * TESTY </br>
   * - PUSTA </br>
   * - JEDNOELEMNT </br>
   * - PARZYSTA </br>
   * - NIEPARZYSTA </br>
   * - NULLE W LISCIE </br>
   * */
  public static void main(String[] args) {
    // przetestowanie listy
    OneWaySquareList<String> squareList = new OneWaySquareList("Twst", null, null, "94", "ds", "test", "test", "test", "222");
    squareList.set(7, null);
    System.out.println(squareList.indexOf(null));
    System.out.println(squareList.contains("111"));
    System.out.println(squareList.contains(null));
    System.out.println(squareList.size());

    squareList.remove(0);
    squareList.remove(3);


     squareList.add("9");
     squareList.add(0, "txt");
     squareList.add(1, "tst");
     squareList.remove("9");
     squareList.remove(5);
     squareList.remove(2);
     squareList.remove(3);

    System.out.println("\n-------------------------\nIteracja:\n");
    for (int i = 0; i < squareList.size(); i++) {
      System.out.println(squareList.get(i));
    }
  }
}