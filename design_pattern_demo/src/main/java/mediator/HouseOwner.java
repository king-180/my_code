package mediator;

/**
 * @author wangxing
 * @date 2021/2/28 11:41
 */
public class HouseOwner extends Person {
    public HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void constact(String msg) {
        mediator.constact(msg, this);
    }

    public void getMsg(String msg) {
        System.out.println("房主：" + name + "消息：" + msg);
    }
}
