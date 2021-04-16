package singleton.demo4;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton instance;

    public synchronized static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
