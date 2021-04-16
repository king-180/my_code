import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangxing
 * @date 2021/4/13 15:38
 * 常用集合类线程不安全现象以及解决方法
 * TODO 异常现象：
 * java.util.ConcurrentModificationException
 * TODO 解决方法：
 * 1.new Vector<>()
 * 2.Collections.synchronizedList(new ArrayList<>())
 * 3.new CopyOnWriteArrayList<>() TODO 写时复制，读写分离思想
 */
public class CollectionThreadNotSafeDemo {

    public static void main(String[] args) {
        arrayListNotSafeTest();
    }

    private static void arrayListNotSafeTest() {
        List<Integer> list = new CopyOnWriteArrayList<>();/*Collections.synchronizedList(new ArrayList<>())*//*new Vector<>()*//*new ArrayList<>()*/
        for (int i = 0; i < 100; i++) {
            final int tmp = i;
            new Thread(() -> {
                list.add(tmp);
                System.out.println(list);
            }, "t" + (i + 1)).start();
        }
    }

    private static void hashSetNotSafeTest() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            final int tmp = i;
            new Thread(() -> {
                set.add(tmp);
                System.out.println(set);
            }, "t" + (i + 1)).start();
        }
    }

    private static void hashMapNotSafeTest() {
        Map<Integer, String> map = new HashMap<>(16);
        for (int i = 0; i < 100; i++) {
            final int tmp = i;
            new Thread(() -> {
                map.put(tmp, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, "t" + (i + 1)).start();
        }
    }

}
