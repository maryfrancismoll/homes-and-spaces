package com.model;

public class BookingModel {

    private Integer spaceId;
    private Long userId;
    private String from;
    private String to;

    public BookingModel(Integer spaceId, Long userId, String from, String to) {
        this.spaceId = spaceId;
        this.userId = userId;
        this.from = from;
        this.to = to;
    }

    public BookingModel() {
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
