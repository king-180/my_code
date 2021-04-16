package observer;

/**
 * @author wangxing
 * @date 2021/2/27 23:40
 */
public class WechatUser implements Observer {

    private String name;

    public WechatUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println("用户: " + name + "接收到消息：" + msg);
    }
}
