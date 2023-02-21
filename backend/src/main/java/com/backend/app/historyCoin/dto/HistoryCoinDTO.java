package com.backend.app.historyCoin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

public class HistoryCoinDTO {
    @Data
    public static class Detail {
//        private Long idx;
        private Double opening_price;
        private Double high_price;
        private Double low_price;
        private Double trade_price;
        private Double candle_acc_trade_volume;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime candle_date_time;
    }
}
