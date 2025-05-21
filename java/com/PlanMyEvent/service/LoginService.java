package com.PlanMyEvent.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.util.PasswordUtil;

public class LoginService {

    private Connection dbConn;

    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public UserModel authenticate(UserModel loginAttempt) {
        String query = "SELECT * FROM User WHERE Username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, loginAttempt.getUsername());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbUsername = rs.getString("Username");
                String encryptedPass = rs.getString("Password");
                String decryptedPass = PasswordUtil.decrypt(encryptedPass, dbUsername);

                if (decryptedPass != null && decryptedPass.equals(loginAttempt.getPassword())) {
                    UserModel user = new UserModel();
                    user.setUserId(rs.getInt("UserId"));
                    user.setUsername(dbUsername);
                    user.setEmail(rs.getString("Email"));
                    user.setFullName(rs.getString("FullName"));
                    user.setContact(rs.getString("Contact"));
                    user.setUserRole(rs.getString("UserRole"));
                    user.setImagePath(rs.getString("image_path")); // ✅ ADDED
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserModel getUserByUsername(String username) {
        String query = "SELECT * FROM User WHERE Username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserModel user = new UserModel();
                user.setUserId(rs.getInt("UserId"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setFullName(rs.getString("FullName"));
                user.setContact(rs.getString("Contact"));
                user.setUserRole(rs.getString("UserRole"));
                user.setImagePath(rs.getString("image_path")); // ✅ ADDED HERE TOO
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
