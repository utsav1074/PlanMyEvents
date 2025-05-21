// BookingAdminService.java
package com.PlanMyEvent.service;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.BookingAdminModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingAdminService {

    public List<BookingAdminModel> getAllBookings() {
        List<BookingAdminModel> bookings = new ArrayList<>();

        String sql = """
            SELECT 
                b.BookingId,
                u.FullName,
                v.VenueName,
                b.BookingDate,
                f.FoodSelected,
                d.DecorationSelected,
                b.TotalGuests,
                b.TotalAmount,
                uv.BookingStatus
            FROM UserVenueFoodDecorationBooking uv
            JOIN Booking b ON uv.BookingId = b.BookingId
            JOIN User u ON uv.UserId = u.UserId
            JOIN Venue v ON uv.VenueId = v.VenueId
            LEFT JOIN Food f ON uv.FoodId = f.FoodId
            LEFT JOIN Decoration d ON uv.DecorationId = d.DecorationId
            ORDER BY b.BookingId DESC
        """;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookingAdminModel booking = new BookingAdminModel();
                booking.setBookingId(rs.getInt("BookingId"));
                booking.setFullName(rs.getString("FullName"));
                booking.setVenueName(rs.getString("VenueName"));
                booking.setBookingDate(rs.getDate("BookingDate").toLocalDate());
                booking.setFoodSelected(rs.getString("FoodSelected"));
                booking.setDecorationSelected(rs.getString("DecorationSelected"));
                booking.setTotalGuests(rs.getInt("TotalGuests"));
                booking.setTotalAmount(rs.getDouble("TotalAmount"));
                booking.setBookingStatus(rs.getString("BookingStatus"));
                bookings.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public double getTotalRevenueFromBooked() {
        double total = 0;
        String sql = """
            SELECT SUM(b.TotalAmount) AS totalRevenue
            FROM UserVenueFoodDecorationBooking uv
            JOIN Booking b ON uv.BookingId = b.BookingId
            WHERE uv.BookingStatus = 'Booked'
        """;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("totalRevenue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public int getTotalUsers() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS userCount FROM User";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("userCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalBookedVenues() {
        int count = 0;
        String sql = """
            SELECT COUNT(DISTINCT VenueId) AS venueCount
            FROM UserVenueFoodDecorationBooking
            WHERE BookingStatus = 'Booked'
        """;
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("venueCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}