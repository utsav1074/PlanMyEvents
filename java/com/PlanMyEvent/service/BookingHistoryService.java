package com.PlanMyEvent.service;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.BookingHistoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryService {
    public List<BookingHistoryModel> getUserBookingHistory(int userId) {
        List<BookingHistoryModel> historyList = new ArrayList<>();

        String sql = """
            SELECT 
                b.BookingId,
                v.VenueName,
                f.FoodSelected,
                d.DecorationSelected,
                b.TotalGuests,
                b.TotalAmount,
                b.BookingDate,
                ub.BookingStatus
            FROM UserVenueFoodDecorationBooking ub
            JOIN Venue v ON ub.VenueId = v.VenueId
            LEFT JOIN Food f ON ub.FoodId = f.FoodId
            LEFT JOIN Decoration d ON ub.DecorationId = d.DecorationId
            JOIN Booking b ON ub.BookingId = b.BookingId
            WHERE ub.UserId = ?
            ORDER BY b.BookingId DESC
        """;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookingHistoryModel history = new BookingHistoryModel();
                history.setBookingId(rs.getInt("BookingId"));
                history.setVenueName(rs.getString("VenueName"));
                history.setFoodSelected(rs.getString("FoodSelected"));
                history.setDecorationSelected(rs.getString("DecorationSelected"));
                history.setTotalGuests(rs.getInt("TotalGuests"));
                history.setTotalAmount(rs.getDouble("TotalAmount"));
                history.setBookingDate(rs.getDate("BookingDate").toLocalDate());
                history.setBookingStatus(rs.getString("BookingStatus"));

                historyList.add(history);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}
