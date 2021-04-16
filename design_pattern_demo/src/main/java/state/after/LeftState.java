package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 22:52
 */
public abstract class LeftState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();

}
