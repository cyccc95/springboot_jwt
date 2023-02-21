package com.backend.app.historyCoin.service;

import com.backend.app.historyCoin.dto.HistoryCoinDTO;
import com.backend.app.historyCoin.repository.HistoryCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HistoryCoinService {

    private final RestTemplate restTemplate;
    private final HistoryCoinRepository historyCoinRepository;

//    @Scheduled(fixedDelay = 1000)
//    public void fetchCandleData() {
//        String url = "https://api.upbit.com/v1/candles/minutes/30?market=KRW-BTC&count=1";
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        System.out.println(response.getBody());
//    }

    @Transactional(readOnly = true)
    public HistoryCoinDTO.Detail findByIdx(Long idx) {
        String url = "https://api.upbit.com/v1/candles/minutes/60?market=KRW-BTC&count=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return historyCoinRepository.findById(idx).get().toHistoryCoinDTO();
    }
}
