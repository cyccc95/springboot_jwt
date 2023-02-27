package com.backend.web.historyCoin.repository;

import com.backend.web.historyCoin.entity.HistoryCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryCoinRepository extends JpaRepository<HistoryCoin, Long> {

    @Query(value = "select * from historycoin limit 19952", nativeQuery = true)
    List<HistoryCoin> findAllHistoryCoins();
}
