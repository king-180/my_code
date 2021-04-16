package singleton.demo7;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton {

    private static final Object lock = new Object();

    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
