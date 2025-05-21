package com.PlanMyEvent.service;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminUserService {

    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();

        String sql = """
            SELECT 
                UserId,
                FullName,
                Username,
                Email,
                Contact,
                UserRole,
                Password
            FROM User
            ORDER BY FullName ASC
        """;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUserId(rs.getInt("UserId"));
                user.setFullName(rs.getString("FullName"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setContact(rs.getString("Contact"));
                user.setUserRole(rs.getString("UserRole"));

                try {
                    // ✅ Correct parameter order
                    String decryptedPassword = PasswordUtil.decrypt(rs.getString("Password"), user.getUsername());
                    user.setPassword(decryptedPassword != null ? decryptedPassword : "Decryption Failed");
                } catch (Exception e) {
                    user.setPassword("Decryption Failed");
                    e.printStackTrace();
                }

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
