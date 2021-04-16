package adapter.adapter_object;

/**
 * @author wangxing
 * @date 2021/2/25 18:16
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Computer computer = new Computer();
        String msg = computer.readSD(new SDCardImpl());
        System.out.println(msg);
        System.out.println("========================");
        String msg2 = computer.readSD(new SDAdapterTF(new TFCardImpl()));
        System.out.println(msg2);
    }
}
