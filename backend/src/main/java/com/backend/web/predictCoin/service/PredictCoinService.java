package com.backend.web.predictCoin.service;

import com.backend.common.model.CustomException;
import com.backend.web.predictCoin.dto.PredictCoinDTO;
import com.backend.web.predictCoin.entity.PredictCoin;
import com.backend.web.predictCoin.repository.PredictCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictCoinService {
    private final PredictCoinRepository predictCoinRepository;

    @Transactional(readOnly = true)
    public PredictCoinDTO.CoinList findAll() throws CustomException {
        List<PredictCoin> list = predictCoinRepository.findAll();
        PredictCoinDTO.CoinList coinList = new PredictCoinDTO.CoinList();
        coinList.setList(list.stream().map((coin) -> coin.toPredictCoinBasicDTO()).collect(Collectors.toList()));
        return coinList;
    }
}
