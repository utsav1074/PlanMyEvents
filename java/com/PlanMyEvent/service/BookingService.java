package com.PlanMyEvent.service;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.BookingModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * BookingService handles all booking-related operations.
 */
public class BookingService {

    private Connection dbConn;

    public BookingService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public int createBooking(BookingModel booking) {
        String sql = "INSERT INTO Booking (BookingDate, TotalGuests, TotalAmount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(booking.getBookingDate()));
            stmt.setInt(2, booking.getTotalGuests());
            stmt.setDouble(3, booking.getTotalAmount());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getVenueCostById(int venueId) {
        String sql = "SELECT PricePerDay FROM Venue WHERE VenueId = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, venueId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("PricePerDay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getFoodCostById(int foodId) {
        String sql = "SELECT FoodCost FROM Food WHERE FoodId = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, foodId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("FoodCost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getDecorationCostById(int decorId) {
        String sql = "SELECT DecorationCost FROM Decoration WHERE DecorationId = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, decorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("DecorationCost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Checks whether the venue is already booked on the given date
     * by any user with BookingStatus = 'Booked'.
     */
    public boolean isVenueBookedOnDate(int venueId, LocalDate bookingDate) {
        String sql = "SELECT COUNT(*) AS count " +
                     "FROM UserVenueFoodDecorationBooking uvfdb " +
                     "JOIN Booking b ON uvfdb.BookingId = b.BookingId " +
                     "WHERE uvfdb.VenueId = ? AND b.BookingDate = ? AND uvfdb.BookingStatus = 'Booked'";

        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, venueId);
            stmt.setDate(2, java.sql.Date.valueOf(bookingDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
