package state.before;

/**
 * @author wangxing
 * @date 2021/2/27 22:28
 */
public class Demo {
    public static void main(String[] args) {
        Left left = new Left();
        left.setState(ILeft.OPEN_STATE);
        left.open();
        left.close();
        left.run();
        left.stop();
    }
}
