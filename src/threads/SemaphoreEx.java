package threads;

import java.util.concurrent.Semaphore;

public class SemaphoreEx {
   private static Semaphore semaphore=new Semaphore(2);
    public static void main(String[] args) {
creationThread("First Thread");
creationThread("Second Thread");
creationThread("Third Thread");
creationThread("Fourth Thread");

    }
    public static void creationThread(String name){
        new Thread(() ->{
            try {
                semaphore.acquire();
                System.out.println(semaphore.availablePermits());
                semaphore.release();
                System.out.println(semaphore.availablePermits());
                System.out.println(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
