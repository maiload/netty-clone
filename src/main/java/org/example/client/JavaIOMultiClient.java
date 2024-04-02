package org.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaIOMultiClient{
    private static final Logger log = LoggerFactory.getLogger(JavaIOMultiClient.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(50);

    public static void run() {
        log.info("Start Client");

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        var start = System.currentTimeMillis();

        for(int i = 0; i < 1000; i++){
            var future = CompletableFuture.runAsync(() -> {
                try(Socket socket = new Socket()){
                    socket.connect(new InetSocketAddress("localhost", 8080));

                    var out = socket.getOutputStream();
                    String requestBody = "Hello, This is client";
                    out.write(requestBody.getBytes());
                    out.flush();

                    var in = socket.getInputStream();
                    var responseBytes = new byte[1024];
                    in.read(responseBytes);
                    log.info("Response : {}", new String(responseBytes).trim());
                }catch (Exception e){
                    log.error("ERROR", e);
                }
            }, executorService);

            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();
        log.info("End Client");
        var end = System.currentTimeMillis();
        log.info("Duration : {}", (end - start) / 1000.0);
    }

}
