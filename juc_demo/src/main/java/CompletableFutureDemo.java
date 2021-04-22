import java.util.concurrent.*;

/**
 * @author wangxing
 * @date 2021/4/21 17:35
 */
public class CompletableFutureDemo {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            10,
            5L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("main...start");

//        runAsync_supplyAsync();

//        runAfterBothAsync();

//        runAfterEitherAsync();

        allOf();

        System.out.println("main...end");

    }

    /**
     * 所有任务执行完才执行主线程， 必须调用 get()方法
     *
     * @throws InterruptedException 异常
     * @throws ExecutionException   异常
     */
    private static void allOf() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future1 开始计算...");
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = 10 * 2;
            System.out.println(Thread.currentThread().getName() + " future1 计算结束...");
            return res;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future2 开始计算...");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = 10 - 2;
            System.out.println(Thread.currentThread().getName() + " future2 计算结束...");
            return res;
        }, executor);

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future3 开始计算...");
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = 10 / 2;
            System.out.println(Thread.currentThread().getName() + " future3 计算结束...");
            return res;
        }, executor);

        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2, future3);
        // 必须调用 get()方法，使三个任务都异步执行完才执行主方法
        future.get();

        System.out.println("三个任务全部完成");
    }

    /**
     * 两个任务只要有一个完成就执行任务三
     */
    private static void runAfterEitherAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future1 开始计算...");
            int res = 10 * 2;
            System.out.println(Thread.currentThread().getName() + " future1 计算结束...");
            return res;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future2 开始计算...");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = 10 - 2;
            System.out.println(Thread.currentThread().getName() + " future2 计算结束...");
            return res;
        }, executor);

//        future1.runAfterEitherAsync(future2, () -> {
//            System.out.println(Thread.currentThread().getName() + " 任务3 开始计算...");
//        }, executor);

        CompletableFuture<Integer> future = future1.applyToEitherAsync(future2, (res) -> {
            System.out.println(Thread.currentThread().getName() + " 任务3 开始计算之前结果：" + res);
            return res * 3;
        }, executor);

        System.out.println("任务三结果：" + future.get());
    }

    /**
     * 两个任务都要完成才做第三个任务
     *
     * @throws ExecutionException   异常
     * @throws InterruptedException 异常
     */
    private static void runAfterBothAsync() throws ExecutionException, InterruptedException {
        // 异步任务编排
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future1 开始计算...");
            int res = 10 * 2;
            System.out.println(Thread.currentThread().getName() + " future1 计算结束...");
            return res;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " future2 开始计算...");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = 10 - 2;
            System.out.println(Thread.currentThread().getName() + " future2 计算结束...");
            return res;
        }, executor);

//        future1.runAfterBothAsync(future2, () -> {
//            System.out.println(Thread.currentThread().getName() + " 任务3 开始计算...");
//        }, executor);

//        future1.thenAcceptBothAsync(future2, (f1, f2) -> {
//            System.out.println("任务3之前的结果：" + f1 + " , " + f2);
//        }, executor);

        CompletableFuture<Integer> future3 = future1.thenCombineAsync(future2, (f1, f2) -> {
            System.out.println("任务3之前的结果：" + f1 + " , " + f2);
            return f1 * f2;
        }, executor);

        Integer res3 = future3.get();
        System.out.println("任务3之后的结果：" + res3);

    }

    private static void runAsync_supplyAsync() throws InterruptedException, ExecutionException {
        // runAsync 异步调用，无返回值
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " CompletableFuture.runAsync() 没有返回值...");
        }, executor);
        System.out.println(future1.get());

        // supplyAsync 异步调用，有返回值
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " CompletableFuture.supplyAsync() 有返回值...");
            int i = 10 / 2;
            return i;
        }, executor);

        // 方法完成后的感知
        Integer result = future2.whenComplete((res, exception) -> {
            System.out.println(" 结果：---> " + res);
            System.out.println(" 异常：---> " + exception);
        }).exceptionally(throwable -> {
            System.out.println(" ---> exception: " + throwable.getMessage());
            return 404;
        }).get();
        System.out.println(result);

        // 方法完成后的处理
        future2.handle((res, exception) -> {
            if (res != null) {
                return res * 2;
            }
            if (exception != null) {
                return 0;
            }
            return 0;
        });
    }
}
