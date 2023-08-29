package com.genesys.weatherapp.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * table of weather metrics.
 */
@Entity
@Table(name = "METRIC")
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Metric {

	/**
	 * primaryKey of the table.
	 */
	@Id
	@SequenceGenerator(name = "SEQ_METRIC", sequenceName = "SEQ_METRIC", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_METRIC")
	@Column(name = "MET_ID", nullable = false, scale = 10)
	private Long id;

	/**
	 * temperature (C).
	 */
	@Column(name = "MET_TEMPERATURE", nullable = false, precision = 10)
	private Double temperature;

	/**
	 * humidity (pourcentage %).
	 */
	@Column(name = "MET_HUMIDITY", nullable = false, precision = 10)
	private Double humidity;

	/**
	 * wind speed (m/s).
	 */
	@Column(name = "MET_WIND_SPEED", nullable = false, precision = 10)
	private Double windSpeed;

	/**
	 * precipitation (mm).
	 */
	@Column(name = "MET_PRECIPITATION", nullable = false, precision = 10)
	private Double precipitation;

	/**
	 * Date de création de l'objet.
	 */
	@Column(name = "MET_DATE_CREATION", nullable = true)
	@CreatedDate
	private LocalDate dateCreation;

	/**
	 * Association réciproque de Sensor.Metrics.
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = Sensor.class)
	@JoinColumn(name = "SEN_ID", referencedColumnName = "SEN_ID")
	private Sensor sensor;

	/**
	 * No arg constructor.
	 */
	public Metric() {
	}

	/**
	 * Getter for id.
	 *
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Getter for temperature.
	 *
	 */
	public Double getTemperature() {
		return this.temperature;
	}

	/**
	 * Getter for humidity.
	 *
	 */
	public Double getHumidity() {
		return this.humidity;
	}

	/**
	 * Getter for windSpeed.
	 *
	 */
	public Double getWindSpeed() {
		return this.windSpeed;
	}

	/**
	 * Getter for precipitation.
	 *
	 */
	public Double getPrecipitation() {
		return this.precipitation;
	}

	/**
	 * Getter for dateCreation.
	 *
	 */
	public LocalDate getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * Getter for sensor.
	 *
	 */
	public Sensor getSensor() {
		return this.sensor;
	}

	/**
	 * @param id value to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param temperature value to set
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param humidity value to set
	 */
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	/**
	 * @param windSpeed value to set
	 */
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * @param precipitation value to set
	 */
	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}

	/**
	 * @param dateCreation value to set
	 */
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @param sensor value to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}
