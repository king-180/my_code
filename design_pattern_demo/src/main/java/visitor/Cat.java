package visitor;

/**
 * @author wangxing
 * @date 2021/2/28 12:24
 */
public class Cat implements Animal {

    @Override
    public void accept(Person person) {
        person.feed(this);
        System.out.println("喵喵喵~");
    }
}
