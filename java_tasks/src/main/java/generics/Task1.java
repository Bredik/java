package generics;

import java.util.ArrayList;

/*
Написать метод, который меняет два элемента массива местами (массив может быть любого
ссылочного типа).
 */

public class Task1 {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);

        System.out.println(arr.toString());
        ChangeI.change(arr, 0, 5);
        System.out.println(arr.toString());

        ArrayList<String> arrStr = new ArrayList<>();
        arrStr.add("1 строка");
        arrStr.add("2 строка");
        arrStr.add("3 строка");
        arrStr.add("4 строка");
        arrStr.add("5 строка");
        arrStr.add("6 строка");
        arrStr.add("7 строка");

        System.out.println(arrStr.toString());
        ChangeI.change(arrStr, 2, 4);
        System.out.println(arrStr.toString());
    }

}

class ChangeI {
    public static <T> void change(ArrayList<T> arr, int first, int second) {
        T tempFirst = arr.get(first);
        T tempSecond = arr.get(second);
        arr.set(first, tempSecond);
        arr.set(second, tempFirst);
    }
}
