package proxy.proxy_static;

/**
 * @author wangxing
 * @date 2021/2/25 14:52
 */
public class TrainStation implements SellTicket {
    @Override
    public void sell() {
        System.out.println("火车站买票...");
    }
}
