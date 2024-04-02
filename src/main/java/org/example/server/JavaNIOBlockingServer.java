package org.example.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class JavaNIOBlockingServer implements Runnable{
    private final Logger log = LoggerFactory.getLogger(JavaNIOBlockingServer.class);

    public void run(){
        log.info("Start JavaNIOBlockingServer");
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()){
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            while(true){
                var clientSocket = serverSocket.accept();
                handleRequest(clientSocket);
            }

        }catch (Exception e){
            log.error("ERROR", e);
        }
    }

    private void handleRequest(SocketChannel clientSocket) throws IOException {
        var requestByteBuffer = ByteBuffer.allocate(1024);
        clientSocket.read(requestByteBuffer);
        requestByteBuffer.flip();
        var requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("Request : {}", requestBody);

        var responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
        clientSocket.write(responseByteBuffer);
        clientSocket.close();
    }
}
