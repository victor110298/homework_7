package threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierEx {
    private static CyclicBarrier cyclic = new CyclicBarrier(2,new Thread(()-> System.out.println("Let's go")));
    public static void main(String[] args) {
        new Service().start();
        new Service().start();
    }
    static class Service extends Thread{
        @Override
        public void run(){
            try {
                System.out.println("Waiting");
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

}
