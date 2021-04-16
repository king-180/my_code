import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/4/14 9:27
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String LockA = "LockA";
        String LockB = "LockB";
        new Thread(new HoldLockThread(LockA, LockB), "thread-AAA").start();
        new Thread(new HoldLockThread(LockB, LockA), "thread-BBB").start();
    }
}

class HoldLockThread implements Runnable {

    private final String lockA;
    private final String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 持有: " + lockA + " 尝试获取: " + lockB);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}