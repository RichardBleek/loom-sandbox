import java.util.stream.*;

public class LoomSandbox {

    public static void main(String[] args) throws InterruptedException {
        // virtual thread
        var start = System.currentTimeMillis();
        var threads =
                IntStream.range(0, 10_000)
                         .mapToObj(index ->
                                           Thread.ofVirtual()
                                                 .name("virtual-", index)
                                                 .start(() -> {
                                                         isPrime(index);
                                                 }))
                         .toList();

        for (var thread : threads) {
            thread.join();
        }
        var end = System.currentTimeMillis();
        System.out.println("Time taken in millis = " + (end - start));
    }

    ;

    //quick snippet to give CPU some actual work
    static boolean isPrime(int number) {
        int i = 2;
        boolean notPrime = false;
        while (i <= number / 2) {
            if (number % i == 0) {
                notPrime = true;
                break;
            }
            ++i;
        }

        if (notPrime) {
            return false;
        } else {
            System.out.println(number + " is a prime number.");
            return true;
        }
    }
}
