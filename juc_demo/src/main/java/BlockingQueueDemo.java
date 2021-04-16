import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/4/13 16:56
 * 阻塞队列：队列为空 take操作会被阻塞，队列满的时候 put操作被阻塞
 * ArrayBlockingQueue : 由数组构成的有界阻塞队列
 * LinkedBlockingQueue ：由链表构成的有界阻塞队列（界限默认值为integer最大值）
 * SynchronousQueue : 不存储元素的阻塞队列，也即单个元素的队列（队列只能放一个元素，消费一个生产一个）
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
    }

    private static void put_take(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        blockingQueue.put("d");
        System.out.println(blockingQueue);
    }

    private static void offer_peek_poll(BlockingQueue<String> blockingQueue) {
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d");
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void add_remove_element(BlockingQueue<String> blockingQueue) {
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        blockingQueue.add("d");
    }
}
