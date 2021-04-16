package state.before;

/**
 * @author wangxing
 * @date 2021/2/27 22:16
 */
public class Left implements ILeft {

    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void open() {
        switch (state) {
            case OPEN_STATE:
                System.out.println("请勿重复开门！");
                break;
            case CLOSE_STATE:
                System.out.println("电梯开门成功");
                this.setState(OPEN_STATE);
                break;
            case STOP_STATE:
                System.out.println("电梯开门成功");
                this.setState(OPEN_STATE);
                break;
            case RUN_STATE:
                System.out.println("电梯正在运行，不能开门！");
                break;
        }
    }

    @Override
    public void close() {
        switch (state) {
            case OPEN_STATE:
                System.out.println("电梯关门成功");
                this.setState(CLOSE_STATE);
                break;
            case CLOSE_STATE:
                System.out.println("请勿重复关门！");
                break;
            case STOP_STATE:
                System.out.println("电梯关门成功");
                this.setState(OPEN_STATE);
                break;
            case RUN_STATE:
                System.out.println("电梯正在运行，非法关门！");
                break;
        }
    }

    @Override
    public void run() {
        switch (state) {
            case OPEN_STATE:
                System.out.println("电梯处于开门状态，不能运行！");
                break;
            case CLOSE_STATE:
                System.out.println("电梯开始运行成功");
                this.setState(RUN_STATE);
                break;
            case STOP_STATE:
                System.out.println("电梯开始运行成功");
                this.setState(RUN_STATE);
                break;
            case RUN_STATE:
                System.out.println("电梯正在运行，请勿重复运行！");
                break;
        }
    }

    @Override
    public void stop() {
        switch (state) {
            case OPEN_STATE:
                break;
            case CLOSE_STATE:
                System.out.println("电梯停止了");
                this.setState(CLOSE_STATE);
                break;
            case STOP_STATE:
                break;
            case RUN_STATE:
                System.out.println("电梯停止了");
                this.setState(CLOSE_STATE);
                break;
        }
    }
}
