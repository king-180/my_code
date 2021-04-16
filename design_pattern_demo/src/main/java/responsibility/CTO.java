package responsibility;

/**
 * @author wangxing
 * @date 2021/2/27 21:22
 */
public class CTO extends Handler {

    public CTO() {
        super(Handler.NUM_THREE, Handler.NUM_SEVEN);
    }

    @Override
    protected void handlerLevel(LeaveRequest level) {
        System.out.println("========部门CTO处理：同意！========");
        System.out.println("请假人：" + level.getName() + " ,请假天数：" + level.getNum() + " ,请假原因：" + level.getContent());
    }
}
