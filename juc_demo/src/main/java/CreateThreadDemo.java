import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wangxing
 * @date 2021/4/13 20:09
 */
public class CreateThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        createByThread();
        createByRunnable();
        createByCallable();
    }

    private static void createByThread() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    private static void createByRunnable() {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }

    private static void createByCallable() throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread1 = new Thread(futureTask, "AAA");
        Thread thread2 = new Thread(futureTask, "BBB");
        thread1.start();
        thread2.start();
        String result = futureTask.get();
        System.out.println(result);
    }
}

// 方式一：继承 Thread类
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ***********come in************");
    }
}

// 方式二：实现 Runnable接口
class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ***********come in************");
    }
}

// 方式二：实现 Callable<T> 接口，可以拿到线程执行返回值
class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " ***********come in************");
        return "hello world...";
    }
}