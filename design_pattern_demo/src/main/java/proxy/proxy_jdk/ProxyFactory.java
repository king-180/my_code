package proxy.proxy_jdk;

import java.lang.reflect.Proxy;

/**
 * @author wangxing
 * @date 2021/2/25 14:58
 */
public class ProxyFactory {

    private TrainStation trainStation = new TrainStation();

    public SellTicket getProxyObject() {
        return (SellTicket) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("invoke() 被调用了...收取代理费...");
                    return method.invoke(trainStation, args);
                });
    }

}
