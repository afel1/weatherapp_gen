package com.genesys.weatherapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.genesys.weatherapp.dao.MetricDAO;
import com.genesys.weatherapp.dao.SensorDAO;
import com.genesys.weatherapp.dtos.CreationMetricDto;
import com.genesys.weatherapp.dtos.CreationSensorDto;
import com.genesys.weatherapp.dtos.SensorDto;
import com.genesys.weatherapp.dtos.StatisticMetricDto;
import com.genesys.weatherapp.entities.Metric;
import com.genesys.weatherapp.entities.Sensor;
import com.genesys.weatherapp.services.AppService;



@ExtendWith(MockitoExtension.class)
class AppServiceTest {

	
	@Mock
	private SensorDAO sensorDAO;
	
	@Mock
	private MetricDAO metricDAO;

	private AppService appService;

	@BeforeEach
	public void init() {
		appService = new AppService(sensorDAO, metricDAO);
	}
	
	@Test
    void recordMetricsTest() {
        // GIVEN
		CreationMetricDto dto = new CreationMetricDto();
		dto.setTemperature(10d);
		dto.setHumidity(20d);
		dto.setWindSpeed(100d);
		dto.setPrecipitation(100d);
		dto.setSensorId(1000l);
		Sensor sensor = new Sensor();
        Mockito.doReturn(Optional.of(sensor)).when(sensorDAO).findById(1000l);

        // WHEN
        var res = appService.recordMetrics(dto);

        // THEN
        assertEquals(true, res);
        Mockito.verify(metricDAO).save(Mockito.any());
    }
	
	@Test
    void createSensorTest() {
        // GIVEN
		CreationSensorDto dto = new CreationSensorDto();
		dto.setName("Sensor 1");
		dto.setLocation("Brest");;
		
        // WHEN
        var res = appService.createSensor(dto);

        // THEN
        assertEquals(true, res);
        Mockito.verify(sensorDAO).save(Mockito.any());
    }
	
	@Test
    void getSensorsDataWithoutSensorIds_Test() {
        // GIVEN
		List<Sensor> sensors = new ArrayList<>();
		sensors.add(Sensor.builder().id(1l).name("Sensor 1").location("Paris").build());
		sensors.add(Sensor.builder().id(2l).name("Sensor 2").location("Brest").build());
		Mockito.doReturn(sensors).when(sensorDAO).findAll();
		
        // WHEN
		List<SensorDto> res = appService.getSensorsData(null);

        // THEN
        assertEquals(2, res.size());
        assertEquals("Paris", res.get(0).getLocation());
        Mockito.verify(sensorDAO).findAll();
    }
	
	@Test
    void getMetricsData_Sum_Test() {
        // GIVEN
		List<Metric> metrics = new ArrayList<>();
		metrics.add(Metric.builder().id(1l).temperature(20d).humidity(80d).build());
		metrics.add(Metric.builder().id(2l).temperature(30d).humidity(20d).build());
		List<String> specifiedMetrics = new ArrayList<>();
		specifiedMetrics.add("TEM");
		specifiedMetrics.add("HUM");
		String statisticType = "SUM";
		Mockito.doReturn(metrics).when(metricDAO).findAllByDateCreationBetween(Mockito.any(), Mockito.any());
		
		// WHEN
		StatisticMetricDto res = appService.getMetricsData(specifiedMetrics, null, statisticType, null, null );
		
		// THEN
		Mockito.verify(metricDAO).findAllByDateCreationBetween(Mockito.any(), Mockito.any());
		assertEquals(100d, res.getHumidity());
		assertEquals(50d, res.getTemperature());
    }

	

	

}
