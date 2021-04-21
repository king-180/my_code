import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wangxing
 * @date 2021/4/21 17:35
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // runAsync 异步调用，无返回值
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " CompletableFuture.runAsync() 没有返回值...");
        });
        System.out.println(future1.get());

        // supplyAsync 异步调用，有返回值
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " CompletableFuture.supplyAsync() 有返回值...");
            int i = 10 / 0;
            return 1024;
        });
        Integer result = future2.whenComplete((t, u) -> {
            System.out.println(" ---> " + t);
            System.out.println(" ---> " + u);
        }).exceptionally(f -> {
            System.out.println(" ---> exception: " + f.getMessage());
            return 404;
        }).get();
        System.out.println(result);

    }
}
