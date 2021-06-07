package string;

/**
 * @author wangxing
 * @date 2021/1/2 19:59
 */
public class StringPool58Demo {
    public static void main(String[] args) {

        String str1 = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern()); // true

        System.out.println();

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern()); // false

        String s1 = new StringBuilder("1.8.0_131").toString();
        System.out.println(s1.intern() == s1); // false

        String s2 = "java";
        System.out.println(s2 == s2.intern()); // true

        String s3 = new String("java");
        System.out.println(s3 == s3.intern()); // false

        String s4 = "alibaba";
        String s5 = new String("alibaba").intern();
        System.out.println(s4 == s5); // true
    }
}
