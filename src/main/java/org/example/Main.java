package org.example;

import org.example.client.JavaIOMultiClient;
import org.example.server.JavaNIOBlockingServer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new JavaNIOBlockingServer()).start();
        Thread.sleep(100);
        JavaIOMultiClient.run();
    }
}