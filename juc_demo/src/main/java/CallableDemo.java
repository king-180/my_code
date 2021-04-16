import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wangxing
 * @date 2021/4/13 20:09
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread3());
        Thread thread = new Thread(futureTask, "AAA");
        Thread thread2 = new Thread(futureTask, "BBB");
        thread.start();
        thread2.start();
        String result = futureTask.get();
        System.out.println(result);
    }
}

class MyThread1 extends Thread {

    @Override
    public void run() {
        super.run();
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " ***********come in************");
        return "hello world...";
    }
}