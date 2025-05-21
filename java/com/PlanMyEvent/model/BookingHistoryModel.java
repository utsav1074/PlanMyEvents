package com.PlanMyEvent.model;

import java.time.LocalDate;

public class BookingHistoryModel {
    private int bookingId;
    private String venueName;
    private String foodSelected;
    private String decorationSelected;
    private int totalGuests;
    private double totalAmount;
    private LocalDate bookingDate;
    private String bookingStatus;

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getFoodSelected() {
        return foodSelected;
    }
    public void setFoodSelected(String foodSelected) {
        this.foodSelected = foodSelected;
    }

    public String getDecorationSelected() {
        return decorationSelected;
    }
    public void setDecorationSelected(String decorationSelected) {
        this.decorationSelected = decorationSelected;
    }

    public int getTotalGuests() {
        return totalGuests;
    }
    public void setTotalGuests(int totalGuests) {
        this.totalGuests = totalGuests;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
