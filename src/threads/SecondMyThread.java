package threads;

public class SecondMyThread extends Thread {
    @Override
    public void run() {
        System.out.println(getName()+" start...");
        while (true){
            if(LockEx.lock.tryLock()){
                System.out.println(getName()+" is working");
            break;
            }else {
                System.out.println(getName()+" waiting");
            }
        }
    }
}
