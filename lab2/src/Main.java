import Iterators.ByteIterator;
import Iterators.DivisorIterator;

import java.util.Arrays;
import java.util.List;

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

        List<Integer> numbers = Arrays.asList(1, 2, 4, 8, 128, 256, 512, 1024, 20048);

        ByteIterator<Integer> byteIterator = new ByteIterator<>(numbers.iterator());
        while (byteIterator.hasNext()) {
            System.out.println(byteIterator.next());
        }
    }
}