package singleton.destroy_singleton.serializable_resolve;

import java.io.Serializable;

/**
 * @author wangxing
 * @date 2021/2/28 16:47
 */
public class Singleton implements Serializable {

    private Singleton() {
    }

    private static Singleton instance;

    public synchronized static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public Object readerResolve() {
        return instance;
    }

}
