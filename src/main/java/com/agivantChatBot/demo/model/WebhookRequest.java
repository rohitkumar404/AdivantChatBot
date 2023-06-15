package com.agivantChatBot.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookRequest {

    @JsonProperty("intentInfo")
    private IntentInfo intentInfo;

    public IntentInfo getIntentInfo() {
        return intentInfo;
    }

    public void setIntentInfo(IntentInfo intentInfo) {
        this.intentInfo = intentInfo;
    }

    // Getters and setters

    // Optional: Define other properties if required
}
