import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/4/17 16:19
 */
public class VolatileDemo {

    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + " flag = true, 退出 while 循环");
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            flag = false;
            System.out.println(Thread.currentThread().getName() + " 将 flag 从 true 改成 false");
        }, "t2").start();
    }
}
