package com.biomatiques.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biomatiques.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
