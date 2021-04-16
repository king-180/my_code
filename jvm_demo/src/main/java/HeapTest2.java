import java.util.concurrent.TimeUnit;

/**
 * -Xms20m -Xss20m
 * @author wangxing
 * @date 2020/12/26 23:24
 */
public class HeapTest2 {
    public static void main(String[] args) {
        System.out.println("HeapTest2 start....");
        try {
            TimeUnit.SECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HeapTest2 end....");
    }
}
