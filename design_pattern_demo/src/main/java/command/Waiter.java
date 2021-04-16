package command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/2/27 20:16
 */
public class Waiter {

    private List<Command> commands = new ArrayList<>(10);

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public void orderUp() {
        System.out.println("服务员：厨师，订单来了！");
        for (Command cmd : commands) {
            if (cmd != null) {
                cmd.execute();
            }
        }
    }
}
