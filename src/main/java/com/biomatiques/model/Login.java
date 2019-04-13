package com.biomatiques.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Login")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"loggedInAt"},
        allowGetters = true)
public class Login implements Serializable{
	
	 	@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    @Column(name="Id")
	 	private long id;
	 	
	 	@Column(name="userId")
	 	private String userId;
	 	
	 	@Column(name="password")
	 	private String password;

	 	@Transient
	 	public static boolean loggedin=false;
	 	
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	 	
	 	
	    
	 

}
