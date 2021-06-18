import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wangxing
 * @date 2021/6/16 14:46
 */
public class UnsafeDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);

        Student student = new Student();
        long idOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("id"));
        long ageOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("age"));
        long nameOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));

        unsafe.compareAndSwapLong(student, idOffset, 0L, 10001L);
        unsafe.compareAndSwapInt(student, ageOffset, 0, 18);
        unsafe.compareAndSwapObject(student, nameOffset, null, "Lisa");

        System.out.println(student);
    }

}

@Data
class Student {
    private volatile long id;
    private volatile int age;
    private volatile String name;
}