package adapter.adapter_object;

/**
 * @author wangxing
 * @date 2021/2/25 18:14
 */
public class Computer {
    public String readSD(SDCard sdCard) throws Exception {
        if (sdCard == null) {
            throw new Exception("sdCard is null");
        }
        return sdCard.readSD();
    }
}
