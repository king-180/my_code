package flyweight;

/**
 * @author wangxing
 * @date 2021/2/27 18:41
 */
public abstract class AbstractBox {

    public abstract String getShape();

    public void display(String color) {
        System.out.println("方块形状：" + getShape() + ",颜色：" + color);
    }

}
