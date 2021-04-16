package interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxing
 * @date 2021/2/28 13:32
 */
public class Context {

    private Map<Variable, Integer> map = new HashMap<>(16);

    public void assign(Variable variable, Integer value) {
        map.put(variable, value);
    }

    public int getVal(Variable variable) {
        return map.get(variable);
    }

}
