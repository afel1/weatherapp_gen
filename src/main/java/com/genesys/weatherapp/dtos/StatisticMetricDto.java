package com.genesys.weatherapp.dtos;


import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

/**
 * Statistic Metric Object (average, min, max or sum).
 */
public class StatisticMetricDto implements Serializable {
	/** Serial ID */
	private static final long serialVersionUID = 1L;

	/**
	 * temperature (C).
	 */
	@NotNull
	private Double temperature;

	/**
	 * precipitation (mm).
	 */
	@NotNull
	private Double precipitation;

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
	 * No arg constructor.
	 */
	public StatisticMetricDto() {
	}

	/**
	 * Getter for temperature.
	 *
	 */
	public Double getTemperature() {
		return this.temperature;
	}

	/**
	 * Getter for precipitation.
	 *
	 */
	public Double getPrecipitation() {
		return this.precipitation;
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
	 * @param temperature value to set
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param precipitation value to set
	 */
	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
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
}
