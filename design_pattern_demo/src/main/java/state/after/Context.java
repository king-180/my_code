package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 22:53
 */
public class Context {

    public static final OpenState OPEN_STATE = new OpenState();
    public static final CloseState CLOSE_STATE = new CloseState();
    public static final RunState RUN_STATE = new RunState();
    public static final StopState STOP_STATE = new StopState();

    private LeftState leftState;

    public LeftState getLeftState() {
        return leftState;
    }

    public void setLeftState(LeftState leftState) {
        this.leftState = leftState;
        this.leftState.setContext(this);
    }

    public void open() {
        this.leftState.open();
    }

    public void close() {
        this.leftState.close();
    }

    public void run() {
        this.leftState.run();
    }

    public void stop() {
        this.leftState.stop();
    }


}
