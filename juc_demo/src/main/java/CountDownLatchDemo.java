import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangxing
 * @date 2021/4/13 14:25
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "被灭...");
                countDownLatch.countDown();
            }, Objects.requireNonNull(CountryEnum.getNameByCode(i)).getName()).start();
        }

        countDownLatch.await();
        System.out.println("秦灭六国，一统天下...");
    }

}

@AllArgsConstructor
@Getter
enum CountryEnum {

    ONE(1, "齐国"),
    TWO(2, "楚国"),
    THREE(3, "燕国"),
    FOUR(4, "韩国"),
    FIVE(5, "赵国"),
    SIX(6, "魏国"),
    UNKNOWN(99, "未知");

    private Integer code;
    private String name;

    public static CountryEnum getNameByCode(int code) {
        for (CountryEnum element : CountryEnum.values()) {
            if (element.getCode() == code) {
                return element;
            }
        }
        return null;
    }

}