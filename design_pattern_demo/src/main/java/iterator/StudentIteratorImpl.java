package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/2/28 11:59
 */
public class StudentIteratorImpl implements StudentIterator {

    private List<Student> studentList = new ArrayList<>(10);
    private int position = 0;

    public StudentIteratorImpl(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean hasNext() {
        return position < studentList.size();
    }

    @Override
    public Student next() {
        return studentList.get(position++);
    }
}
