package com.enigmacamp.service;

import com.enigmacamp.model.User;

public interface UserService {
    String Login(User user);

    boolean validateToken(String token);
    boolean Logout(String token);
}
