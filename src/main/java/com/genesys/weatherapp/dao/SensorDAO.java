package com.genesys.weatherapp.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genesys.weatherapp.entities.Sensor;



public interface SensorDAO extends JpaRepository<Sensor, Long> {
	
	List<Sensor> findAllByIdIn(List<Long> sensorIds);

}