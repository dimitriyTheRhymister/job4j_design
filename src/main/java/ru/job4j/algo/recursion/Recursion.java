package ru.job4j.algo.recursion;

public class Recursion {

    public int sumLoop(int summary, int add) {
        for (int i = add; i > 0; i--) {
            summary += i;
        }
        return summary;
    }

    public int sumRecursion(int summary, int add) {
        if (add > 0) {
            summary += add;
            add--;
            summary = sumRecursion(summary, add);
        }
        return summary;
    }

    public long factorialLoop(int num) {
        long result = 1L;
        if (num > 0) {
            for (int i = 1; i <= num; i++) {
                result = result * i;
            }
        }
        return result;
    }

    public long factorialRecursion(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return f * factorialRecursion(f - 1);
        }
    }

    public long factorialRecursion2args(int num) {
        return factorialRecursion2args(1L, num);
    }

    public long factorialRecursion2args(long result, int num) {
        if (num > 0) {
            result *= num;
            num--;
            result = factorialRecursion2args(result, num);
        }
        return result;
    }

    public long fibonacciLoop(int n) {
        long result = 0L;
        if (n == 1) {
            result = 1L;
        } else if (n > 1) {
            long f1 = 1L;
            long f2 = 1L;
            for (int i = 0; i < (n - 2); i++) {
                result = f2 + f1;
                f1 = f2;
                f2 = result;
            }
        }
        return result;
    }

    public long fibonacciRecursion(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
        }
    }

    public long fibonacciRecursionWithArr(int n) {
        long[] memo = new long[n + 1];
        return fibonacciRecursionWithArr(n, memo);
    }

    private long fibonacciRecursionWithArr(int n, long[] memo) {
        if (n <= 1) {
            return n;
        } else if (memo[n] != 0) {
            return memo[n];
        } else {
            memo[n] = fibonacciRecursionWithArr(n - 1, memo) + fibonacciRecursionWithArr(n - 2, memo);
            return memo[n];
        }
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();

        System.out.println(recursion.sumLoop(10, 5));
        System.out.println(recursion.sumRecursion(10, 5));

        System.out.println(recursion.factorialLoop(5));
        System.out.println(recursion.factorialRecursion(5));
        System.out.println(recursion.factorialRecursion2args(1L, 5));
        System.out.println(recursion.factorialRecursion2args(5));

        System.out.println(recursion.fibonacciRecursion(6));
        System.out.println(recursion.fibonacciRecursionWithArr(6));
    }
}