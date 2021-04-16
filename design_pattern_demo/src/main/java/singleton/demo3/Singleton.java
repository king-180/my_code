package singleton.demo3;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
