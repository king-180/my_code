package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 22:59
 */
public class OpenState extends LeftState {

    @Override
    public void open() {
        System.out.println("电梯开启...");
    }

    @Override
    public void close() {
        super.context.setLeftState(Context.CLOSE_STATE);
        super.context.close();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
