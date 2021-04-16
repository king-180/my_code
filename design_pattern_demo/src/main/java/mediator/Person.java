package mediator;

/**
 * @author wangxing
 * @date 2021/2/28 11:20
 */
public abstract class Person {

    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
