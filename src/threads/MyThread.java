package threads;

public class MyThread extends Thread {
    @Override
    public void run() {
        LockEx.lock.lock();
        System.out.println(getName() + " start working...");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " stop working...");
        LockEx.lock.unlock();
    }
}
