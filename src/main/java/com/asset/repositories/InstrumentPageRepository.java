package com.asset.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.asset.entity.Instrument;

public interface InstrumentPageRepository extends PagingAndSortingRepository<Instrument, Long> {
	

}
