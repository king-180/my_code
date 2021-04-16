package decorator;

/**
 * @author wangxing
 * @date 2021/2/25 18:50
 */
public class Egg extends Garnish {

    public Egg(FastFood fastFood) {
        super(1, "鸡蛋", fastFood);
    }

    @Override
    public float cost() {
        return getPrice() + getFastFood().cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
