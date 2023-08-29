package com.genesys.weatherapp.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

/**
 * Sensor data object.
 */
public class SensorDto implements Serializable {
	/** Serial ID */
	private static final long serialVersionUID = 1L;

	/**
	 * primaryKey of the table.
	 */
	private Long id;

	/**
	 * name of the sensor.
	 */
	@NotNull
	private String name;

	/**
	 * location of the sensor.
	 */
	@NotNull
	private String location;

	/**
	 * Date de création de l'objet.
	 */
	@PastOrPresent
	private LocalDate dateCreation;

	/**
	 * No arg constructor.
	 */
	public SensorDto() {
	}

	/**
	 * Getter for id.
	 *
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Getter for name.
	 *
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for location.
	 *
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Getter for dateCreation.
	 *
	 */
	public LocalDate getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * @param id value to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name value to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param location value to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param dateCreation value to set
	 */
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
}
