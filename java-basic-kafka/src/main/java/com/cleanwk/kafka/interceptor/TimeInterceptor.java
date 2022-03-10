package com.cleanwk.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.ProducerInterceptors;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

/**
 * @author cleanwk
 * @date 2021/12/12
 */
public class TimeInterceptor implements ProducerInterceptor<String,String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String value = record.value();
        String newValue = String.valueOf(System.currentTimeMillis())+"_"+value;
        return new ProducerRecord<String, String>(record.topic(),newValue);
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }


    @Override
    public void configure(Map<String, ?> configs) {

    }
}
