package com.biomatiques.repository;

import org.springframework.data.repository.CrudRepository;

import com.biomatiques.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public Employee findByIrisId(byte[] irisId);
	public Employee findById(long id);
}
