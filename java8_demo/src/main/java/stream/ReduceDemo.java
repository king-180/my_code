package stream;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author wangxing
 * @date 2021/4/19 16:14
 */
public class ReduceDemo {
    public static void main(String[] args) throws ParseException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(sum);

        List<Student> students = SortDemo.getStudents();
        Optional<Integer> sum2 = students.stream().map(Student::getId).reduce(Integer::sum);
        System.out.println(sum2);
    }
}
