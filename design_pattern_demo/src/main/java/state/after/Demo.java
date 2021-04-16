package state.after;

/**
 * @author wangxing
 * @date 2021/2/27 23:20
 */
public class Demo {
    public static void main(String[] args) {

        Context context = new Context();
        context.setLeftState(Context.RUN_STATE);

        context.open();
        context.stop();
        context.close();
        context.run();

    }
}
