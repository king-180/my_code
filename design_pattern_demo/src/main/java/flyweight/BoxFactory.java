package flyweight;

import java.util.HashMap;

/**
 * @author wangxing
 * @date 2021/2/27 18:46
 */
public class BoxFactory {

    private HashMap<String, AbstractBox> map;

    private BoxFactory() {
        map = new HashMap<>(16);
        map.put("I", new IBox());
        map.put("L", new LBox());
        map.put("O", new OBox());
    }

    private static final BoxFactory boxFactory = new BoxFactory();

    public static BoxFactory getInstance() {
        return boxFactory;
    }

    public AbstractBox getShape(String name) {
        return map.get(name);
    }
}
