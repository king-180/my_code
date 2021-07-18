import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.serializer.StringEncoder;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/7/18 12:09
 */
public class KafkaProducer extends Thread {

    private Producer<String, String> producer;

    public KafkaProducer(String name) {
        super.setName(name);
        // 读取配置文件
        Properties properties = new Properties();
        // kafka地址,多个用逗号分隔
        properties.put("metadata.broker.list", "192.168.0.175:9092");
        // 设置写出数据的格式
        properties.put("serializer.class", StringEncoder.class.getName());
        // 写出应答方式
        properties.put("acks", 1);
        // 批量写出
        properties.put("batch.size", 16384);
        // 创建生产者对象
        producer = new Producer<>(new kafka.producer.ProducerConfig(properties));
    }

    @Override
    public void run() {
        int cnt = 0;
        System.out.println("KafkaProducer.run...开始发送数据...");

        while (cnt < 100000) {
            String key = String.valueOf(++cnt);
            String value = Thread.currentThread().getName() + "--" + cnt;
            // 封装消息对象
            KeyedMessage<String, String> message = new KeyedMessage<>("userlog", key, value);
            // 发送消息到服务器
            producer.send(message);
            // 打印消息
            System.out.println("Producer.run--" + key + "--" + value);
            // 每隔一秒钟发一条消息
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        KafkaProducer producer = new KafkaProducer("producer01");
        producer.start();
    }

}
