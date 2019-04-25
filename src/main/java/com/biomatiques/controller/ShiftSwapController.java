package com.biomatiques.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biomatiques.model.Employee;
import com.biomatiques.model.Login;
import com.biomatiques.model.ShiftSwap;
import com.biomatiques.services.EmployeeService;
import com.biomatiques.services.ShiftSwapService;

@Controller
public class ShiftSwapController {

	@Autowired
	ShiftSwapService shiftSwapService;
	
	@Autowired
	EmployeeService employeeService;
	
	/*@RequestMapping(value="/shiftSwap",method=RequestMethod.GET)
	public String shiftSwap(ShiftSwap shiftSwap) {
		return "shiftSwap-home";
	}
	*/
	@RequestMapping(value= {"/shiftSwap.html"},method=RequestMethod.GET)
	public String shiftSwapForm(ShiftSwap shiftSwap, Model model) {
		if(Login.loggedin==true) {
			model.addAttribute("employees", employeeService.getAllEmployees());
			return "shiftSwap.html";
			}
			else {
				return "error1.html";
			}
		
	}
	
	@RequestMapping(value="/addShiftSwap",method=RequestMethod.POST)
	public String addShiftSwap(@Valid ShiftSwap shiftSwap,BindingResult result,Model model) throws ParseException{
		if(Login.loggedin==true) {
			shiftSwapService.swapShift(shiftSwap);
	 		return "viewShiftSwap.html";
			}
			else {
				return "error1.html";
			}
 		
	}
	
	@RequestMapping(value="/viewShiftSwap.html",method=RequestMethod.GET)
	public String getShiftSwap(Model model) throws ParseException{
		if(Login.loggedin==true) {
			model.addAttribute("shiftSwap", shiftSwapService.getAllShiftSwap());
	 		return "viewShiftSwap.html";
			}
			else {
				return "error1.html";
			}
 		
	}
	
	@RequestMapping(value="/editShiftSwap/{id}",method=RequestMethod.GET)
	public String editShiftSwap(@PathVariable("id") long id, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
		if(Login.loggedin==true) {
			model.addAttribute("employees", employeeService.getAllEmployees());
			model.addAttribute("shiftSwap",shiftSwapService.getShiftSwapById(id));
		    return "editShiftSwap.html";	
			}
			else {
				return "error1.html";
			}     
	    
	}
	
	//UPDATE
	@RequestMapping(value="/updateShiftSwap/{id}",method=RequestMethod.POST)
	public String updateShiftSwap(@PathVariable("id") long id, @Valid ShiftSwap shiftSwap,BindingResult result, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
		if(Login.loggedin==true) {
			shiftSwapService.updateShiftSwap(shiftSwap);
		    //model.addAttribute("shiftSwap",employeeService.getAllEmployees());
		    return "redirect:/viewShiftSwap.html";	
			}
			else {
				return "error1.html";
			}        
		
	}
	
	//DELETE
	@RequestMapping(value="/deleteShiftSwap/{id}",method=RequestMethod.GET)
	public String deleteShiftSwap(@PathVariable("id") long id, Model model) throws ParseException {
		if(Login.loggedin==true) {
			  shiftSwapService.deleteShiftSwap(id);
			   // model.addAttribute("shiftSwap", shiftSwapService.getAllShiftSwap());
			    return "redirect:/viewShiftSwap.html";	
			}
			else {
				return "error1.html";
			} 
	    //ShiftSwap shiftSwap = shiftSwapService.getShiftSwapById(id);
	  
	}
	
}


