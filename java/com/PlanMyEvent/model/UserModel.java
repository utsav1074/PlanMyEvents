package com.PlanMyEvent.model;

public class UserModel {
    private int userId;
    private String userRole;
    private String username;
    private String fullName;
    private String email;
    private String contact;
    private String password;
    private String imagePath;  // ✅ Used for profile picture path

    public UserModel() {}

    public UserModel(int userId, String userRole, String username, String fullName,
                     String email, String contact, String password, String imagePath) {
        this.userId = userId;
        this.userRole = userRole;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.imagePath = imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ Correct imagePath getter/setter
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
