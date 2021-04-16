import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * -Xms10m -Xmx10m
 *
 * @author wangxing
 * @date 2020/12/26 23:24
 */
public class HeapTest1 {
    public static void main(String[] args) {
        System.out.println("-Xmx:" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.println("-Xms:" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
        ArrayList<Integer> list = new ArrayList<>();
        while (true){
            list.add((int) (Math.random()*10000));
        }
//        try {
//            TimeUnit.SECONDS.sleep(600);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
