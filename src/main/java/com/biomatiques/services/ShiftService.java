package com.biomatiques.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.biomatiques.model.Shift;

import com.biomatiques.repository.ShiftRepository;

@Service
public class ShiftService {
	
	 @Autowired
	    private ShiftRepository shiftRepository;
	   
	    public List<Shift> getAllShifts() {
	        List<Shift> shiftList = new ArrayList<>();
	        shiftRepository.findAll().forEach(shiftList::add);
	        return shiftList;
	    }
	   
	    public void addShift(Shift shift) {
	    	shiftRepository.save(shift);
	    }
	    
	    public void updateShift(Shift shift) {
	    	shiftRepository.save(shift);
	    }
	    public void deleteShift(long id) {
	    	shiftRepository.deleteById(id);
	    }

		public Shift getShiftById(Long id) {
			return shiftRepository.findById(id).get();			
		}
}
