package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 22:59
 */
public class StopState extends LeftState {
    @Override
    public void open() {
        super.context.setLeftState(Context.OPEN_STATE);
        super.context.open();
    }

    @Override
    public void close() {
        super.context.setLeftState(Context.CLOSE_STATE);
        super.context.close();
    }

    @Override
    public void run() {
        super.context.setLeftState(Context.RUN_STATE);
        super.context.run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }
}
