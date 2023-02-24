package com.backend.web.auth.controller;

import com.backend.common.model.ApiResponse;
import com.backend.common.model.CustomException;
import com.backend.common.util.ResponseMessageUtil;
import com.backend.web.auth.dto.TokenDTO;
import com.backend.web.auth.dto.TokenRequestDTO;
import com.backend.web.auth.service.AuthService;
import com.backend.web.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/signIn")
    public ResponseEntity<ApiResponse> signIn(@RequestBody MemberDTO.SignIn signInInfo) {
        try {
            TokenDTO token = authService.signIn(signInInfo);
            return ResponseMessageUtil.successMessage(token);
//        } catch (CustomException ce) {
//            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }

    @PostMapping("/api/auth/refresh")
    public ResponseEntity<ApiResponse> refresh(@RequestBody TokenRequestDTO tokenRequestDTO) {
        try {
            TokenDTO token = authService.refresh(tokenRequestDTO);
            return ResponseMessageUtil.successMessage(token);
//        } catch (CustomException ce) {
//            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }
}
