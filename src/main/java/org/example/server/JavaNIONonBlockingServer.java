package org.example.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class JavaNIONonBlockingServer  implements Runnable{
    private final Logger log = LoggerFactory.getLogger(JavaNIONonBlockingServer.class);
    private int sleepCount;
    @Override
    public void run() {
        log.info("Start JavaNIONonBlockingServer");
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()){
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false);

            while(true){
                var clientSocket = serverSocket.accept();
                if(clientSocket == null){
                    Thread.sleep(10);
                    sleepCount++;
                    continue;
                }

                log.warn("SleepCount : {}", sleepCount);
                handleRequest(clientSocket);
            }

        }catch (Exception e){
            log.error("ERROR", e);
        }

    }

    private void handleRequest(SocketChannel clientSocket) throws IOException, InterruptedException {
        var requestByteBuffer = ByteBuffer.allocate(1024);
        clientSocket.read(requestByteBuffer);
        requestByteBuffer.flip();
        var requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("Request : {}", requestBody);
        Thread.sleep(10);

        var responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
        clientSocket.write(responseByteBuffer);
        clientSocket.close();
    }
}
