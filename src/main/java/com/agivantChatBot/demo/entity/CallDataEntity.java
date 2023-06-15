package com.agivantChatBot.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "calldata", schema = "support-db")
public class CallDataEntity {

    @Column(name = "Call Center")
    private String callCenter;

    @Column(name = "Channel")
    private String channel;

    @Column(name = "City")
    private String city;

    @Column(name = "Customer Name")
    private String customerName;

    @Column(name = "Reason")
    private String reason;

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Response Time")
    private String responseTime;

    @Column(name = "Sentiment")
    private String sentiment;

    @Column(name = "State")
    private String state;

    @Column(name = "Average Call Duration")
    private int averageCallDuration;

    @Column(name = "Average Customer Satisfaction")
    private int averageCustomerSatisfaction;

    @Column(name = "Call Duration")
    private int callDuration;

    @Column(name = "Csat Score")
    private int csatScore;

    @Column(name = "sat rate")
    private double satRate;

    @Column(name = "Total Calls Received")
    private int totalCallsReceived;

    public String getCallCenter() {
        return callCenter;
    }

    public void setCallCenter(String callCenter) {
        this.callCenter = callCenter;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAverageCallDuration() {
        return averageCallDuration;
    }

    public void setAverageCallDuration(int averageCallDuration) {
        this.averageCallDuration = averageCallDuration;
    }

    public int getAverageCustomerSatisfaction() {
        return averageCustomerSatisfaction;
    }

    public void setAverageCustomerSatisfaction(int averageCustomerSatisfaction) {
        this.averageCustomerSatisfaction = averageCustomerSatisfaction;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public int getCsatScore() {
        return csatScore;
    }

    public void setCsatScore(int csatScore) {
        this.csatScore = csatScore;
    }

    public double getSatRate() {
        return satRate;
    }

    public void setSatRate(double satRate) {
        this.satRate = satRate;
    }

    public int getTotalCallsReceived() {
        return totalCallsReceived;
    }

    public void setTotalCallsReceived(int totalCallsReceived) {
        this.totalCallsReceived = totalCallsReceived;
    }

    @Override
    public String toString() {
        return "CallDataEntity{" +
                "callCenter='" + callCenter + '\'' +
                ", channel='" + channel + '\'' +
                ", city='" + city + '\'' +
                ", customerName='" + customerName + '\'' +
                ", reason='" + reason + '\'' +
                ", id='" + id + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", sentiment='" + sentiment + '\'' +
                ", state='" + state + '\'' +
                ", averageCallDuration=" + averageCallDuration +
                ", averageCustomerSatisfaction=" + averageCustomerSatisfaction +
                ", callDuration=" + callDuration +
                ", csatScore=" + csatScore +
                ", satRate=" + satRate +
                ", totalCallsReceived=" + totalCallsReceived +
                '}';
    }
}
