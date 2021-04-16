package strategy;

/**
 * @author wangxing
 * @date 2021/2/27 19:51
 */
public class StrategyC implements Strategy {
    @Override
    public void show() {
        System.out.println("满1000送电饭煲");
    }
}
