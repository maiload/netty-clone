package org.example;

import org.example.client.JavaIOMultiClient;
import org.example.server.JavaNIOBlockingServer;
import org.example.server.JavaNIONonBlockingMultiServer;
import org.example.server.JavaNIONonBlockingServer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new JavaNIOBlockingServer()).start();
//        new Thread(new JavaNIONonBlockingServer()).start();
//        new Thread(new JavaNIONonBlockingMultiServer()).start();
        Thread.sleep(100);
        JavaIOMultiClient.run();
    }
}