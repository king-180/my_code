package decorator;

/**
 * @author wangxing
 * @date 2021/2/25 18:45
 */
public class FiredNoodles extends FastFood {

    public FiredNoodles() {
        super(12, "炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
