package zklock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangxing
 * @date 2021/7/31 13:51
 */
public abstract class AbstractZKLock implements IZKLock {

    private static final String ZK_SERVER = "192.168.0.175:2181";

    private static final int ZK_TIMEOUT = 45 * 1000;

    protected static final String ZK_PATH = "/zkLockEphemeral";

    protected static CountDownLatch countDownLatch;

    protected static final ZkClient zkClient = new ZkClient(ZK_SERVER, ZK_TIMEOUT);

    @Override
    public void lock() {
        if (tryLock()) {
            // 尝试占锁成功
            System.out.println(Thread.currentThread().getName() + " 占锁成功");
        } else {
            // 尝试占锁失败，等待锁
            waitLock();
            lock();
        }
    }

    @Override
    public void unlock() {
        zkClient.close();
        System.out.println(Thread.currentThread().getName() + " 释放锁成功");
    }

    protected abstract boolean tryLock();

    protected abstract void waitLock();

}
