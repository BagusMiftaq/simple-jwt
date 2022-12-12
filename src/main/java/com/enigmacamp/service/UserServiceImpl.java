package com.enigmacamp.service;

import com.enigmacamp.exception.UnauthorizedException;
import com.enigmacamp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final List<String> tokenStorage = new ArrayList<>();
    @Autowired
    GenerateTokenService generateTokenService;

    @Autowired
    ValidateTokenService validateTokenService;
    @Override
    public String Login(User user) {
        if (user.getUserName().equals("user") && user.getPassword().equals("123")){
            String tokenize = generateTokenService.generateToken("bebas");
            tokenStorage.add(tokenize);
            return tokenize;
        } else {
            throw new UnauthorizedException("Invalid user name or password");
        }

    }

    @Override
    public boolean validateToken(String token) {
        String existingToken = null;
        for (String sToken : tokenStorage){
            if (sToken.equals(token)){
                existingToken = sToken;
                break;
            }
        }
        if (existingToken == null){
            throw new UnauthorizedException("Token is not exist");
        }

        if (validateTokenService.validateToken(existingToken)){
            return true;
        } else {
            throw new UnauthorizedException("Token invalid");
        }
    }

    @Override
    public boolean Logout(String token) {
        return tokenStorage.remove(token);
    }
}
