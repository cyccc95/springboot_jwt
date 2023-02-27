package com.backend.web.historyCoin.entity;

import com.backend.web.historyCoin.dto.HistoryCoinDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "historycoin")
public class HistoryCoin {
    @Id
    private Long idx;
    private Double opening_price;
    private Double high_price;
    private Double low_price;
    private Double trade_price;
    private Double candle_acc_trade_volume;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime candle_date_time_kst;

    @Builder
    public HistoryCoin(
            Long idx,
            Double opening_price,
            Double high_price,
            Double low_price,
            Double trade_price,
            Double candle_acc_trade_volume,
            LocalDateTime candle_date_time_kst
            ) {
        this.idx = idx;
        this.opening_price = opening_price;
        this.high_price = high_price;
        this.low_price = low_price;
        this.trade_price = trade_price;
        this.candle_acc_trade_volume = candle_acc_trade_volume;
        this.candle_date_time_kst = candle_date_time_kst;
    }

    public HistoryCoinDTO.Basic toHistoryCoinBasicDTO(){
        return new ModelMapper().map(this, HistoryCoinDTO.Basic.class);
    }
}
