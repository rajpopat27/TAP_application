package com.biomatiques.repository;

import org.springframework.data.repository.CrudRepository;


import com.biomatiques.model.Shift;
import com.biomatiques.model.ShiftPattern;

public interface ShiftRepository extends CrudRepository<Shift,Long> {
		
	public Shift findById(long id);
	
}