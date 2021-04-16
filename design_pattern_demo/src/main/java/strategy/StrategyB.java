package strategy;

/**
 * @author wangxing
 * @date 2021/2/27 19:51
 */
public class StrategyB implements Strategy {
    @Override
    public void show() {
        System.out.println("满200减50");
    }
}
