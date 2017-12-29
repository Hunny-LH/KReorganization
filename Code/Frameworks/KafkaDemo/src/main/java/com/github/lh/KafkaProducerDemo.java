package com.github.lh;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-29
 */
public class KafkaProducerDemo {

    private static final Properties props = new Properties();

    static {
        // broker的地址
        props.put("bootstrap.servers", "localhost:9092");
        // 判别请求是否完整的条件（就是判断是不是成功发送了）。指定 All 将会阻塞消息。
        props.put("acks", "all");
        // 重试次数，0表示不重试，如果启用重试，则会有重复消息的可能性
        props.put("retries", 0);
        // 生产者缓存每个分区未发送的消息。batch.size 指定分批次发送缓存消息的数量。
        props.put("batch.size", 16384);
        // 生产者发送消息的间隔，单位毫秒；如果为0，则是立即发送，为默认值。
        props.put("linger.ms", 1000);
        // 控制生产者缓存的总量，如果消息发送速度比其传输到服务器的快，将会耗尽这个缓存空间。
        // 当缓存空间耗尽，其他发送调用将被阻塞，阻塞时间的阈值通过max.block.ms设定，
        // 之后它将抛出一个TimeoutException。
        props.put("buffer.memory", 33554432);
        // 键值序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static void main(String[] args) throws InterruptedException {
        Producer<String, String> producer = new KafkaProducer<>(props);
        AtomicInteger counter = new AtomicInteger(0);

        Callback callback = (recordMetadata, e) -> {
            System.out.println(recordMetadata.toString());

        };
        Consumer<String> send = s -> producer.send(new ProducerRecord<>("test", s, s), callback);

        List<String> messages = Stream.generate(() -> String.format("message:%d", counter.getAndIncrement()))
                .limit(100000)
                .collect(Collectors.toList());

        for (String message : messages) {
            send.accept(message);
            Thread.sleep(5 * 1000);
        }

        producer.close();
    }
}
