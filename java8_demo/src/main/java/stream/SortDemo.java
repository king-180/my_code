package stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/4/14 16:09
 */
public class SortDemo {
    public static void main(String[] args) throws ParseException {

        List<Student> students = getStudents();

        List<Student> studentList1 = students.stream()
                .sorted(Comparator.comparing(Student::getBirthday))
                .collect(Collectors.toList());
        studentList1.forEach(System.out::println);
        System.out.println("====================================================");
        List<Student> studentList2 = students.stream()
                .sorted(Comparator.comparing(Student::getBirthday, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        studentList2.forEach(System.out::println);
        System.out.println("====================================================");
        List<Student> studentList3 = students.stream()
                .sorted(Comparator.comparing(Student::getBirthday).reversed())
                .collect(Collectors.toList());
        studentList3.forEach(System.out::println);
        System.out.println("====================================================");
        List<Student> studentList4 = students.stream()
                .sorted(Comparator.comparing(Student::getBirthday).reversed().thenComparing(Student::getId))
                .collect(Collectors.toList());
        studentList4.forEach(System.out::println);
    }

    public static List<Student> getStudents() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Student> students = Arrays.asList(
                new Student(1, sdf.parse("2021-11-12"), "A"),
                new Student(3, sdf.parse("2021-11-15"), "C"),
                new Student(2, sdf.parse("2021-11-11"), "D"),
                new Student(6, sdf.parse("2021-11-16"), "B"),
                new Student(4, sdf.parse("2021-11-14"), "F"),
                new Student(5, sdf.parse("2021-11-13"), "E"),
                new Student(9, sdf.parse("2021-11-11"), "G"),
                new Student(8, sdf.parse("2021-11-14"), "H"),
                new Student(7, sdf.parse("2021-11-11"), "I"),
                new Student(0, sdf.parse("2021-11-12"), "J"));
        return students;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Student {

    private Integer id;

    private Date birthday;

    private String name;

}