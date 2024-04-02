package org.example.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventLoop{
    private final Logger log = LoggerFactory.getLogger(EventLoop.class);
    private final Selector selector;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public EventLoop(int port) throws IOException {
        selector = Selector.open();
        var serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT).attach(new Acceptor(selector));
    }

    public void run() {
        executorService.submit(() -> {
            log.info("Start EventLoop");
            while (true) {
                selector.select();
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                while (selectionKeys.hasNext()){
                    SelectionKey key = selectionKeys.next();
                    selectionKeys.remove();

                    dispatch(key);
                }

            }
        });
    }

    private void dispatch(SelectionKey key) throws IOException {
        EventHandler eventHandler = (EventHandler) key.attachment();

        if(key.isAcceptable() || key.isReadable()){
            eventHandler.handle(key);
        }
    }
}
