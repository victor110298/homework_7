package threads;

public class CreateSingleton {
    public CreateSingleton instance = null;
    public CreateSingleton getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new CreateSingleton();
                }
            }
        }
        return instance;
    }
}
