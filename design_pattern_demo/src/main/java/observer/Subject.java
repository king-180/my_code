package observer;

/**
 * @author wangxing
 * @date 2021/2/27 23:33
 */
public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notify(String msg);

}
