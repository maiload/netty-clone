package org.example.netty.http;

public class RequestLine {
    private final String method;
    private final String urlPath;
    private QueryStrings queryStrings;
    public RequestLine(String firstLine) {
        String[] tokens = firstLine.split(" ");
        this.method = tokens[0];
        String[] urlTokens = tokens[1].split("\\?");
        this.urlPath = urlTokens[0];
        if(urlTokens.length == 2){
            this.queryStrings = new QueryStrings(urlTokens[1]);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public QueryStrings getQueryStrings() {
        return queryStrings;
    }
}
