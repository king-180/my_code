package builder.builder2;

/**
 * @author wangxing
 * @date 2021/2/25 14:07
 */
public class Demo {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .cpu("Intel")
                .price(5000)
                .memory("金士顿")
                .screen("三星")
                .build();
        System.out.println(computer);
    }
}
