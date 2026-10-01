package utils;

import java.util.ArrayList;

public class AsciiCharacters {
  public static ArrayList<Character> getAsciiCharacters(int start, int end) {
    ArrayList<Character> asciiTable = new ArrayList<>(end - start + 1);

    for (int i = 0; i <= end - start; i++) {
      asciiTable.add((char) (start + i));
    }

    return asciiTable;
  }
}
