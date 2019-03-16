package threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockEx {
    public static Lock lock =new ReentrantLock();

    public static void main(String[] args) {
        new MyThread().start();
        new SecondMyThread().start();

    }

}
