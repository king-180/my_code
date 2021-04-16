import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2020/12/27 12:57
 */
public class HeapTest3 {

    byte[] bytes = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        ArrayList<HeapTest3> list = new ArrayList<>();
        while (true){
            list.add(new HeapTest3());
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
