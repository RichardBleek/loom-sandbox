package com.rbleek;

public class PrimePrinter {

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
