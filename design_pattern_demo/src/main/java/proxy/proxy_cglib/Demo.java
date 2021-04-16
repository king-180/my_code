package proxy.proxy_cglib;

/**
 * @author wangxing
 * @date 2021/2/25 15:50
 */
public class Demo {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        TrainStation proxyObject = proxyFactory.getProxyObject();
        proxyObject.sell();
    }
}
