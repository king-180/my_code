package singleton.demo1;

/**
 * @author wangxing
 * @date 2021/2/28 16:49
 */
public class Demo {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
