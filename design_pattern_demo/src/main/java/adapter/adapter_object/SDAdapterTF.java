package adapter.adapter_object;

/**
 * @author wangxing
 * @date 2021/2/25 18:19
 */
public class SDAdapterTF implements SDCard {

    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read TFCard...");
        return tfCard.readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write TFCard...");
        tfCard.writeTF(msg);
    }
}
