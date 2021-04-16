import java.util.TreeSet;

/**
 * @author wangxing
 * @date 2020/12/29 20:32
 */
public class StringTableTest {
    public static void main(String[] args) {

        String s2 = "javaEEhadoop";
        String s3 = "javaEE";
        String s4 = "hadoop";
        String s5 = "javaEE" + "hadoop";
        String s6 = "javaEE" + s4;
        String s7 = s3 + "hadoop";
        String s8 = s3 + s4;
        String s9 = s8.intern();

        System.out.println(s2 == s5); // true
        System.out.println(s2 == s9); // true
        System.out.println(s2 == s6);
        System.out.println(s2 == s7);
        System.out.println(s2 == s8);
        System.out.println(s6 == s7);
        System.out.println(s6 == s8);
        System.out.println(s7 == s8);

        final String s10 = "a";
        final String s11 = "b";
        String s12 = "ab";
        String s13 = s10 + s11;
        System.out.println(s12 == s13); // true
    }
}
