package builder.builder1;

/**
 * @author wangxing
 * @date 2021/2/25 13:42
 */
public abstract class Builder {
    protected Bike bike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract Bike createBike();
}
