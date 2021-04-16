package bridge;

/**
 * @author wangxing
 * @date 2021/2/27 13:06
 */
public class Demo {
    public static void main(String[] args) {
        OperatingSystem mac = new Mac(new AviFile());
        mac.play("战狼3.avi");
    }
}
