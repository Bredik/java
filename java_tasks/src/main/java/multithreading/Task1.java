package multithreading;

import java.util.Arrays;

/*
Необходимо написать два метода, которые делают следующее:
(первый метод работает в один поток, второй в два поток)
- Создают одномерный длинный массив, заполняют этот массив единицами.
- Засекают время выполнения
- Проходят по всему массиву и для каждой ячейки считают новое значение (любая формула)
- Проверяется время окончания метода
- В консоль выводится время работы
 */

public class Task1 {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) {
        Task1 app = new Task1();
        app.first();
        app.second();
    }

    void first() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        // Замеряем время
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) *  Math.cos (0.4f + i / 2 ));
        }

        System.out.println("Время одного потока = " + (System.currentTimeMillis() - a));

    }

    void second() {
        float[] arr = new float [SIZE];
        Arrays.fill(arr, 1);
        // Замеряем время
        long a = System.currentTimeMillis();
        /*
        System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
        откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
         */
        final float[] arrF = new float [HALF];
        final float[] arrS = new float [HALF];
        System.arraycopy(arr, 0, arrF, 0, HALF);
        System.arraycopy(arr, HALF, arrS, 0, HALF);

        Thread threadF = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrF.length; i++) {
                    arrF[i] = (float) (arrF[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) *  Math.cos (0.4f + i / 2 ));
                }
            }
        });

        Thread threadS = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrS.length; i++) {
                    arrS[i] = (float) (arrS[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) *  Math.cos (0.4f + i / 2 ));
                }
            }
        });

        threadF.start();
        threadS.start();

        try {
            threadF.join();
            threadS.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arrF, 0, arr, 0, SIZE/2);
        System.arraycopy(arrS, 0, arr, SIZE/2, SIZE/2);

        System.out.println("Время в два потока = " + (System.currentTimeMillis() - a));

    }
}
