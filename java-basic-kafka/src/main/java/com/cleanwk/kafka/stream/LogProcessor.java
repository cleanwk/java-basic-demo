package com.cleanwk.kafka.stream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @author cleanwk
 * @date 2021/12/12
 */
public class LogProcessor implements Processor<byte[],byte[]> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext processorContext) {

    }

    @Override
    public void process(byte[] bytes, byte[] bytes2) {

    }

    @Override
    public void close() {

    }
}
