/**
 * @author wangxing
 * @date 2020/12/29 22:04
 */
public class InternTest {
    public static void main(String[] args) {
        String s1 = new String("1");
        String intern = s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2); // false

        String s3 = new String("1") + new String("1");
        String intern1 = s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); // false
    }
}
