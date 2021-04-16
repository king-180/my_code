package iterator;

/**
 * @author wangxing
 * @date 2021/2/28 12:03
 */
public interface StudentAggregate {

    void addStudent(Student student);

    void removeStudent(Student student);

    StudentIterator getStudentIterator();

}
