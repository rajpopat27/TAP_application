package com.biomatiques.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "attendance")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"attendedDate"},
        allowGetters = true)

public class Attendance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date attendedDate;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIME)
    @CreatedDate
    private Date attendedTime ;
	
	@Column
	private long employeeId;
	
	@Column
	private String employeeFirstName;
	
	@Column
	private String employeeLastName;
	
	@Column
	private boolean hoursCalculated;
	/*@Column
	private String CheckIn;
	
	@Column
	private String CheckOut;
//	@ManyToOne
*///	Employee employee;

	/*@OneToOne
	@JoinColumn(name="Employee_ID")
	private Employee employeeId;
	@OneToOne
	@JoinColumn(name="Employee_First_Name")
	private Employee employeeFirstName;
*/

	/*public String getCheckIn() {
		return CheckIn;
	}

	public void setCheckIn(String checkIn) {
		CheckIn = checkIn;
	}

	public int getCheckOut() {
		return CheckOut;
	}

	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}*/

	public Long getId() {
		return id;
	}
	
	public boolean getHoursCalculated() {
		return hoursCalculated;
	}

	public void setHoursCalculated(boolean hoursCalculated) {
		this.hoursCalculated = hoursCalculated;
	}

	public Date getAttendedDate() {
		return attendedDate;
	}
	
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
		

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public Date getAttendedTime() {
		return attendedTime;
	}

	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAttendedDate(Date attendedDate) {
		this.attendedDate = attendedDate;
	}


}
