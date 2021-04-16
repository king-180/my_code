package singleton.demo6;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton {

    private static final Object lock = new Object();

    private Singleton() {
    }

    private static volatile Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
