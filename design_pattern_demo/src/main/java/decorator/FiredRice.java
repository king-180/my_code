package decorator;

/**
 * @author wangxing
 * @date 2021/2/25 18:45
 */
public class FiredRice extends FastFood {

    public FiredRice() {
        super(10, "炒饭");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
