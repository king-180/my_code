import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wangxing
 * @date 2021/4/13 15:04
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("7颗龙珠集齐，召唤神龙..."));

        for (int i = 1; i <= 7; i++) {
            final int tem = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "搜集到第" + tem + "颗龙珠...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }

    }
}
