package com.biomatiques.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="ShiftPattern")
public class ShiftPattern {
	
	public ShiftPattern() {
		
	}

	@Id
	@JsonProperty("EmployeeId")
    @Column(name="Id")
	private long id;
	
	
	@NotNull(message = "This is mandatory")
    public int monday;
	
	@NotNull(message = "This is mandatory")
    public int tuesday;

	@NotNull(message = "This is mandatory")
    public int wednesday;
	
	@NotNull(message = "This is mandatory")
    public int thursday;
	
	@NotNull(message = "This is mandatory")
    public int friday;
	
	@NotNull(message = "This is mandatory")	
    public int saturday;
	
	@NotNull(message = "This is mandatory")
    public int sunday;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMonday() {
		return monday;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public int getThursday() {
		return thursday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public int getFriday() {
		return friday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public int getSaturday() {
		return saturday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}

	public int getSunday() {
		return sunday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}

	
}
