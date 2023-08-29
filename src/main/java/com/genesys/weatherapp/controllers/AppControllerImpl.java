package com.genesys.weatherapp.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.weatherapp.dtos.CreationMetricDto;
import com.genesys.weatherapp.dtos.CreationSensorDto;
import com.genesys.weatherapp.dtos.SensorDto;
import com.genesys.weatherapp.dtos.StatisticMetricDto;
import com.genesys.weatherapp.services.AppService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppControllerImpl implements AppController {
	
	private final AppService appService;
	
	@Override
	public Boolean recordMetrics(@Valid CreationMetricDto creationMetricDto) {
		return appService.recordMetrics(creationMetricDto);
	}

	@Override
	public Boolean createSensor(@Valid CreationSensorDto creationSensorDto) {
		return appService.createSensor(creationSensorDto);
	}

	@Override
	public List<SensorDto> getSensorsList(List<Long> sensorIds) {
		return appService.getSensorsData(sensorIds);
	}

	@Override
	public StatisticMetricDto getMetricsData(List<String> specifiedMetrics, List<Long> sensorIds, String statistic, LocalDate startDate, LocalDate endDate) {
		return appService.getMetricsData(specifiedMetrics, sensorIds, statistic, startDate, endDate);
	}

}
