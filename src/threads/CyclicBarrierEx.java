package threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierEx {
    private static CyclicBarrier FerryBarrier;
    private static final int FerryBoat_size = 3;

    public static class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(
                        "\nCar is coming");
                Thread.sleep(500);
                System.out.println(
                        "Ferry finished road\n");
            } catch (InterruptedException e) {
            }
        }
    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.println("Coming car number " + carNumber);
                FerryBarrier.await();
                System.out.println("Keep going car number " + carNumber);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        FerryBarrier = new CyclicBarrier(FerryBoat_size,
                new FerryBoat());
        for (int i = 1; i < 5; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }
}