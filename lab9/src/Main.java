import executors.PrintTreeExecutor;
import interfaces.Executor;
import trees.TrieDictionary;
import utils.AsciiCharacters;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    /**
     * Testy:
     * <br/> Pusta tablica
     * <br/> Jeden element
     * <br/> Parzysta liczba elementow
     * <br/> Nieparzysta liczba elementow
     */
    public static void doTestEmpty(ArrayList<Character> alphabet) {
        System.out.println("Test empty");
        TrieDictionary<Integer> trie = new TrieDictionary<>(alphabet, Comparator.naturalOrder());

        Executor<String, ?> triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());
    }

    public static void doTestOneElem(ArrayList<Character> alphabet) {
        System.out.println("Test one elem");
        TrieDictionary<Integer> trie = new TrieDictionary<>(alphabet, Comparator.naturalOrder());

        trie.insert("", 7);

        Executor<String, ?> triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());

        System.out.println("Stara wartosc dla '': " + trie.insert("", 8));

        triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());

        System.out.println("Szuaknie '': " + trie.search(""));
        System.out.println();

        triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());

        System.out.println("Usuwanie '': " + trie.remove(""));
        System.out.println();

        triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());
    }

    public static void doTestEven(ArrayList<Character> alphabet) {
        System.out.println("Test even");
        TrieDictionary<Integer> trie = new TrieDictionary<>(alphabet, Comparator.naturalOrder());

        trie.insert("dance", 7);
        trie.insert("dad", 8);
        trie.insert("box", 9);
        trie.insert("bank", 10);
        trie.insert("bad", 11);
        trie.insert("baby", 12);

        Executor<String, ?> triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());

        System.out.println("Szuaknie baby: " + trie.search("baby"));
        System.out.println("Szuaknie dance: " + trie.search("dance"));
        System.out.println("Szuaknie '': " + trie.search(""));
        System.out.println("Szuaknie apple: " + trie.search("apple"));

        System.out.println("Usuwanie bad: " + trie.search("bad"));
        trie.remove("bad");

        triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());
    }

    public static void doTestOdd(ArrayList<Character> alphabet) {
        System.out.println("Test odd");
        TrieDictionary<Integer> trie = new TrieDictionary<>(alphabet, Comparator.naturalOrder());

        trie.insert("baby", 7);
        trie.insert("bad", 8);
        trie.insert("bank", 13);
        trie.insert("box", 10);
        trie.insert("apple", 20);
        trie.insert("applex", 12);

        System.out.println("Klucze o najwiekszej wartosci: " + trie.highestValueKeys(Comparator.naturalOrder()));

        Executor<String, ?> triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());

        System.out.println("usuwanie apple: " + trie.remove("apple"));
        System.out.println("usuwanie apple: " + trie.remove("apple"));
        System.out.println("usuwanie bay: " + trie.remove("bay"));
        trie.remove("bad");
        trie.remove("baby");

        triePrinter = new PrintTreeExecutor();
        trie.printTree(triePrinter);
        System.out.println(triePrinter.getResult());

    }

    public static void main(String[] args) {
        ArrayList<Character> alphabet = AsciiCharacters.getAsciiCharacters(32, 126); // stworzenie alfabetu

        doTestEmpty(alphabet);
        doTestOneElem(alphabet);
        doTestEven(alphabet);
        doTestOdd(alphabet);
    }
}