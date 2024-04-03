package org.example.netty.http;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private final String statusLine;
    private final String header;
    private final String body;
    public HttpResponse(String msg) {
        this.statusLine = "HTTP/1.1 200 OK\n";
        this.body = "<html><body><h1>" + msg + "</h1></body></html>";
        int contentLength = this.body.getBytes().length;
        this.header = "Content-Type: text/html; charset=utf-8\n" +
                "Content-Length: " +contentLength + "\n\n";
    }

    public ByteBuffer toByteBuffer() {
        String httpResponse = statusLine + header + body;
        return StandardCharsets.UTF_8.encode(httpResponse);
    }
}
