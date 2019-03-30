package com.biomatiques.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomatiques.model.ShiftPattern;
import com.biomatiques.repository.ShiftPatternRepository;
import com.biomatiques.repository.ShiftRepository;

@Service
public class ShiftPatternService {
	
	@Autowired
    private ShiftPatternRepository shiftPatternRepository;
   
    public List<ShiftPattern> getAllShiftPattern() {
        List<ShiftPattern> shiftPatternList = new ArrayList<>();
        shiftPatternRepository.findAll().forEach(shiftPatternList::add);
        return shiftPatternList;
    }
   
    public void addShiftPattern(ShiftPattern shiftPattern) {
    	shiftPatternRepository.save(shiftPattern);
    }
    
    public void updateShiftPattern(ShiftPattern shiftPattern) {
    	shiftPatternRepository.save(shiftPattern);
    }
    public void deleteShiftPattern(long id) {
    	shiftPatternRepository.deleteById(id);
    }

	public ShiftPattern getShiftPatternById(Long id) {
		return shiftPatternRepository.findById(id).get();			
	}
}
