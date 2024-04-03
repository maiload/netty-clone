package org.example.netty.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
    private final List<QueryString> queryStrings = new ArrayList<>();

    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");
        Arrays.stream(queryStringTokens).forEach(token -> {
            queryStrings.add(new QueryString(token));
        });
    }

    public String getValue(String key){
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exist(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "QueryStrings{" + queryStrings + '}';
    }
}
