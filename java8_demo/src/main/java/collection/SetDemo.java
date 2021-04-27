package collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxing
 * @date 2021/4/27 15:08
 */
public class SetDemo {
    public static void main(String[] args) {

        Set<Integer> result = new HashSet<>();

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));

        System.out.println("set1:          " + set1 + "\n" + "set2:          " + set2);

        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("set1-set2差集：" + result);

        result.clear();
        result.addAll(set2);
        result.removeAll(set1);
        System.out.println("set2-set1差集：" + result);

        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("交集：         " + result);

        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("并集：         " + result);

    }
}
