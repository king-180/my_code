package flyweight;

/**
 * @author wangxing
 * @date 2021/2/27 18:51
 */
public class Demo {
    public static void main(String[] args) {
        BoxFactory boxFactory = BoxFactory.getInstance();
        AbstractBox i = boxFactory.getShape("I");
        i.display("黄色");

        AbstractBox l = boxFactory.getShape("L");
        l.display("红色");

        AbstractBox o = boxFactory.getShape("O");
        o.display("绿色");

    }
}
