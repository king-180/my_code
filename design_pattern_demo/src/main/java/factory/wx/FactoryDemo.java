package factory.wx;

/**
 * @author wangxing
 * @date 2021/4/19 10:24
 */
public class FactoryDemo {
    public static void main(String[] args) {

        System.out.println("===========简单工厂模式============");

        SendFactory factory = new SendFactory();
        Sender sms = factory.produce("sms");
        sms.send();
        Sender mail = factory.produce("mail");
        mail.send();

        System.out.println("===========静态工厂模式============");

        Sender sender1 = SendFactory.produceSms();
        sender1.send();
        Sender sender2 = SendFactory.produceMail();
        sender2.send();

        System.out.println("===========抽象工厂模式============");

        Producer producer1 = new SendSmsFactory();
        Sender sender3 = producer1.produce();
        sender3.send();

        Producer producer2 = new SendMailFactory();
        Sender sender4 = producer2.produce();
        sender4.send();
    }
}

interface Sender {
    void send();
}

class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("SMS sender ....");
    }
}

class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("Mail sender ....");
    }
}

class SendFactory {
    // 简单工厂模式
    public Sender produce(String type) {
        if ("sms".equals(type)) {
            return new SmsSender();
        } else if ("mail".equals(type)) {
            return new MailSender();
        } else {
            System.out.println("未知类型...");
            return null;
        }
    }

    // 静态工厂模式
    public static Sender produceSms() {
        return new SmsSender();
    }

    public static Sender produceMail() {
        return new MailSender();
    }

}

interface Producer {
    Sender produce();
}

class SendMailFactory implements Producer {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}

class SendSmsFactory implements Producer {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}