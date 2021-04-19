package functional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wangxing
 * @date 2021/4/19 15:10
 */
public class FunctionalDemo {
    public static void main(String[] args) {
        // 只有一个抽象方法的接口(不能出现第二个方法) --> 函数式接口

        Consumer<String> consumer = System.out::println;
        consumer.accept("hello world...");

        List<String> list = filterString(Arrays.asList("abc", "a", "b", "c", "d", "ad"), s -> s.contains("a"));
        System.out.println(list);

        Emp tom = new Emp(1, "Tom");
        Supplier<String> supplier = tom::getName;
        String s = supplier.get();
        System.out.println(s);

        Function<Double, Long> function = Math::round;
        Long res = function.apply(12.6);
        System.out.println(res);

    }

    public static List<String> filterString(List<String> stringList, Predicate<String> predicate) {
        List<String> list = new ArrayList<>(10);
        for (String s : stringList) {
            if (predicate.test(s)) {
                list.add(s);
            }
        }
        return list;
    }
}

@FunctionalInterface
interface MyFunctionalInterface {

    void method();

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Emp {
    private int id;
    private String name;
}