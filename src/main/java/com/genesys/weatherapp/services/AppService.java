package com.genesys.weatherapp.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.weatherapp.dao.MetricDAO;
import com.genesys.weatherapp.dao.SensorDAO;
import com.genesys.weatherapp.dtos.CreationMetricDto;
import com.genesys.weatherapp.dtos.CreationSensorDto;
import com.genesys.weatherapp.dtos.SensorDto;
import com.genesys.weatherapp.dtos.StatisticMetricDto;
import com.genesys.weatherapp.entities.Metric;
import com.genesys.weatherapp.entities.Sensor;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppService {
	
	private final SensorDAO sensorDAO;
	private final MetricDAO metricDAO;
	
	/**
	 * @param creationMetricDto
	 * @return
	 */
	public Boolean recordMetrics(CreationMetricDto creationMetricDto) {
		Metric newMetric = new Metric();
		Sensor linkedSensor = sensorDAO.findById(creationMetricDto.getSensorId()).orElseThrow();
		newMetric.setTemperature(creationMetricDto.getTemperature());
		newMetric.setHumidity(creationMetricDto.getHumidity());
		newMetric.setWindSpeed(creationMetricDto.getWindSpeed());
		newMetric.setPrecipitation(creationMetricDto.getPrecipitation());
		newMetric.setDateCreation(LocalDate.now());
		newMetric.setSensor(linkedSensor);
		metricDAO.save(newMetric);
		return true;
	}
	
	/**
	 * @param creationSensorDto
	 * @return
	 */
	public Boolean createSensor(@Valid CreationSensorDto creationSensorDto) {
		Sensor newSensor = new Sensor();
		newSensor.setName(creationSensorDto.getName());
		newSensor.setLocation(creationSensorDto.getLocation());
		newSensor.setDateCreation(LocalDate.now());
		sensorDAO.save(newSensor);
		return true;
	}
	
	/**
	 * @param sensorIds
	 * @return
	 */
	public List<SensorDto> getSensorsData(List<Long> sensorIds) {
		List<Sensor> sensors;
		if(sensorIds == null) {
			sensors = sensorDAO.findAll();
		} else {
			sensors = sensorDAO.findAllByIdIn(sensorIds);
		}
		return sensors.stream().map(this::mapSensorToDto).toList();
	}
	
	/**
	 * @param sensor
	 * @return
	 */
	private SensorDto mapSensorToDto(Sensor sensor) {
		SensorDto dto = new SensorDto();
		dto.setId(sensor.getId());
		dto.setName(sensor.getName());
		dto.setLocation(sensor.getLocation());
		dto.setDateCreation(sensor.getDateCreation());
		return dto;
	}
	
	
	/**
	 * @param specifiedMetrics
	 * @param sensorIds
	 * @param statistic
	 * @return
	 */
	public StatisticMetricDto getMetricsData(List<String> specifiedMetrics, List<Long> sensorIds, String statistic, LocalDate startDate, LocalDate endDate) {
		// We check of the params are valid or not
		paramsValidation(specifiedMetrics, startDate, endDate);
		
		// If no dates are specified we get the statistic of the last month metrics data
		LocalDate defaultStartDate = LocalDate.now().minusDays(30);
        if (startDate == null) {
            startDate = defaultStartDate;
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        if (!isDateRangeValid(startDate, endDate)) {
            throw new IllegalArgumentException("Date range must be between 1 day and 1 month.");
        }
        
	    List<Metric> metrics = (sensorIds == null) ? metricDAO.findAllByDateCreationBetween(startDate,endDate) 
	    		: metricDAO.findAllBySensorIdInAndDateCreationBetween(sensorIds,startDate,endDate);
	    return createStatisticMetricDto(specifiedMetrics, metrics, statistic);
	}
	
	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private boolean isDateRangeValid(LocalDate startDate, LocalDate endDate) {
		// Calculate the number of days between start and end dates
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // Check if the number of days is between 1 and 30 (1 month)
        return daysBetween >= 1 && daysBetween <= 30;
    }

	/**
	 * @param specifiedMetrics
	 * @param metrics
	 * @param statistic
	 * @return
	 */
	private StatisticMetricDto createStatisticMetricDto(List<String> specifiedMetrics, List<Metric> metrics, String statistic) {
	    StatisticMetricDto dto = new StatisticMetricDto();
	    if (specifiedMetrics == null || specifiedMetrics.contains("TEM")) {
	        dto.setTemperature(calculateStatistic(metrics.stream().map(Metric::getTemperature).toList(), statistic));
	    }
	    if (specifiedMetrics == null || specifiedMetrics.contains("HUM")) {
	        dto.setHumidity(calculateStatistic(metrics.stream().map(Metric::getHumidity).toList(), statistic));
	    }
	    if (specifiedMetrics == null || specifiedMetrics.contains("PRE")) {
	        dto.setPrecipitation(calculateStatistic(metrics.stream().map(Metric::getPrecipitation).toList(), statistic));
	    }
	    if (specifiedMetrics == null || specifiedMetrics.contains("WIN")) {
	        dto.setWindSpeed(calculateStatistic(metrics.stream().map(Metric::getWindSpeed).toList(), statistic));
	    }
	    return dto;
	}

	/**
	 * @param numbers
	 * @param statistic
	 * @return
	 */
	private Double calculateStatistic(List<Double> numbers, String statistic) {
		if(statistic == null) {
			statistic = "AVG";
		}
	    switch (statistic) {
	        case "SUM":
	            return numbers.stream().mapToDouble(Double::doubleValue).sum();
	        case "MIN":
	            return numbers.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
	        case "MAX":
	            return numbers.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
	        default:
	            return numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
	    }
	}
	
	private void paramsValidation(List<String> specifiedMetrics,LocalDate startDate, LocalDate endDate) {
		List<String> allowedMetrics = new ArrayList<>();
		allowedMetrics.add("TEM");
		allowedMetrics.add("HUM");
		allowedMetrics.add("PRE");
		allowedMetrics.add("WIN");
		if(specifiedMetrics != null) {
			for (String metric : specifiedMetrics) {
	            if (!allowedMetrics.contains(metric)) {
	                throw new IllegalArgumentException("The value " + metric + "is not accepted.");
	            }
			}
		}
		if (startDate != null && endDate != null && startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
	}

}
