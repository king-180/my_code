import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangxing
 * @date 2021/4/13 16:36
 * 写操作保证原子性，不可中断
 * 读-读 共享
 * 读-写 互斥
 * 写-写 互斥
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 10; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.put(tmp + "", tmp);
            }, "t" + i).start();
        }

        for (int i = 1; i <= 10; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.get(tmp + "");
            }, "t" + i).start();
        }
    }
}

class MyCache {
    private final Map<String, Object> map = new HashMap<>(16);

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入缓存...");
            try {
                TimeUnit.MILLISECONDS.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入缓存成功...");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取...");
            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取到数据：" + o);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void clear() {
        map.clear();
    }
}