package com.cleanwk.kafka.admin;

import com.sun.tools.javac.util.List;
import org.apache.kafka.clients.admin.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author cleanwk
 * @date 2021/12/13
 */
public class TestAdminClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        createTopic("third");
        topicLists();
    }
    /**
     * 配置并创建AdminClient
     */
    public static AdminClient adminClient() {
        Properties properties = new Properties();
        // 配置Kafka服务的访问地址及端口号
        properties.setProperty(AdminClientConfig.
                BOOTSTRAP_SERVERS_CONFIG, "txcloud.com:9092");

        // 创建AdminClient实例
        return AdminClient.create(properties);
    }

    /**
     * 创建topic
     */
    public static void createTopic(String name) throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        // partition数量
        int numPartitions = 1;
        // 副本数量
        short replicationFactor = 1;
        NewTopic topic = new NewTopic(name, numPartitions, replicationFactor);
        CreateTopicsResult result = adminClient.createTopics(List.of(topic));
        // 避免客户端连接太快断开而导致Topic没有创建成功
        Thread.sleep(500);
        // 获取topic设置的partition数量
        System.out.println(result.numPartitions(name).get());
    }

    /**
     * 查询Topic列表
     */
    public static void topicLists() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        ListTopicsResult result1 = adminClient.listTopics();
        // 打印Topic的名称
        System.out.println(result1.names().get());
        // 打印Topic的信息
        System.out.println(result1.listings().get());

        ListTopicsOptions options = new ListTopicsOptions();
        // 是否列出内部使用的Topic
        options.listInternal(true);
        ListTopicsResult result2 = adminClient.listTopics(options);
        System.out.println(result2.names().get());
    }




}
