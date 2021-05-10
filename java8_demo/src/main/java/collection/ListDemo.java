package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/28 10:50
 */
public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        System.out.println(list.toString());
        System.out.println(list.toString().substring(1, list.toString().length()-1));
    }
}
