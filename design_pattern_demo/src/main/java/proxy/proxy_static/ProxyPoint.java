package proxy.proxy_static;

/**
 * @author wangxing
 * @date 2021/2/25 14:53
 */
public class ProxyPoint implements SellTicket {
    private TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("代理点收取服务费...");
        trainStation.sell();
    }
}
