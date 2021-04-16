package responsibility;

/**
 * @author wangxing
 * @date 2021/2/27 21:22
 */
public class Manager extends Handler {

    public Manager() {
        super(NUM_ONE, Handler.NUM_THREE);
    }

    @Override
    protected void handlerLevel(LeaveRequest level) {
        System.out.println("========部门经理处理：同意！========");
        System.out.println("请假人：" + level.getName() + " ,请假天数：" + level.getNum() + " ,请假原因：" + level.getContent());
    }
}
