package stream;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/6/19 23:19
 */
public class GroupByDemo {
    public static void main(String[] args) throws ParseException {
        List<Student> students = SortDemo.getStudents();
        System.out.println(students.stream().collect(Collectors.groupingBy(Student::getBirthday)).size());
    }
}
