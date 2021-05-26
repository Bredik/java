package multithreading.task2_race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Реализовать гонку машин. Условия:
- Участники сначала готовятся к гонке, время подготовки у всех разное
- Все участники должны стартовать одновременно, несмотря на разное время подготовки.
- В тоннель не может одновременно заехать больше половины участников
- Когда все завершат гонку, нужно выдать объявление об окончании.
- Нужно объявить победителя (он должен быть только один, и это участник
первым закончивший последний этап)
 */

public class Main {
    public static final int CARS_COUNT = 6;

    public static void main (String[] args) throws InterruptedException {
        System.out.println("Подготовка к гонке." );
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT, () -> System.out.println("Гонка началась!"));
        CountDownLatch latch = new CountDownLatch(CARS_COUNT);

        Race race = new Race(new Road(100), new Tunnel(), new Road(10));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0 ; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10 ), cb, latch);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for (int i =0; i < cars.length; i++) {
            executorService.execute(cars[i]);
        }

        latch.await();
        executorService.shutdown();
        System.out.println("Гонка закончилась!!!" );
    }
}
