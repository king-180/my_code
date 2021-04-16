package factory;

/**
 * @author wangxing
 * @date 2021/2/25 11:12
 */
public class AmericaDessertFactory implements DessertFactory{
    @Override
    public Coffee createCoffee() {
        return new AmericaCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new Mochamusi();
    }
}
