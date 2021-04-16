import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxing
 * @date 2021/4/13 17:23
 * sync -> wait -> notify
 * lock -> await -> signalAl
 * lockSupport -> park -> unpark
 */
public class ProdConsumerDemo1 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    shareData.up();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    shareData.down();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
    }
}

class ShareData {
    private int number = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void up() throws InterruptedException {
        lock.lock();
        try {
            // 多线程下的判断用 while 不能用 if判断，if不能用于多线程（超过2个线程）
            while (number != 0) {
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            // 通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void down() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                // 等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " " + number);
        } finally {
            lock.unlock();
        }
    }

}