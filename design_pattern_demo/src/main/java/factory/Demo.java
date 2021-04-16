package factory;

/**
 * @author wangxing
 * @date 2021/2/25 11:19
 */
public class Demo {
    public static void main(String[] args) {
//        ItalyDessertFactory factory = new ItalyDessertFactory();
        AmericaDessertFactory factory = new AmericaDessertFactory();
        Coffee coffee = factory.createCoffee();
        Dessert dessert = factory.createDessert();
        coffee.show();
        dessert.show();
    }
}
