package prototype;

/**
 * @author wangxing
 * @date 2021/2/28 18:22
 */
public class RealizeType implements Cloneable {
    public RealizeType() {
        System.out.println("具体原型对象创建完成！");
    }

    @Override
    protected RealizeType clone() throws CloneNotSupportedException {
        System.out.println("原型被复制！");
        return (RealizeType) super.clone();
    }
}
