package com.backend.web.auth.controller;

import com.backend.web.auth.dto.AuthDTO;
import com.backend.web.auth.service.AuthService;
import com.backend.common.model.ApiResponse;
import com.backend.common.model.CustomException;
import com.backend.common.util.ResponseMessageUtil;
import com.backend.config.security.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth")
    public ResponseEntity<ApiResponse> signIn(@RequestBody AuthDTO.Create signInInfo) {
        try {
            JwtAuthenticationResponse jwtAuthenticationResponse = authService.signIn(signInInfo);
            return ResponseMessageUtil.successMessage(jwtAuthenticationResponse);
        } catch (CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }

    @GetMapping("api/auth/accessToken")
    public ResponseEntity<ApiResponse> accessToken(HttpServletRequest request) {
        try {
            JwtAuthenticationResponse jwtAuthenticationResponse = authService.getNewAccessToken(request);
            return ResponseMessageUtil.successMessage(jwtAuthenticationResponse);
        } catch(CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch(Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }

    @GetMapping("api/auth/refreshToken")
    public ResponseEntity<ApiResponse> refreshToken(HttpServletRequest request) {
        try {
            JwtAuthenticationResponse jwtAuthenticationResponse = authService.refreshToken(request);
            return ResponseMessageUtil.successMessage(jwtAuthenticationResponse);
        } catch(CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch(Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }
}
