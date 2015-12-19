package com.wang.yan.mvc.dao;

import org.springframework.stereotype.Component;

/**
 * Created by ywang on 19/12/15.
 */
@Component
public class Message {
    private String content;

    public Message() {
        this.content = "Yan";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
