package stream;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/4/15 13:49
 */
public class FilterDemo {
    public static void main(String[] args) throws ParseException {
        List<Student> students = SortDemo.getStudents();
        List<Student> students1 = students.stream().filter(student -> student.getId() > 7).collect(Collectors.toList());
        System.out.println(students1);
    }
}
