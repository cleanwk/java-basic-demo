package com.example;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author cleanwk
 * @date 2022/7/3
 */
public class HbaseConnection {

    public static Connection  connection= null;

    static {

        try {
            connection = ConnectionFactory.createConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(connection);
    }

}
