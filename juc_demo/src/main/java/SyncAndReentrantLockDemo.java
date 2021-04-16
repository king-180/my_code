import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxing
 * @date 2021/4/13 17:53
 * A线程打印5次 --> B线程打印5次 --> C线程打印5次 循环10轮打印
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}

class ShareResource {

    private String thread = "A";
    private final Lock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    private Condition c = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (!thread.equals("A")) {
                a.await();
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "AAA" + i);
                }
            }
            thread = "B";
            b.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (!thread.equals("B")) {
                b.await();
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "BBB" + i);
                }
            }
            thread = "C";
            c.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (!thread.equals("C")) {
                c.await();
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "CCC" + i);
                }
            }
            thread = "A";
            a.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}