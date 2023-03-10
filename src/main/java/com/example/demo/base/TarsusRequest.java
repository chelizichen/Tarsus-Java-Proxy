package com.example.demo.base;
import java.util.LinkedHashMap;

public class TarsusRequest {
    protected LinkedHashMap<String, Object> Body = new LinkedHashMap<>();
    protected LinkedHashMap<String, Object> Data = new LinkedHashMap<>();

    public static TarsusRequest create(String interFace, String method) {
        return new TarsusRequest(interFace, method);
    }
    public TarsusRequest(String interFace, String method) {
        this.Body.put("interFace", interFace);
        this.Body.put("method", method);
    }
    public TarsusRequest assemble(String key, Object value) {
        this.Data.put(key, value);
        return this;
    }
    public LinkedHashMap<String, Object> payload() {
        this.Body.put("data", this.Data);
        return this.Body;
    }
}
