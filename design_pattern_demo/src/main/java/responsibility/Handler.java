package responsibility;

/**
 * @author wangxing
 * @date 2021/2/27 21:12
 */
public abstract class Handler {

    protected final static int NUM_ONE = 1;
    protected final static int NUM_THREE = 3;
    protected final static int NUM_SEVEN = 7;

    private int numStart;
    private int numEnd;

    private Handler nextHandler;

    public Handler(int numStart) {
        this.numStart = numStart;
    }

    public Handler(int numStart, int numEnd) {
        this.numStart = numStart;
        this.numEnd = numEnd;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void handlerLevel(LeaveRequest level);

    public final void submit(LeaveRequest level) {
        if (this.nextHandler != null && level.getNum() > this.numEnd) {
            this.nextHandler.submit(level);
        } else {
            this.handlerLevel(level);
            System.out.println("流程审批结束!");
        }
    }
}
