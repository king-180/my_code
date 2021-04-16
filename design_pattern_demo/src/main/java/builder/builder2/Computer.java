package builder.builder2;

/**
 * @author wangxing
 * @date 2021/2/25 14:07
 */
public class Computer {
    private String cpu;
    private String memory;
    private String screen;
    private Integer price;
    private Builder builder;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
        this.screen = builder.screen;
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", screen='" + screen + '\'' +
                ", price=" + price +
                '}';
    }

    public static final class Builder {
        private String cpu;
        private String memory;
        private String screen;
        private Integer price;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public Builder screen(String screen) {
            this.screen = screen;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
