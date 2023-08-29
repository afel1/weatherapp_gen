package com.genesys.weatherapp.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.genesys.weatherapp.dtos.CreationMetricDto;
import com.genesys.weatherapp.dtos.CreationSensorDto;
import com.genesys.weatherapp.dtos.SensorDto;
import com.genesys.weatherapp.dtos.StatisticMetricDto;

import jakarta.validation.Valid;

public interface AppController {
	

	/**
	 * add a new sensor.
	 * @param creationSensorDto creationSensorDto
	 * @return Boolean to indicate if a new sensor is added
	 */
	@PostMapping(path = "createSensor")
	Boolean createSensor(@RequestBody @Valid CreationSensorDto creationSensorDto);


	/**
	 * record metrics of a sensor.
	 * @param creationMetricDto creationMetricDto
	 * @return Boolean to indicate if the metrics of the sensor is added
	 */
	@PostMapping(path = "recordMetrics")
	Boolean recordMetrics(@RequestBody @Valid CreationMetricDto creationMetricDto);
	
	/**
	 * get the sensors data by id list or all.
	 * @param sensorIds List of sensors ids
	 * @return list of sensors
	 */
	@GetMapping(path = "getSensorsList")
	List<SensorDto> getSensorsList(@RequestParam(value = "sensorIds", required = false) List<Long> sensorIds);
	

	/**
	 * get metrics (specific or all) average by sensors id list or all.
	 * @param metricsTypes List of metrics we want the average for
	 * @param sensorIds List of sensors ids we want average of their metrics
	 * @param statistic the statistic we're looking for (sum, min, max or average)
	 * @param start Date
	 * @param end Date
	 * @return StatisticMetricDto
	 */
	@GetMapping(path = "getMetricsData")
	StatisticMetricDto getMetricsData(@RequestParam(value = "metricsTypes", required = false) List<String> metricsTypes, 
			@RequestParam(value = "sensorIds", required = false) List<Long> sensorIds, 
			@RequestParam(value = "statistic", required = false) String statistic,
			@RequestParam(value = "startDate", required = false) LocalDate startDate,
		    @RequestParam(value = "endDate", required = false) LocalDate endDate);

	
}
