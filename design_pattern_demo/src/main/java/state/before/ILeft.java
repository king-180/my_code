package state.before;

/**
 * @author wangxing
 * @date 2021/2/27 22:13
 */
public interface ILeft {

    int OPEN_STATE = 1;
    int CLOSE_STATE = 2;
    int RUN_STATE = 3;
    int STOP_STATE = 4;

    void setState(int state);

    void open();

    void close();

    void run();

    void stop();
}
