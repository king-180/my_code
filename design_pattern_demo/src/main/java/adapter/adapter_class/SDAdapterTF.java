package adapter.adapter_class;

/**
 * @author wangxing
 * @date 2021/2/25 18:19
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {


    @Override
    public String readSD() {
        System.out.println("adapter read TFCard...");
        return readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write TFCard...");
        writeTF(msg);
    }
}
