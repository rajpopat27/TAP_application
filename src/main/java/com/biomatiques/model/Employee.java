package com.biomatiques.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Employee")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)

public class Employee implements Serializable{
   

	public Employee() {
       
    }
	public Employee(float indexLabel,float y) {
	       this.indexLabel=indexLabel;
	       this.y=y;
    }
    
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    @Column(name="employeeId")
    	private long id;
	    
	    @NotBlank(message = "First Name is mandatory")
	    @JsonProperty("FirstName")	
        public String firstName;
		
	    ////@NotEmpty
	    @NotBlank(message = "Middle Name is mandatory")
	    @JsonProperty("MiddleName")
        public String middleName;
		
	    ////@NotEmpty
	    @NotBlank(message = "Last Name is mandatory")
	    @JsonProperty("LastName")
        public String lastName;
		
	    //@NotEmpty
	    @NotBlank(message = "Gender is mandatory")	
        public String gender;
	    
	    //@NotEmpty
	    @NotBlank(message = "Phone number is mandatory")
	    //@Pattern(regexp="(^$|[0-9]{10})")
        @JsonProperty("PhoneNumber")
        public String phoneNumber;
		
	    
	    @NotNull(message = "DOB is mandatory")
	    @DateTimeFormat(pattern="dd/MM/yyyy")
        @JsonProperty("DateOfBirth")
		public Date dob;
        
	    //@NotEmpty
	    @NotBlank(message = "Address is mandatory")
        @JsonProperty("Address")
		public String address;
        
	    //@NotEmpty
	    @NotBlank(message = "Pincode is mandatory")
        @JsonProperty("Pincode")
	    public String pincode;
	    
	    //@NotEmpty
	    //@NotBlank(message = "Date is mandatory")
        @JsonProperty("State")
		public String state;
	    
	    //@NotEmpty
	    //@NotBlank(message = "City is mandatory")
       
		public String city;
        
	    //@NotEmpty
	   @NotBlank(message = "Email is mandatory")
		 @JsonProperty("Email")       
		public String email;
        
	    
	    @NotBlank(message = "Linkedin Id is mandatory")
		 @JsonProperty("LinkedinId")        
        public String linkedinId;
        
        //@NotEmpty
	   @NotBlank(message = "Blood Group is mandatory")
		 @JsonProperty("BloodGroup")
        public String bloodGroup;
        
        //@NotEmpty
	    @NotBlank(message = "Addhar Number is mandatory")
		 @JsonProperty("AadharNumber")
        public String aadharNumber;
        
        @Min(0)
        @NotNull(message = "Experience is mandatory")
		 @JsonProperty("Experience")
        public Integer experience;
        
        //@NotEmpty
        @NotBlank(message = "Department is mandatory")
		 @JsonProperty("Department")
        public String department;
        
        //@NotEmpty
        @NotBlank(message = "Employee Type is mandatory")
		 @JsonProperty("EmployeeType")
        public String employeeType;
        
        //@NotEmpty
        @NotBlank(message = "Post is mandatory")
		 @JsonProperty("EmployeePost")
        public String employeePost;
        
        //@NotEmpty
        @NotBlank(message = "Shift Name is mandatory")
		 @JsonProperty("ShiftName")
        public String shiftName;
        
        //@Min(1000)
        @NotNull(message = "Salary is mandatory")
		 @JsonProperty("Salary")
        public Float salary;
        
        //@NotEmpty
       // @NotBlank(message = "Company Name is mandatory")
		 @JsonProperty("Company")
		public String company;
	    
        //@NotEmpty
       // @NotNull(message = "IrisId Name is mandatory")
		@JsonProperty("IrisId")
        @Lob       
        public byte[] irisId;
        
		@Transient
		public float indexLabel;
		
		@Transient
		public float y;
		
        
		@Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date joinedAt;
	   
	    @Column(nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @LastModifiedDate
	    private Date updatedAt;
	    
        public long getId() {
    		return id;
    	}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLinkedinId() {
			return linkedinId;
		}

		public void setLinkedinId(String linkedinId) {
			this.linkedinId = linkedinId;
		}

		public String getBloodGroup() {
			return bloodGroup;
		}

		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}

		public String getAadharNumber() {
			return aadharNumber;
		}

		public void setAadharNumber(String aadharNumber) {
			this.aadharNumber = aadharNumber;
		}

		public Integer getExperience() {
			return experience;
		}

		public void setExperience(Integer experience) {
			this.experience = experience;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getEmployeeType() {
			return employeeType;
		}

		public void setEmployeeType(String employeeType) {
			this.employeeType = employeeType;
		}

		public String getEmployeePost() {
			return employeePost;
		}

		public void setEmployeePost(String employeePost) {
			this.employeePost = employeePost;
		}

		public String getShiftName() {
			return shiftName;
		}

		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}

		public Float getSalary() {
			return salary;
		}

		public void setSalary(Float salary) {
			this.salary = salary;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public byte[] getIrisId() {
			return irisId;
		}

		public void setIrisId(byte[] irisId) {
			this.irisId = irisId;
		}

		public Date getJoinedAt() {
			return joinedAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setId(long id) {
			this.id = id;
		}

		public void setJoinedAt(Date joinedAt) {
			this.joinedAt = joinedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public float getIndexLabel() {
			return indexLabel;
		}

		public void setIndexLabel(float indexLabel) {
			this.indexLabel = indexLabel;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
		}
        
        
}
		