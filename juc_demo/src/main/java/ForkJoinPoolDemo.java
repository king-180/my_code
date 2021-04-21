import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wangxing
 * @date 2021/4/21 16:51
 */
public class ForkJoinPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        MyTask task = new MyTask(0, 100);

        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);

        Integer result = submit.get();

        System.out.println(result);

        forkJoinPool.shutdown();

    }
}

class MyTask extends RecursiveTask<Integer> {

    private static final Integer MIN_NUM = 10;

    private int begin;
    private int end;

    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if ((end - begin) <= MIN_NUM) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int mid = (begin + end) / 2;
            MyTask task1 = new MyTask(begin, mid);
            MyTask task2 = new MyTask(mid + 1, end);
            task1.fork();
            task2.fork();
            result = task1.join() + task2.join();
        }

        return result;
    }
}