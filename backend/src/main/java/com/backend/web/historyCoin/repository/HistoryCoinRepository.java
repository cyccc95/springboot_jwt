package com.backend.web.historyCoin.repository;

import com.backend.web.historyCoin.entity.HistoryCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryCoinRepository extends JpaRepository<HistoryCoin, Long> {
}
