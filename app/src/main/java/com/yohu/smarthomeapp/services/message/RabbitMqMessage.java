package com.yohu.smarthomeapp.services.message;

public class RabbitMqMessage {

    private Integer msgType;

    private String content;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
