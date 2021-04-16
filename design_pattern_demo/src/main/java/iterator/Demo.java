package iterator;

/**
 * @author wangxing
 * @date 2021/2/28 12:07
 */
public class Demo {
    public static void main(String[] args) {

        StudentAggregateImpl aggregate = new StudentAggregateImpl();
        aggregate.addStudent(new Student("Tom", "1001"));
        aggregate.addStudent(new Student("Jack", "1002"));
        aggregate.addStudent(new Student("Sim", "1003"));
        aggregate.addStudent(new Student("Lisa", "1004"));

        StudentIterator iterator = aggregate.getStudentIterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }

    }
}
