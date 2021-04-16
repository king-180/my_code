/**
 * @author wangxing
 * @date 2020/12/26 13:50
 *
 * 默认情况下 11416
 *
 * 设置栈的大小 -Xss256k  2472
 */
public class StackErrorTest {
    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
