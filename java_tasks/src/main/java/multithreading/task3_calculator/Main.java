package multithreading.task3_calculator;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> data;
        data = ReadFile.read();

        for (Integer n : data) {
            System.out.println("start");
            Thread t2 = new Thread(new CalculateFactorial(n));
            t2.start();
            System.out.println("end");
        }
    }
}
