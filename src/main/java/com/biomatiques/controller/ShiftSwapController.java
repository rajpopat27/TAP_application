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
import com.biomatiques.model.ShiftSwap;
import com.biomatiques.services.ShiftSwapService;

@Controller
public class ShiftSwapController {

	@Autowired
	ShiftSwapService shiftSwapService;
	/*@RequestMapping(value="/shiftSwap",method=RequestMethod.GET)
	public String shiftSwap(ShiftSwap shiftSwap) {
		return "shiftSwap-home";
	}
	*/
	@RequestMapping(value= {"/shiftSwap.html"},method=RequestMethod.GET)
	public String shiftSwapForm(ShiftSwap shiftSwap) {
		return "shiftSwap.html";
	}
	@RequestMapping(value="/addShiftSwap",method=RequestMethod.POST)
	public String addShiftSwap(@Valid ShiftSwap shiftSwap,BindingResult result,Model model) throws ParseException{
 		shiftSwapService.swapShift(shiftSwap);
 		return "viewShiftSwap.html";
	}
	
	@RequestMapping(value="/viewShiftSwap.html",method=RequestMethod.GET)
	public String getShiftSwap(Model model) throws ParseException{
 		model.addAttribute("shiftSwap", shiftSwapService.getAllShiftSwap());
 		return "viewShiftSwap.html";
	}
	
	@RequestMapping(value="/editShiftSwap/{id}",method=RequestMethod.GET)
	public String editShiftSwap(@PathVariable("id") long id, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
	         
	    model.addAttribute("shiftSwap",shiftSwapService.getShiftSwapById(id));
	    return "editShiftSwap.html";	
	}
	
	//UPDATE
	@RequestMapping(value="/updateShiftSwap/{id}",method=RequestMethod.POST)
	public String updateShiftSwap(@PathVariable("id") long id, @Valid ShiftSwap shiftSwap,BindingResult result, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
	         
		shiftSwapService.updateShiftSwap(shiftSwap);
	    //model.addAttribute("shiftSwap",employeeService.getAllEmployees());
	    return "redirect:/viewShiftSwap.html";	
	}
	
	//DELETE
	@RequestMapping(value="/deleteShiftSwap/{id}",method=RequestMethod.GET)
	public String deleteShiftSwap(@PathVariable("id") long id, Model model) throws ParseException {
	    //ShiftSwap shiftSwap = shiftSwapService.getShiftSwapById(id);
	    shiftSwapService.deleteShiftSwap(id);
	   // model.addAttribute("shiftSwap", shiftSwapService.getAllShiftSwap());
	    return "redirect:/viewShiftSwap.html";	
	}
	
}


