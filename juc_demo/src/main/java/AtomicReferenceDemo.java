import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wangxing
 * @date 2021/4/13 15:19
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User z3 = new User("z3", 22);
        User l4 = new User("l4", 25);
        userAtomicReference.set(z3);
        System.out.println(userAtomicReference.compareAndSet(z3, l4) + " " + userAtomicReference.get());
        System.out.println(userAtomicReference.compareAndSet(z3, l4) + " " + userAtomicReference.get());
    }

}

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
class User {
    private String name;
    private int age;
}
