package com.agivantChatBot.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Map;

public class RequestBodyEntity {

    @JsonView
    private Object intentInfo;

    @JsonView
    private String text;

    @Override
    public String toString() {
        return "RequestBodyEntity{" +
                "intentInfo=" + intentInfo +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getIntentInfo() {
        return intentInfo;
    }

    public void setIntentInfo(Object intentInfo) {
        this.intentInfo = intentInfo;
    }


}
