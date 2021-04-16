import java.util.concurrent.*;

/**
 * @author wangxing
 * @date 2021/4/13 20:46
 * 阻塞队列 blockingQueue满了才会开启救急线程
 * 线程数大于 最大线程数 maximumPoolSize加阻塞队列大小 capacity 就会执行jdk内置的拒决策略
 * 默认的拒绝策略为 AbortPolicy 执行会直接抛异常
 * 当救急线程没有任务并且超过 keepAliveTime 就会被回收
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()/*DiscardOldestPolicy()*//*CallerRunsPolicy()*//*AbortPolicy()*/);
        try {
            for (int i = 1; i <= 10; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务...");
                });
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private static void threadPoolInit() {
        // ExecutorService service = Executors.newFixedThreadPool(3);
        // ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 10; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务...");
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
