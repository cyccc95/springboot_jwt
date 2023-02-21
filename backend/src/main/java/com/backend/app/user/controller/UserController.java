package com.backend.app.user.controller;

import com.backend.app.user.entity.User;
import com.backend.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        return new ResponseEntity<>(userService.signUp(user), HttpStatus.OK);
    }
}
