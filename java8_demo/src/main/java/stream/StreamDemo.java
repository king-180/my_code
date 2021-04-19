package stream;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author wangxing
 * @date 2021/4/19 15:46
 */
public class StreamDemo {
    public static void main(String[] args) throws ParseException {
        List<Student> students = SortDemo.getStudents();
        // 顺序流
        Stream<Student> studentStream = students.stream();
        // 并行流
        Stream<Student> parallelStream = students.parallelStream();

        Stream<String> stringStream = Arrays.stream(new String[]{"a", "b", "c"});

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

    }
}
