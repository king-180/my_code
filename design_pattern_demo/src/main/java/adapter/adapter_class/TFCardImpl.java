package adapter.adapter_class;

/**
 * @author wangxing
 * @date 2021/2/25 18:10
 */
public class TFCardImpl implements TFCard {
    @Override
    public String readTF() {
        String msg = "TFCard read msg : hello world! ";
        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("TFCard write msg : " + msg);
    }
}
