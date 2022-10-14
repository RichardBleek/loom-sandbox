package com.rbleek;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class ContinuousPrimePrinter extends PrimePrinter {

    int virtualThreadCount = 100;
    int primesFound = 0;
    AtomicInteger counter = new AtomicInteger(1);
    Collection<Thread> threads;
    long start;

    ContinuousPrimePrinter() {
        super();
        registerShutdown();

    }

    private void registerShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            threads.forEach(Thread::interrupt);
            System.out.println("Time taken = " + (System.currentTimeMillis() - start));
            System.out.println("Prime counter = " + primesFound);
        }));
    }

    void start() throws InterruptedException {
        if (threads != null) {
            throw new IllegalStateException("Already started");
        }
        start = System.currentTimeMillis();
        threads =
                IntStream.range(0, virtualThreadCount)
                         .mapToObj(index ->
                                           Thread.ofVirtual()
                                                 .name("virtual-", index)
                                                 .start(() -> new PrimeThread().start()))
                         .toList();

        while(true) {
            Thread.sleep(1000);
        }
    }

    private class PrimeThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int primeCandidate = counter.getAndIncrement();
                if (isPrime(primeCandidate)) {
                    System.out.println(primeCandidate + " is prime");
                    primesFound++;
                }
            }
        }
    }
}
