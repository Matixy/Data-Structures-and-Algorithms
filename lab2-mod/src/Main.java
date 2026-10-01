import Iterators.ByteIterator;
import Iterators.DivisorIterator;
import Iterators.ExtractingIterator;

import java.util.*;

// nie trzeba kopiowac
// 0 elemnt
// 1 elemnt
// null
// parysztosc nie parzystosc
//SPRAWDZIC TO

public class Main {
    public static void main(String[] args) {
        final int N = 100;

        // zadanie nr 1
        System.out.println("Zadanie nr.1");

        DivisorIterator divisorIterator = new DivisorIterator(N);
        for ( int divisor : divisorIterator) {
            System.out.println(divisor);
        }


        // -----------------------------------------
        System.out.println("--------------------------------------------");

        //zadanie nr 2
        System.out.println("Zadanie nr.2");

        List<Integer> numbers = Arrays.asList(1, 2, 4, 8, 128, 256, 512, 1024, 20048, 37283);

        ByteIterator<Integer> byteIterator = new ByteIterator<>(numbers.iterator());
        while (byteIterator.hasNext()) {
            System.out.println(byteIterator.next());
        }

        System.out.println("--------------------------------------------");

        //zadanie nr 2
        System.out.println("Modyfikacja");

        Integer[] numbers2 = {null, null};

        ExtractingIterator<Integer> extractingIterator = new ExtractingIterator<>(numbers2);
        while (extractingIterator.hasNext()) {
            Iterator iterator = extractingIterator.next();
            System.out.println("Kolejny iterator");

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}