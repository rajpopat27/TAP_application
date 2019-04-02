package com.biomatiques.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "hours_worked")
public class Hours_worked_payroll {
	public Hours_worked_payroll() {
		
	}
	
	public Hours_worked_payroll(long emp_id, float no_of_hours) {
		super();
		this.emp_id = emp_id;
		No_of_hours = no_of_hours;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column
	private long emp_id;
	@Column
	private Date time_out;
	@Column
	private Date time_in;
	@Column
	private float No_of_hours;
	@Column
	private boolean payroll_generated;
	
public long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}
	public boolean isPayroll_generated() {
		return payroll_generated;
	}
	public void setPayroll_generated(boolean payroll_generated) {
		this.payroll_generated = payroll_generated;
	}
	public void setId(Long id) {
		this.id = id;
	}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Date getTime_out() {
	return time_out;
}
public void setTime_out(Date time_out) {
	this.time_out = time_out;
}
public Date getTime_in() {
	return time_in;
}
public void setTime_in(Date time_in) {
	this.time_in = time_in;
}
public float getNo_of_hours() {
	return No_of_hours;
}
public void setNo_of_hours(float no_of_hours) {
	No_of_hours = no_of_hours;
}


}
