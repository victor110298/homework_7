package threads;

import java.util.concurrent.Semaphore;

public class SemaphoreEx {
    private static final int COUNT_CONTROL_PLACES = 5;
    private static final int COUNT_RIDERS = 7;
    private static boolean[] CONTROL_PLACES = null;
    private static Semaphore SEMAPHORE = null;

    public static class Rider implements Runnable {
        private int ruderNum;

        public Rider(int ruderNum) {
            this.ruderNum = ruderNum;
        }

        @Override
        public void run() {
            System.out.println("The rider approached the control zone " + ruderNum);
            try {
                SEMAPHORE.acquire();
                System.out.println("The rider checks for a free controller " + ruderNum);
                int controlNum = -1;
                synchronized (CONTROL_PLACES) {
                    for (int i = 0;
                         i < COUNT_CONTROL_PLACES; i++)
                        if (CONTROL_PLACES[i]) {
                            CONTROL_PLACES[i] = false;
                            controlNum = i;
                            System.out.println("The rider approached the controller" + ruderNum + i);
                            break;
                        }
                }

                Thread.sleep((int)
                        (Math.random() * 10 + 1) * 1000);
                synchronized (CONTROL_PLACES) {
                    CONTROL_PLACES[controlNum] = true;
                }
                SEMAPHORE.release();
                System.out.println("The rider completed the check" + ruderNum);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        CONTROL_PLACES = new boolean[COUNT_CONTROL_PLACES];
        for (int i = 0; i < COUNT_CONTROL_PLACES; i++)
            CONTROL_PLACES[i] = true;
        SEMAPHORE = new Semaphore(CONTROL_PLACES.length,
                true);

        for (int i = 1; i <= COUNT_RIDERS; i++) {
            new Thread(new Rider(i)).start();
            Thread.sleep(400);
        }
    }
}