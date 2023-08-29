package com.genesys.weatherapp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * table of weather sensors.
 */
@Entity
@Table(name = "SENSOR")
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Sensor {

	/**
	 * primaryKey of the table.
	 */
	@Id
	@SequenceGenerator(name = "SEQ_SENSOR", sequenceName = "SEQ_SENSOR", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SENSOR")
	@Column(name = "SEN_ID", nullable = false, scale = 10)
	private Long id;

	/**
	 * name of the sensor.
	 */
	@Column(name = "SEN_NAME", nullable = false, length = 250)
	private String name;

	/**
	 * location of the sensor.
	 */
	@Column(name = "SEN_LOCATION", nullable = false, length = 250)
	private String location;

	/**
	 * metrics of the sensor.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sensor")
	private List<Metric> metrics;

	/**
	 * Date de création de l'objet.
	 */
	@Column(name = "SEN_DATE_CREATION", nullable = true)
	@CreatedDate
	private LocalDate dateCreation;

	/**
	 * No arg constructor.
	 */
	public Sensor() {
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
	 * Getter for metrics.
	 *
	 */
	public List<Metric> getMetrics() {
		if(this.metrics == null)
			this.metrics = new ArrayList<>();
		return this.metrics;
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
	 * @param metrics value to set
	 */
	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

	/**
	 * @param dateCreation value to set
	 */
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

}
