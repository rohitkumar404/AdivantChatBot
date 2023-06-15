package com.agivantChatBot.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class IntentInfo {

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("parameters")
    private Map<String, Map<String,String>> parameters;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Map<String, Map<String,String>> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Map<String,String>> parameters) {
        this.parameters = parameters;
    }


    // Optional: Define other properties if required
}