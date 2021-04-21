package lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/4/21 17:07
 */
public class ChainDemo {
    public static void main(String[] args) {
        Book book = new Book();
        book.setId(1001).setName("深入了解JVM虚拟机").setPrice(new BigDecimal("199.9"));
        System.out.println(book);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Book {
    private Integer id;
    private String name;
    private BigDecimal price;
}