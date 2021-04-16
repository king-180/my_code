package visitor;

/**
 * @author wangxing
 * @date 2021/2/28 12:27
 */
public class SomeOne implements Person {
    @Override
    public void feed(Cat cat) {
        System.out.println("陌生人喂猫");
    }

    @Override
    public void feed(Dog dog) {
        System.out.println("陌生人喂狗");
    }
}
