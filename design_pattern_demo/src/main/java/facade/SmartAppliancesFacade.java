package facade;

/**
 * @author wangxing
 * @date 2021/2/27 13:20
 */
public class SmartAppliancesFacade {

    private final Light light;
    private final TV tv;
    private final AirCondition airCondition;

    public SmartAppliancesFacade() {
        light = new Light();
        tv = new TV();
        airCondition = new AirCondition();
    }

    public void say(String msg) {
        if (msg.contains("打开")) {
            on();
        } else if (msg.contains("关闭")) {
            off();
        } else {
            System.out.println("语言无法识别...请输入【打开】或【关闭】");
        }
    }

    private void on() {
        light.on();
        tv.on();
        airCondition.on();
    }

    private void off() {
        light.off();
        tv.off();
        airCondition.off();
    }

}
