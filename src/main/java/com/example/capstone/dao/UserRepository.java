package com.example.capstone.dao;

import com.example.capstone.model.User;

// DIP: Controllers depend on this interface, not concrete DAO classes
public interface UserRepository {
    boolean register(User user);
    User login(String email, String password);
}