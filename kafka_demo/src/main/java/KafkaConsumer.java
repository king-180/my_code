import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangxing
 * @date 2021/7/18 12:09
 */
public class KafkaConsumer extends Thread {

    private ConsumerConnector consumer;

    public KafkaConsumer(String name) {
        super.setName(name);
        // 读取配置文件
        Properties properties = new Properties();
        // zk地址
        properties.put("zookeeper.connect", "192.168.0.175:2181");
        // 消费者组id
        properties.put("group.id", "wx-bigdata");
        // zk超时时间
        properties.put("zookeeper.session.timeout.ms", "4000");
        // 当消费者开始第一次消费时候，从最低的偏移量开始消费
        properties.put("auto.offset.reset", "smallest");
        // 自动提交偏移量
        properties.put("auto.commit.enable", "true");
        // 消费者自动提交偏移量的时间间隔
        properties.put("auto.commit.interval.ms", "1000");
        // 创建消费者对象
        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));

    }

    @Override
    public void run() {
        Map<String, Integer> topicMap = new HashMap<>();
        topicMap.put("userlog", 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicMap);
        List<KafkaStream<byte[], byte[]>> list = consumerMap.get("userlog");
        KafkaStream<byte[], byte[]> stream0 = list.get(0);
        ConsumerIterator<byte[], byte[]> it = stream0.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> value = it.next();
            int partition = value.partition();
            long offset = value.offset();
            String data = new String(value.message());
            System.out.println("start...开始操作消息 " + data + " partition: " + partition + " offset: " + offset);
        }
    }

    public static void main(String[] args) {
        KafkaConsumer consumer = new KafkaConsumer("consumer01");
        consumer.start();
    }

}
