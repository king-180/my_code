package singleton.destroy_singleton.reflection_resolve;

import java.lang.reflect.Constructor;

/**
 * @author wangxing
 * @date 2021/2/28 17:33
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Class clazz = Singleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton instance1 = (Singleton) constructor.newInstance();
        Singleton instance2 = (Singleton) constructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}
