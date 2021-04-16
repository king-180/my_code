package factory;

/**
 * @author wangxing
 * @date 2021/2/25 11:16
 */
public class ItalyDessertFactory implements DessertFactory {

    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new Tilamisu();
    }
}
