package adapter.adapter_class;

/**
 * @author wangxing
 * @date 2021/2/25 18:09
 */
public interface TFCard {
    String readTF();

    void writeTF(String msg);
}
