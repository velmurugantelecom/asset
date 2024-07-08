package com.asset.repositories;

import com.asset.entity.InstrumentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstrumentHistoryRepository extends JpaRepository<InstrumentHistory, Long> {
	
}
