package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 22:59
 */
public class CloseState extends LeftState {
    @Override
    public void open() {
        super.context.setLeftState(Context.OPEN_STATE);
        super.context.open();
    }

    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    @Override
    public void run() {
        super.context.setLeftState(Context.RUN_STATE);
        super.context.run();
    }

    @Override
    public void stop() {
        super.context.setLeftState(Context.STOP_STATE);
        super.context.stop();
    }
}
