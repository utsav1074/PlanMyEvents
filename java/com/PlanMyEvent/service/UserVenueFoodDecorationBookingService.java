package com.PlanMyEvent.service;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.UserVenueFoodDecorationBookingModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserVenueFoodDecorationBookingService {

    private Connection dbConn;

    public UserVenueFoodDecorationBookingService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(UserVenueFoodDecorationBookingModel model) {
        String sql = "INSERT INTO UserVenueFoodDecorationBooking " +
                     "(UserId, VenueId, FoodId, DecorationId, BookingId, BookingStatus) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getVenueId());
            stmt.setInt(3, model.getFoodId());       // No more null checks; defaults to 0
            stmt.setInt(4, model.getDecorationId()); // Same here
            stmt.setInt(5, model.getBookingId());
            stmt.setString(6, model.getBookingStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
