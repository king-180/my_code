package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 22:59
 */
public class RunState extends LeftState{
    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println("电梯正在运行...");
    }

    @Override
    public void stop() {
        super.context.setLeftState(Context.STOP_STATE);
        super.context.stop();
    }
}
