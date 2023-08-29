package com.genesys.weatherapp.dao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.genesys.weatherapp.dtos.StatisticMetricDto;
import com.genesys.weatherapp.entities.Metric;




public interface MetricDAO extends JpaRepository<Metric, Long> {
	
	List<Metric> findAllBySensorIdInAndDateCreationBetween(List<Long> sensorIds, LocalDate startDate, LocalDate endDate);
	
	List<Metric> findAllByDateCreationBetween(LocalDate startDate, LocalDate endDate);
	
}