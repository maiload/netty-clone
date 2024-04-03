package org.example.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class TcpHandler implements EventHandler{
    private final Logger log = LoggerFactory.getLogger(TcpHandler.class);
    @Override
    public void handle(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        handleRequest(clientChannel);
    }

    private void handleRequest(SocketChannel clientChannel) throws IOException {
        ByteBuffer requestByteBuffer = ByteBuffer.allocate(1024);
        clientChannel.read(requestByteBuffer);
        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("Request : {}", requestBody);

        ByteBuffer responseByteBuffer = ByteBuffer.wrap("This is Tcpserver".getBytes());
        clientChannel.write(responseByteBuffer);
        clientChannel.close();
    }

}
