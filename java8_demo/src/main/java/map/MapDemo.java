package map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxing
 * @date 2021/6/11 10:48
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(10);
        map.put("hello", "bat");
        map.put("hi", "batj");
        map.put("a", "A");
        map.put("b", "B");
        System.out.println(map);
        map.computeIfPresent("hello", (k, v) -> "world");
        map.computeIfPresent("hi", (k, v) -> {
            System.out.println(k);
            System.out.println(v);
            return "TMD";
        });
        map.computeIfPresent("c", (k, v) -> "C");
        map.putIfAbsent("d", "D");
        System.out.println(map);
    }
}
