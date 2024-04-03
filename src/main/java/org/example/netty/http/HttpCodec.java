package org.example.netty.http;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HttpCodec {

    public HttpRequest decode(final ByteBuffer requestByteBuffer){
        requestByteBuffer.flip();
        return new HttpRequest(requestByteBuffer);
    }

    public ByteBuffer encode(String msg) {
        return new HttpResponse(msg).toByteBuffer();
    }
}
