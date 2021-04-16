package decorator;

/**
 * @author wangxing
 * @date 2021/2/25 18:50
 */
public class Bacon extends Garnish {

    public Bacon(FastFood fastFood) {
        super(2, "培根", fastFood);
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
