package facade;

/**
 * @author wangxing
 * @date 2021/2/27 13:20
 */
public class Demo {
    public static void main(String[] args) {
        SmartAppliancesFacade facade = new SmartAppliancesFacade();
        facade.say("打开");
        System.out.println("============");
        facade.say("关闭");
    }
}
