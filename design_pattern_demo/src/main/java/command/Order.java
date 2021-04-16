package command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxing
 * @date 2021/2/27 20:08
 */
public class Order {

    private int diningTable;
    private Map<String, Integer> fooDir = new HashMap<>(16);

    public int getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(int diningTable) {
        this.diningTable = diningTable;
    }

    public Map<String, Integer> getFooDir() {
        return fooDir;
    }

    public void setFood(String name, int num) {
        fooDir.put(name, num);
    }
}
