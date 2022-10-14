package com.rbleek;

import java.util.stream.*;

public class SimplePrimePrinter extends PrimePrinter {

    void checkTenThousandNumbersForPrime() throws InterruptedException {
        // virtual thread
        var start = System.currentTimeMillis();
        var threads =
                IntStream.range(0, 10_000)
                         .mapToObj(index ->
                                           Thread.ofVirtual()
                                                 .name("virtual-", index)
                                                 .start(() -> printNumberIfPrime(index)))
                         .toList();

        for (var thread : threads) {
            thread.join();
        }
        var end = System.currentTimeMillis();
        System.out.println("Time taken in millis = " + (end - start));
    }
}
