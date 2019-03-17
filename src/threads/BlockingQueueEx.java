package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueEx {
    private static BlockingQueue<String> bqe = new PriorityBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                System.out.println(bqe.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        new Thread(() -> System.out.println(bqe.add("something"))).start();
    }
}
