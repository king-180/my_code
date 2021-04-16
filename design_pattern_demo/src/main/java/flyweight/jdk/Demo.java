package flyweight.jdk;

/**
 * @author wangxing
 * @date 2021/2/27 19:04
 */
public class Demo {
    public static void main(String[] args) {
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2);

        System.out.println("========");

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);
    }
}
