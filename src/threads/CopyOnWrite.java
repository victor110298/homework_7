package threads;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWrite {
    private static CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new Thread(() -> {
            copy.add("1 element");
            copy.add("2 element");
            copy.add("3 element");
            System.out.println(copy.isEmpty());
        }).start();
        new Thread(() -> {
            copy.remove(1);
            System.out.println(copy.toString());
        }).start();
    }
}

