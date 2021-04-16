package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/2/28 12:04
 */
public class StudentAggregateImpl implements StudentAggregate {

    private final List<Student> studentList = new ArrayList<>(10);

    @Override
    public void addStudent(Student student) {
        studentList.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        studentList.remove(student);
    }

    @Override
    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(studentList);
    }
}
