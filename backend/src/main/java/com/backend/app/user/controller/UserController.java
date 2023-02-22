package com.backend.app.user.controller;

import com.backend.app.user.dto.UserDTO;
import com.backend.app.user.service.UserService;
import com.backend.common.model.ApiResponse;
import com.backend.common.model.CustomException;
import com.backend.common.util.ResponseMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/app/user")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserDTO.Create userInfo) {
        try {
            userService.createUser(userInfo);
            return ResponseMessageUtil.successMessage();
        } catch (CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }

}
