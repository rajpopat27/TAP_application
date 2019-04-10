package com.biomatiques.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.biomatiques.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public Employee findByIrisId(byte[] irisId);
	public Employee findById(long id);
	
}
