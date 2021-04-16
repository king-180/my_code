package command;

/**
 * @author wangxing
 * @date 2021/2/27 20:21
 */
public class Demo {
    public static void main(String[] args) {

        Order order1 = new Order();
        order1.setDiningTable(1);
        order1.setFood("黄焖鸡", 2);
        order1.setFood("可乐", 1);

        Order order2 = new Order();
        order2.setDiningTable(2);
        order2.setFood("红烧牛肉面", 1);
        order2.setFood("啤酒", 1);

        SeniorChef receiver = new SeniorChef();
        Command com1 = new OrderCommand(receiver, order1);
        Command com2 = new OrderCommand(receiver, order2);

        Waiter invoke = new Waiter();
        invoke.addCommand(com1);
        invoke.addCommand(com2);
        invoke.orderUp();
    }
}
