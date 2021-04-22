import lombok.Data;

/**
 * @author wangxing
 * @date 2021/4/22 17:17
 */
public class ThreadLocalDemo {

    public static ThreadLocal<Thread> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 同一个线程共享数据

    }
}
