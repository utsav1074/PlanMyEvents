package com.PlanMyEvent.model;

import java.time.LocalDate;

public class BookingModel {
    private int bookingId;
    private LocalDate bookingDate;
    private int totalGuests;
    private double totalAmount;

    public BookingModel() {}

    public BookingModel(LocalDate bookingDate, int totalGuests, double totalAmount) {
        this.bookingDate = bookingDate;
        this.totalGuests = totalGuests;
        this.totalAmount = totalAmount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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
}
