package com.shujia.bean;

public class Message {

    private String msg;  //success  error

    private String name; // 信息


    public Message(String msg, String name) {
        this.msg = msg;
        this.name = name;
    }

    public Message() {
    }


    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
