package threads;

import java.lang.reflect.Constructor;

public class BreakingSingleton {
    public static void main(String[] args) {

        CreateSingleton instance1 = CreateSingleton.instance;
        CreateSingleton instance2 = null;
        try {

            Constructor[] constructors =
                    CreateSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instance2 = (CreateSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("instance1.hashCode():- "
                + instance1.hashCode());
        System.out.println("instance2.hashCode():- "
                + instance2.hashCode());
    }
}
