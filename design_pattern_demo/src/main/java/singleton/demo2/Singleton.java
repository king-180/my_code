package singleton.demo2;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton instance;

    static {
        instance = new Singleton();
    }

    public static Singleton getInstance() {
        return instance;
    }

}
