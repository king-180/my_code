import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author wangxing
 * @date 2021/6/13 11:49
 */
public class LongAdderDemo {

    public static void main(String[] args) {

        LongAdder longAdder = new LongAdder();
        // LongAdder 性能比 AtomicLong 性能更优越:
        // LongAdder 在有竞争的情况下，设置多个累加单元，线程1累加Cell[0],线程2累加Cell[1]...最后将结果汇总起来。
        // 这样它们在累加的时候操作的不同的Cell变量，因此减少了CAS重试失败次数，从而提高了性能。

        longAdder.increment();
        System.out.println(longAdder.intValue());

        AtomicLong atomicLong = new AtomicLong(0);
        long res = atomicLong.incrementAndGet();
        System.out.println(res);

    }

}
