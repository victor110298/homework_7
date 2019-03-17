package threads;

public class CreateSingleton {
    public static CreateSingleton instance;

    private CreateSingleton() {
    }

    public static CreateSingleton getInstance() {
        if (instance == null)
            synchronized (CreateSingleton.instance) {
                if (instance == null) {
                    instance = new CreateSingleton();
                }
            }
        return instance;
    }
}
