package collection;

import java.util.Collections;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/5/10 15:03
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        System.out.println(Collections.emptyList());
        System.out.println(Collections.emptyMap());
        System.out.println(Collections.emptySet());

        List<Integer> list = null;
        for (Integer e : list) {
            System.out.println(e);
        }

    }
}
