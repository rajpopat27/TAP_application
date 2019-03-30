package com.biomatiques.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ShiftSwap")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class ShiftSwap {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="SwapId")
 	private long id;
	 
	@NotNull(message="Emp 1 is required")
	public long emp1;
	@NotNull(message="Emp 2 is required")
	public long emp2;
	@NotBlank(message = "Date is mandatory")
    public String date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEmp1() {
		return emp1;
	}
	public void setEmp1(long emp1) {
		this.emp1 = emp1;
	}
	public long getEmp2() {
		return emp2;
	}
	public void setEmp2(long emp2) {
		this.emp2 = emp2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
