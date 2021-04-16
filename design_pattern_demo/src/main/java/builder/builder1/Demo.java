package builder.builder1;

/**
 * @author wangxing
 * @date 2021/2/25 13:49
 */
public class Demo {
    public static void main(String[] args) {
        Director director = new Director(new MobileBuilder());
        Bike bike = director.construct();
        System.out.println(bike);
    }
}
