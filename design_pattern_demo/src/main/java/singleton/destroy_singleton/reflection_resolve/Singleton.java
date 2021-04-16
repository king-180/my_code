package singleton.destroy_singleton.reflection_resolve;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton {

    private static boolean flag = false;

    private Singleton() {
        if (flag) {
            throw new RuntimeException();
        }
    }

    private static Singleton instance;

    public synchronized static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
            flag = true;
        }
        return instance;
    }

}
