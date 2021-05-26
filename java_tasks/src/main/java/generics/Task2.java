package generics;

import java.util.ArrayList;
import java.util.Arrays;

/*
Написать метод, который преобразует массив в ArrayList.
 */

public class Task2 {
    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;

        createArray(arr);

        System.out.println(arr.getClass() + " " + Arrays.toString(arr));
        System.out.println(createArray(arr).getClass() + " " + createArray(arr).toString());

        String[] arrStr = new String[5];
        arrStr[0] = "1 строка";
        arrStr[1] = "2 строка";
        arrStr[2] = "3 строка";
        arrStr[3] = "4 строка";
        arrStr[4] = "5 строка";

        createArray(arrStr);
        System.out.println(arrStr.getClass() + " " + Arrays.toString(arrStr));
        System.out.println(createArray(arrStr).getClass() + " " + createArray(arrStr).toString());


    }

    public static <T> ArrayList<T> createArray(T[] arr) {
        ArrayList<T> newArr = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            newArr.add(arr[i]);
        }
        return newArr;
    }
}
