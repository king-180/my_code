package command;

import java.util.Map;
import java.util.Set;

/**
 * @author wangxing
 * @date 2021/2/27 20:12
 */
public class OrderCommand implements Command {

    private SeniorChef receiver;
    private Order order;

    public OrderCommand(SeniorChef receiver, Order order) {
        this.receiver = receiver;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println("============" + order.getDiningTable() + " 桌的订单：" + "============");
        Map<String, Integer> fooDir = order.getFooDir();
        Set<String> keySet = fooDir.keySet();
        for (String foodName : keySet) {
            receiver.makeFood(foodName, fooDir.get(foodName));
        }
        System.out.println(order.getDiningTable() + " 桌的菜准备完毕！");
    }
}
