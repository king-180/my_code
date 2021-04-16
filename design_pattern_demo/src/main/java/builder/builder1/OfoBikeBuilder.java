package builder.builder1;

/**
 * @author wangxing
 * @date 2021/2/25 13:45
 */
public class OfoBikeBuilder extends Builder {
    @Override
    public void buildFrame() {
        bike.setFrame("Ofo单车框架");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("Ofo单车座位");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
