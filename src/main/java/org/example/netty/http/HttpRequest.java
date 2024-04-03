package org.example.netty.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HttpRequest {
    private final Logger log = LoggerFactory.getLogger(HttpRequest.class);
    private final RequestLine requestLine;
    private Object header;
    private Object body;

    /**
     * GET /netty?name=jin HTTP/1.1
     * Host: localhost:8080
     * Connection: Keep-Alive
     * User-Agent: Apache-HttpClient/4.5.14 (Java/17.0.10)
     * Accept-Encoding: br,deflate,gzip,x-gzip
     */
    public HttpRequest(ByteBuffer httpByteBuffer) {
        String httpRequest = StandardCharsets.UTF_8.decode(httpByteBuffer).toString().trim();
//        log.debug("httpRequest : {}", httpRequest);
        String firstLine = httpRequest.split("\\r?\\n")[0];
        requestLine = new RequestLine(firstLine);
    }

    public String getMethod(){
        return requestLine.getMethod();
    }

    public String getUrlPath(){
        return requestLine.getUrlPath();
    }

    public QueryStrings getQueryStrings(){
        return requestLine.getQueryStrings();
    }
}
