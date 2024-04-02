package org.example;

import org.example.client.JavaIOMultiClient;
import org.example.netty.EventLoop;
import org.example.server.JavaNIOBlockingMultiServer;
import org.example.server.JavaNIOBlockingServer;
import org.example.server.JavaNIONonBlockingMultiServer;
import org.example.server.JavaNIONonBlockingServer;

import java.io.IOException;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException, IOException {
//        new Thread(new JavaNIOBlockingServer()).start();
//        new Thread(new JavaNIOBlockingMultiServer()).start();
//        new Thread(new JavaNIONonBlockingServer()).start();
//        new Thread(new JavaNIONonBlockingMultiServer()).start();

        List<EventLoop> eventLoopGroup = List.of(new EventLoop(8080), new EventLoop(8080));
        eventLoopGroup.forEach(EventLoop::run);
    }
}