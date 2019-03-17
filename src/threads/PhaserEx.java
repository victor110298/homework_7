package threads;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class PhaserEx {
    private static final Phaser PHASER = new Phaser(1);
    static ArrayList<Passenger> passengers = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 5; i++) {
            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, i + 1));
            }
            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, 5));
            }
        }
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Bus is going from park");
                    PHASER.arrive();
                    break;
                case 6:
                    System.out.println("Bus came to park");
                    PHASER.arriveAndDeregister();
                    break;
                default:
                    defaultSentence();
            }
        }
    }

    public static void defaultSentence() {
        int currentBusStop = PHASER.getPhase();
        System.out.println("Bus stop № " + currentBusStop);
        for (Passenger p : passengers) {
            if (p.departure == currentBusStop) {
                PHASER.register();
                p.start();
            }
            PHASER.arriveAndAwaitAdvance();
        }
    }

    public static class Passenger extends Thread {
        private int departure;
        private int destination;

        public Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.println(this + " wait on bus stop № " + this.departure);
        }

        @Override
        public void run() {
            try {
                System.out.println(this + " sit in bus");
                while (PHASER.getPhase() < destination)
                    PHASER.arriveAndAwaitAdvance();
                Thread.sleep(1);
                System.out.println(this + " leave bus");
                PHASER.arriveAndDeregister();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return "Passenger{" + departure + " -> " + destination + '}';
        }
    }
}
