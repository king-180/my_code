import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author wangxing
 * @date 2021/4/19 23:17
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(16);
        map.put("name", "jack");
        map.put("address", "China");
        map.put("age", "18");
        System.out.println(map);
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        String res = "";
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            res = res + key + " : " + value;
        }
        res += ", ";
        System.out.println(res);
    }
}
