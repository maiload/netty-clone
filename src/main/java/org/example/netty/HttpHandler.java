package org.example.netty;

import org.example.netty.http.HttpCodec;
import org.example.netty.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class HttpHandler implements EventHandler{
    private final Logger log = LoggerFactory.getLogger(HttpHandler.class);
    private final HttpCodec httpCodec;

    public HttpHandler() {
        httpCodec = new HttpCodec();
    }

    @Override
    public void handle(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        handleRequest(clientChannel);
    }

    private void handleRequest(SocketChannel clientChannel) throws IOException {
        ByteBuffer requestByteBuffer = ByteBuffer.allocate(1024);
        clientChannel.read(requestByteBuffer);
        HttpRequest httpRequest = httpCodec.decode(requestByteBuffer);
        log.info("Method : {}, urlPath : {}, queryStrings : {}",
                httpRequest.getMethod(), httpRequest.getUrlPath(), httpRequest.getQueryStrings());

        String responseBody = "This is httpServer";
        ByteBuffer responseByteBuffer = httpCodec.encode(responseBody);
        clientChannel.write(responseByteBuffer);
        clientChannel.close();
    }
}
