package observer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxing
 * @date 2021/2/27 23:37
 */
public class SubscriptionSubject implements Subject {

    private Set<Observer> wechatUserList = new HashSet<>(16);

    @Override
    public void attach(Observer observer) {
        wechatUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        wechatUserList.remove(observer);
    }

    @Override
    public void notify(String msg) {
        for (Observer observer : wechatUserList) {
            observer.update(msg);
        }
    }
}
