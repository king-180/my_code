package zklock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangxing
 * @date 2021/7/31 13:54
 */
public class ZKDistributedLook extends AbstractZKLock {
    @Override
    protected boolean tryLock() {
        try {
            zkClient.createEphemeral(ZK_PATH);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    protected void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        // 添加监听器
        zkClient.subscribeDataChanges(ZK_PATH, listener);
        if (zkClient.exists(ZK_PATH)) {
            // 只能等着，不能往下走
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 解除监听器
            zkClient.unsubscribeDataChanges(ZK_PATH, listener);
        }
    }
}
