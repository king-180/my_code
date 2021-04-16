package observer;

/**
 * @author wangxing
 * @date 2021/2/27 23:41
 */
public class Demo {
    public static void main(String[] args) {

        SubscriptionSubject subscriptionSubject = new SubscriptionSubject();

        subscriptionSubject.attach(new WechatUser("Lisa"));
        subscriptionSubject.attach(new WechatUser("Tom"));
        subscriptionSubject.attach(new WechatUser("Jack"));

        subscriptionSubject.notify("秦时明月更新了...");
    }
}
