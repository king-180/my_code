package strategy;

/**
 * @author wangxing
 * @date 2021/2/27 19:54
 */
public class Demo {
    public static void main(String[] args) {
        SaleMan saleMan = new SaleMan(new StrategyA());
        saleMan.saleManShow();
        saleMan = new SaleMan(new StrategyB());
        saleMan.saleManShow();
        saleMan = new SaleMan(new StrategyC());
        saleMan.saleManShow();

    }
}
