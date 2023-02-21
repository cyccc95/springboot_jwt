package com.backend.app.historyCoin.repository;

import com.backend.app.historyCoin.entity.HistoryCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryCoinRepository extends JpaRepository<HistoryCoin, Long> {
}
