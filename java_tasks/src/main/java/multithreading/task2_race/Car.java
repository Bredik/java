package multithreading.task2_race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static final AtomicBoolean WINNER = new AtomicBoolean(false);


    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;
    private CountDownLatch latch;

    public String getName () {
        return name;
    }
    public int getSpeed () {
        return speed;
    }

    public Car (Race race, int speed, CyclicBarrier cb, CountDownLatch latch) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.latch = latch;
    }

    @Override
    public void run () {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep( 500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        latch.countDown();
        if (!WINNER.getAndSet(true)) {
            System.out.println(this.name + " WINS!");
        }
    }
}
