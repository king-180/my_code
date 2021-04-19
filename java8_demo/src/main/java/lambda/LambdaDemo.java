package lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author wangxing
 * @date 2021/4/19 14:58
 */
public class LambdaDemo {
    public static void main(String[] args) {

        Comparator<Integer> comparator = (Integer::compare);
        System.out.println(comparator.compare(11, 22));
        System.out.println(comparator.compare(11, 11));
        System.out.println(comparator.compare(22, 11));

        Consumer<String> consumer = s -> System.out.println("1个参数 的消费型接口: " + s);
        consumer.accept("消费...");

    }
}
