package threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScalableMaps {
    private static Map<Integer, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Thread(() -> {
            map.put(1, "Fist element");
            map.put(2, "Second element");
            map.put(3, "Third element");
            map.replace(1, "First element", "New element");
        }).start();
        new Thread(() ->
        {
            map.remove(2);
            System.out.println(map.toString());
        }).start();
    }
}
