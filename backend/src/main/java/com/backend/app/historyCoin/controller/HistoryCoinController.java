package com.backend.app.historyCoin.controller;

import com.backend.app.historyCoin.service.HistoryCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HistoryCoinController {

    private final HistoryCoinService historyCoinService;

    @GetMapping("/api/coin/{idx}")
    public ResponseEntity<?> findByIdx(@PathVariable Long idx){
        return new ResponseEntity<>(historyCoinService.findByIdx(idx), HttpStatus.OK);
    }
}
