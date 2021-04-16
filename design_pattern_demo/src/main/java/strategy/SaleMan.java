package strategy;

/**
 * @author wangxing
 * @date 2021/2/27 19:52
 */
public class SaleMan {

    private Strategy strategy;

    public SaleMan(Strategy strategy) {
        this.strategy = strategy;
    }

    public void saleManShow() {
        strategy.show();
    }

}
