package com.biomatiques.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="LeaveManagement")
public class LeaveManagement {
	
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="leaveId")
	private long id;
	
	@NotNull
	//@JsonProperty("EmpId")	
	@Column
    public long empId;
	
	@NotEmpty
	////@JsonProperty("Date1")
	@Column
    public String date1;
	
	@NotEmpty
	//@JsonProperty("Date2")
	@Column
    public String date2;
	
	@NotEmpty
	//@JsonProperty("Reason")
	@Column
	public String reason;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}