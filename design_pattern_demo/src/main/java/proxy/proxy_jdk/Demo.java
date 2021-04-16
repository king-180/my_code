package proxy.proxy_jdk;

/**
 * @author wangxing
 * @date 2021/2/25 15:06
 */
public class Demo {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        SellTicket proxyObject = proxyFactory.getProxyObject();
        proxyObject.sell();
    }
}
