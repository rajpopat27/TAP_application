package com.biomatiques.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomatiques.model.Employee;
import com.biomatiques.model.ShiftPattern;
import com.biomatiques.model.ShiftSwap;
import com.biomatiques.repository.ShiftPatternRepository;
import com.biomatiques.repository.ShiftSwapRepository;
import com.biomatiques.services.EmployeeService;
import com.biomatiques.services.ShiftPatternService;

@Service
public class ShiftSwapService {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	ShiftPatternService shiftPatternService;
	@Autowired
	ShiftPatternRepository shiftPatternRepository;
	@Autowired
	ShiftSwapRepository shiftSwapRepository;
	
	int shiftEmp1,shiftEmp2,tempShiftEmp1,tempShiftEmp2;
	
	public void swapShift(ShiftSwap shiftSwap) throws ParseException {
		long empId1 = shiftSwap.getEmp1();
		long empId2 = shiftSwap.getEmp2();
		String date = shiftSwap.getDate();
		ShiftPattern shiftPatternEmp1=shiftPatternRepository.findById(empId1);
		ShiftPattern shiftPatternEmp2=shiftPatternRepository.findById(empId2);
		 Date formattedDate=new SimpleDateFormat("dd/M/yyyy").parse(date);
		 String dayOfWeek = new SimpleDateFormat("EEEE").format(formattedDate).toLowerCase();
		 switch (dayOfWeek) { 
	        case "monday": 
	            shiftEmp1 = shiftPatternEmp1.getMonday();
	            shiftEmp2 = shiftPatternEmp2.getMonday();
	            tempShiftEmp1 = shiftEmp2;
	   		    tempShiftEmp2 = shiftEmp1;
	   		    shiftPatternEmp1.setMonday(tempShiftEmp1);
	   		    shiftPatternEmp2.setMonday(tempShiftEmp2);
	            
	            break; 
	        case "tuesday": 
	        	 shiftEmp1 = shiftPatternEmp1.getTuesday();
	             shiftEmp2 = shiftPatternEmp2.getTuesday();
	             tempShiftEmp1 = shiftEmp2;
	    		 tempShiftEmp2 = shiftEmp1;
	    		 shiftPatternEmp1.setTuesday(tempShiftEmp1);
	   		     shiftPatternEmp2.setTuesday(tempShiftEmp2);
	            break; 
	        case "wednesday": 
	        	 shiftEmp1 = shiftPatternEmp1.getWednesday();
	             shiftEmp2 = shiftPatternEmp2.getWednesday();
	             tempShiftEmp1 = shiftEmp2;
	    		 tempShiftEmp2 = shiftEmp1;
	    		 shiftPatternEmp1.setWednesday(tempShiftEmp1);
	   		     shiftPatternEmp2.setWednesday(tempShiftEmp2);
	            break; 
	        case "thursday": 
	        	shiftEmp1 = shiftPatternEmp1.getThursday();
	             shiftEmp2 = shiftPatternEmp2.getThursday();
	             tempShiftEmp1 = shiftEmp2;
	    		 tempShiftEmp2 = shiftEmp1;
	    		 shiftPatternEmp1.setThursday(tempShiftEmp1);
		   		    shiftPatternEmp2.setThursday(tempShiftEmp2);
	            break; 
	        case "friday": 
	        	shiftEmp1 = shiftPatternEmp1.getFriday();
	             shiftEmp2 = shiftPatternEmp2.getFriday();
	             tempShiftEmp1 = shiftEmp2;
	    		 tempShiftEmp2 = shiftEmp1;
	    		 shiftPatternEmp1.setFriday(tempShiftEmp1);
	   		     shiftPatternEmp2.setFriday(tempShiftEmp2);
	            break; 
	        case "saturday": 
	        	shiftEmp1 = shiftPatternEmp1.getSaturday();
	             shiftEmp2 = shiftPatternEmp2.getSaturday();
	             tempShiftEmp1 = shiftEmp2;
	    		 tempShiftEmp2 = shiftEmp1;
	    		 shiftPatternEmp1.setSaturday(tempShiftEmp1);
	   		     shiftPatternEmp2.setSaturday(tempShiftEmp2);
	            break; 
	        case "sunday": 
	        	shiftEmp1 = shiftPatternEmp1.getSunday();
	             shiftEmp2 = shiftPatternEmp2.getSunday();
	             tempShiftEmp1 = shiftEmp2;
	    		 tempShiftEmp2 = shiftEmp1;
	    		 shiftPatternEmp1.setSunday(tempShiftEmp1);
	   		     shiftPatternEmp2.setSunday(tempShiftEmp2);
	            break; 
	        default:
	            break; 
	        } 
		 
		shiftPatternRepository.save(shiftPatternEmp1);
		shiftPatternRepository.save(shiftPatternEmp2);
		shiftSwapRepository.save(shiftSwap);
	}
	
	public List<ShiftSwap> getAllShiftSwap() {
        List<ShiftSwap> ShiftSwapList = new ArrayList<>();
        shiftSwapRepository.findAll().forEach(ShiftSwapList::add);
        return ShiftSwapList;
    }
	
	 public void updateShiftSwap(ShiftSwap shiftSwap) {
	    	shiftSwapRepository.save(shiftSwap);
	    }
    public void deleteShiftSwap(long id) throws ParseException {
    	ShiftSwap shiftSwap = new ShiftSwap();
    	shiftSwap = shiftSwapRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));	
    	swapShift(shiftSwap);
    	shiftSwapRepository.deleteById(id);
    }
    public ShiftSwap getShiftSwapById(Long id) {
		return shiftSwapRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));		
	}
}
