package org.example.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements EventHandler{
    private final Logger log = LoggerFactory.getLogger(Acceptor.class);
    private final Selector selector;
    private final EventHandler tcpHandler;
    private final EventHandler httpHandler;
    public Acceptor(Selector selector) {
        this.selector = selector;
        tcpHandler = new TcpHandler();
        httpHandler = new HttpHandler();
    }

    @Override
    public void handle(SelectionKey key) throws IOException{
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
        clientChannel.configureBlocking(false);

//        clientChannel.register(selector, SelectionKey.OP_READ).attach(tcpHandler);
        clientChannel.register(selector, SelectionKey.OP_READ).attach(httpHandler);
    }

}
