package zklock;

/**
 * @author wangxing
 * @date 2021/7/31 14:21
 */
public class Demo {

    public static void main(String[] args) {
        IZKLock zkLock = new ZKDistributedLook();
        zkLock.lock();
        try {
            for (int i = 0; i < 200; i++) {
                new Thread(() -> {
                    int orderSn = OrderSnUtil.produceOrderSn();
                    System.out.println(Thread.currentThread().getName() + "\t-->\t" + orderSn);
                }, "t" + (i + 1)).start();
            }
            System.out.println();
        } finally {
            zkLock.unlock();
        }
    }

}

class OrderSnUtil {
    private static int id;

    public static int produceOrderSn() {
        return id++;
    }
}