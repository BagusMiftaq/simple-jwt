package com.enigmacamp.controller;

import com.enigmacamp.model.User;
import com.enigmacamp.service.UserService;
import com.enigmacamp.service.ValidateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(UrlMapping.AUTH_URL)
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    ValidateTokenService validateTokenService;
    @PostMapping
    public ResponseEntity auth(@RequestBody User user){
            return ResponseEntity.ok(userService.Login(user));
    }

    @GetMapping(UrlMapping.VAL_URL)
    public ResponseEntity validation(@RequestParam String token){
       if(userService.validateToken(token)){
           return ResponseEntity.ok("Token is valid");
       } else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
       }

    }

    @GetMapping(UrlMapping.OUT_URL)
    public ResponseEntity logout(@RequestParam String token){
        if(userService.Logout(token)){
            return ResponseEntity.ok("Succes logout");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

    }

}
