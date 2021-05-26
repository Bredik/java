package multithreading.task3_calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    public static ArrayList<Integer> read() throws IOException {
        BufferedReader reader = null;
        ArrayList<Integer> arr = new ArrayList<>();

        try {
            reader = new BufferedReader (new FileReader("src/main/resources/multiFactorial.txt"));
            while (true) {
                String str = reader.readLine();
                if (str == null) break;
                arr.add(Integer.parseInt(str));
            }
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert reader != null;
            reader.close();
        }
        return arr;
    }
}
