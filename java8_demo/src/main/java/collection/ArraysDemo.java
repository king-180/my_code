package collection;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/6 16:03
 */
public class ArraysDemo {
    public static void main(String[] args) {
        List<String> str1 = Arrays.asList("a", "b", "c", "d");
        List<String> str2 = Arrays.asList("a", "b", "e", "f");
        System.out.println(str1.contains(str2));
        System.out.println(str1.containsAll(str2));
        for (String s : str2) {
            System.out.println(str1.contains(s));
        }
        System.out.println(str1.retainAll(str2));
    }
}
