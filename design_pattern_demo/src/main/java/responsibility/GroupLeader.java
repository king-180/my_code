package responsibility;

/**
 * @author wangxing
 * @date 2021/2/27 21:22
 */
public class GroupLeader extends Handler {

    public GroupLeader() {
        super(0, Handler.NUM_ONE);
    }

    @Override
    protected void handlerLevel(LeaveRequest level) {
        System.out.println("========部门组长处理：同意！========");
        System.out.println("请假人：" + level.getName() + " ,请假天数：" + level.getNum() + " ,请假原因：" + level.getContent());
    }
}
