package visitor;

/**
 * @author wangxing
 * @date 2021/2/28 12:27
 */
public class Demo {
    public static void main(String[] args) {
        Home home = new Home();
        home.addAnimal(new Cat());
        home.addAnimal(new Dog());

        Owner owner = new Owner();
        home.action(owner);

        System.out.println("============");

        SomeOne someOne = new SomeOne();
        home.action(someOne);
    }
}
