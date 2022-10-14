package com.rbleek;

import java.util.stream.*;

public class SimplePrimePrinter {

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

    void printNumberIfPrime(int index) {
        if(isPrime(index)) {
            System.out.println(index + " is prime");
        }
    }

    //return true if number is prime
    boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
