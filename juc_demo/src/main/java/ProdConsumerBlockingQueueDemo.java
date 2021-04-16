import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangxing
 * @date 2021/4/13 18:10
 */
public class ProdConsumerBlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(5));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 生产线程启动...");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 消费线程启动...");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒钟时间到，停止...");
        myResource.stop();
    }
}

class MyResource {

    private boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retVal;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retVal = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retVal) {
                System.out.println(Thread.currentThread().getName() + " 插入队列成功：" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + " 插入队列失败：" + data);
            }
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 停停停...");
    }

    public void myConsumer() throws InterruptedException {

        String resVal;
        while (FLAG) {
            resVal = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (resVal == null || "".equals(resVal)) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + " 超过2秒钟没有取到蛋糕,消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费队列" + resVal + " 成功");
        }

    }

    public void stop() {
        FLAG = false;
    }
}