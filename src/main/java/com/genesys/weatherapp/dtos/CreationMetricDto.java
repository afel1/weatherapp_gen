package com.genesys.weatherapp.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

/**
 * New Metric Creation Object.
 */
public class CreationMetricDto implements Serializable {
	/** Serial ID */
	private static final long serialVersionUID = 1L;

	/**
	 * primaryKey of the table.
	 */
	private Long id;

	/**
	 * temperature (C).
	 */
	@NotNull
	private Double temperature;

	/**
	 * humidity (pourcentage %).
	 */
	@NotNull
	private Double humidity;

	/**
	 * wind speed (m/s).
	 */
	@NotNull
	private Double windSpeed;

	/**
	 * precipitation (mm).
	 */
	@NotNull
	private Double precipitation;

	/**
	 * Date de création de l'objet.
	 */
	@PastOrPresent
	private LocalDate dateCreation;

	/**
	 * primaryKey of the table.
	 */
	@NotNull
	private Long sensorId;

	/**
	 * No arg constructor.
	 */
	public CreationMetricDto() {
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
	 * Getter for sensorId.
	 *
	 */
	public Long getSensorId() {
		return this.sensorId;
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
	 * @param sensorId value to set
	 */
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
}
