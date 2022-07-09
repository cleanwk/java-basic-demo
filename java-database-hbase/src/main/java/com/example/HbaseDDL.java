package com.example;

import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

/**
 * @author cleanwk
 * @date 2022/7/3
 */
public class HbaseDDL {

    private static Connection connection = HbaseConnection.connection;

    public static void createNamespace(String namespace) throws IOException {
        Admin admin = connection.getAdmin();
        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(namespace);
        builder.addConfiguration("user","it-boy");

        admin.createNamespace(builder.build());

        admin.close();
    }


    public static void main(String[] args) throws IOException {
        createNamespace("first-namespace");
        System.out.println("code....");
        connection.close();
    }
}
