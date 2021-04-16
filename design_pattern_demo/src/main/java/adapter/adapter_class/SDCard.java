package adapter.adapter_class;

/**
 * @author wangxing
 * @date 2021/2/25 18:12
 */
public interface SDCard {
    String readSD();

    void writeSD(String msg);
}
