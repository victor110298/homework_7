package threads;

import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueue {
    private static java.util.concurrent.BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        new Thread(() -> System.out.println(blockingQueue.add("something"))).start();
    }
}
