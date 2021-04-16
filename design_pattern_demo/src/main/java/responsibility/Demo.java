package responsibility;

/**
 * @author wangxing
 * @date 2021/2/27 21:29
 */
public class Demo {
    public static void main(String[] args) {
        LeaveRequest level = new LeaveRequest("Lisa", 8, "世界那么大，我想去看看！");

        GroupLeader groupLeader = new GroupLeader();
        Manager manager = new Manager();
        CTO cto = new CTO();

        groupLeader.setNextHandler(manager);
        manager.setNextHandler(cto);

        groupLeader.submit(level);

    }
}
