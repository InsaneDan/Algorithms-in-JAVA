package lesson5;

import java.math.BigInteger;

//5! = 5 * 4 * 3 * 2 * 1 = 120
public class Factorial {

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE * Integer.MAX_VALUE);
        System.out.println(factorial(BigInteger.valueOf(10000)));
//        System.out.println(tailFact(5));
    }

    private static int tailFact(int n) {
        return fact(1, n);

    }

    private static int fact(int acc, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be 0 or positive value!");
        }

        if (n == 0 || n == 1) {
            return acc;
        }

        return fact(acc * n, n - 1);
    }

    private static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("n should be 0 or positive value!");
        }

        if (n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        }

        return n.multiply(n.subtract(BigInteger.ONE));
    }
}
