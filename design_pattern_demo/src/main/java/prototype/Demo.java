package prototype;

/**
 * @author wangxing
 * @date 2021/2/28 18:28
 */
public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType realizeType = new RealizeType();
        RealizeType clone = realizeType.clone();
        System.out.println(realizeType == clone);
    }
}
