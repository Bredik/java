package multithreading.task3_calculator;

import java.math.BigInteger;

class CalculateFactorial implements Runnable {
    private final int number;

    public CalculateFactorial(int number) {
        this.number = number;
    }

    public static void getFactorial(int number) {
        System.out.println("Поток " + Thread.currentThread().getName() + " заработал");
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= number; i++)
            result = result.multiply(BigInteger.valueOf(i));
        System.out.println("Поток " + Thread.currentThread().getName() +
                " вычислил факториал от " + number + " равен " + result);
    }

    @Override
    public void run() {
        getFactorial(number);
    }
}