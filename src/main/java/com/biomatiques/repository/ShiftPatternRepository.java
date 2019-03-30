package com.biomatiques.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.biomatiques.model.Attendance;
import com.biomatiques.model.ShiftPattern;

public interface ShiftPatternRepository extends CrudRepository<ShiftPattern,Long>{
	
	public ShiftPattern findById(long id);
	
	
}
