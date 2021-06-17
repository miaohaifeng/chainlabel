package com.happy.video.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class KeyValue {
    private String key;
    private String value;


    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
