package strategy;

/**
 * @author wangxing
 * @date 2021/2/27 19:51
 */
public class StrategyA implements Strategy {
    @Override
    public void show() {
        System.out.println("买一送一");
    }
}
