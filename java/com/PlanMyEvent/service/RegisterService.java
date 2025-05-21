package com.PlanMyEvent.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.UserModel;

/**
 * RegisterService handles the registration of new users. It manages database
 * interactions for user registration.
 */
public class RegisterService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public RegisterService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Registers a new user in the database.
     *
     * @param userModel the user details to be registered
     * @return Boolean indicating the success of the operation
     */
    public Boolean addUser(UserModel userModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return false;
        }

        String insertQuery = "INSERT INTO User (UserRole, Username, FullName, Email, Contact, Password) "
                           + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, userModel.getUserRole());
            insertStmt.setString(2, userModel.getUsername());
            insertStmt.setString(3, userModel.getFullName());
            insertStmt.setString(4, userModel.getEmail());
            insertStmt.setString(5, userModel.getContact());
            insertStmt.setString(6, userModel.getPassword());

            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during user registration: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
