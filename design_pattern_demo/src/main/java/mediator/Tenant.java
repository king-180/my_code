package mediator;

/**
 * @author wangxing
 * @date 2021/2/28 11:21
 */
public class Tenant extends Person {

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void constact(String msg) {
        mediator.constact(msg, this);
    }

    public void getMsg(String msg) {
        System.out.println("租房者：" + name + "消息：" + msg);
    }
}
