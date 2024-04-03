package org.example.netty.http;

public class QueryString {
    private final String key;
    private final String value;
    public QueryString(String queryString) {
        String[] tokens = queryString.split("=");
        this.key = tokens[0];
        this.value = tokens[1];
    }

    public boolean exist(String key){
        return this.key.equals(key);
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return "QueryString{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
