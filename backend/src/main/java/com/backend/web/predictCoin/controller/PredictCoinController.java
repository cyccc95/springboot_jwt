package com.backend.web.predictCoin.controller;

import com.backend.common.model.ApiResponse;
import com.backend.common.model.CustomException;
import com.backend.common.util.ResponseMessageUtil;
import com.backend.web.predictCoin.service.PredictCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PredictCoinController {
    private final PredictCoinService predictCoinService;

    @GetMapping("/api/predictCoins")
    public ResponseEntity<ApiResponse> findAll() {
        try {
            return ResponseMessageUtil.successMessage(predictCoinService.findAll());
        } catch (CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }
}
