package com.cleanwk.kafka.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.internals.InternalTopologyBuilder;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author cleanwk
 * @date 2021/12/13
 */
public class IOStream {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"loggliter");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"txcloud:9092"); //zookeeper的地址
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass()); //输入key的类型
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.stream("first").to("second");

        Topology build = streamsBuilder.build();

        KafkaStreams streams = new KafkaStreams(build, properties);

        CountDownLatch latch = new CountDownLatch(1);
        // 附加关闭处理程序来捕获control-c
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);//是非正常退出，就是说无论程序正在执行与否，都退出
        }
        System.exit(0);//正常退出，程序正常执行结束退出
    }
}

