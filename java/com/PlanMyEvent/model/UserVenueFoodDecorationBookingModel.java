package com.PlanMyEvent.model;

public class UserVenueFoodDecorationBookingModel {
    private int userId;
    private int venueId;
    private int foodId;
    private int decorationId;
    private int bookingId;
    private String bookingStatus;

    public UserVenueFoodDecorationBookingModel() {}

    public UserVenueFoodDecorationBookingModel(int userId, int venueId, int foodId, int decorationId, int bookingId, String bookingStatus) {
        this.userId = userId;
        this.venueId = venueId;
        this.foodId = foodId;
        this.decorationId = decorationId;
        this.bookingId = bookingId;
        this.bookingStatus = bookingStatus;
    }

    public int getUserId() {
        return userId;
    }

    public int getVenueId() {
        return venueId;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getDecorationId() {
        return decorationId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setDecorationId(int decorationId) {
        this.decorationId = decorationId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
