package com.PlanMyEvent.model;

import java.time.LocalDate;

public class BookingAdminModel {
    private int bookingId;
    private String fullName;
    private String venueName;
    private LocalDate bookingDate;
    private String foodSelected;
    private String decorationSelected;
    private int totalGuests;
    private double totalAmount;
    private String bookingStatus;

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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
    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
