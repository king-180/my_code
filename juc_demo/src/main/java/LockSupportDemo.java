import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxing
 * @date 2021/4/14 11:04
 * 线程等待唤醒机制
 * synchronized + wait + notify
 * reentrantLock + await + signal
 * lockSupport + park + unpark
 */
public class LockSupportDemo {

    static final Object objectLock = new Object();

    static final Lock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 被唤醒...");
        }, "t1");
        t1.start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            LockSupport.unpark(t1);
        }, "t2").start();
    }

    private static void reentrantLockAwaitSignal() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in...");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " 被唤醒...");
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " 通知...");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + " come in...");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 被唤醒...");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + " 通知...");
            }
        }, "t2").start();
    }
}
