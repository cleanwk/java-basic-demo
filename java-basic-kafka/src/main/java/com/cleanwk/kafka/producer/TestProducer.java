package com.cleanwk.kafka.producer;

import com.cleanwk.kafka.interceptor.TimeInterceptor;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author cleanwk
 * @date 2021/12/12
 */
public class TestProducer {


    public static void main(String[] args) {
        //初始化配置
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG,"1");
        properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, TimeInterceptor.class.getName());


        //创建生产者
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        //
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("first","COeeME you");
        //异步发送数据
        producer.send(record);

        //回调函数
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(metadata.partition());
                System.out.println(metadata.offset());
            }
        });

        System.out.println("发送结束");

        producer.close();
    }
}
