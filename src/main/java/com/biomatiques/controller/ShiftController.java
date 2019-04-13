package com.biomatiques.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.biomatiques.model.Login;
import com.biomatiques.model.Shift;
import com.biomatiques.services.ShiftService;

@Controller
public class ShiftController {
	
	    @Autowired
	    ShiftService shiftService;
	    
	    @RequestMapping(value= {"/shift.html"},method=RequestMethod.GET)
		public String shiftHomePage() {
	    	if(Login.loggedin==true) {
	    		return "shift";
				}
				else {
					return "error1.html";
				}
			
		}
	    
	    @RequestMapping(value="/shiftForm.html",method=RequestMethod.GET)
	    public String shiftForm(Shift shift) {
	    	if(Login.loggedin==true) {
	    		return "shiftForm";
				}
				else {
					return "error1.html";
				}
			
	    	
	    }
	  //Add Method
	    @RequestMapping(value="/addShift",method=RequestMethod.POST)
	    public String addShift( @Valid Shift shift,BindingResult result,Model model) {
	    	/*if(result.hasErrors()) {
	    		return "add-shift";
	    	}*/	       
	        if(Login.loggedin==true) {
	        	 shiftService.addShift(shift);
	 	        model.addAttribute("shift", shiftService.getAllShifts());
	 	        return "redirect:/viewShift.html";
				}
				else {
					return "error1.html";
				}
	    }
	    //GET Method
	    @RequestMapping(method = RequestMethod.GET, value = "/viewShift.html")
	    public String getAllEmployees(Model model){
	    	 if(Login.loggedin==true) {
	    		 model.addAttribute("shifts", shiftService.getAllShifts());
	 	        return "viewShift";
				}
				else {
					return "error1.html";
				}
	       
	    }
	    
	    //Edit Form
	    @RequestMapping(value="/editShift/{id}",method=RequestMethod.GET)
	    public String editShiftById(@PathVariable Long id,Model model) {
	    	if(Login.loggedin==true) {
	    		model.addAttribute("shift",shiftService.getShiftById(id));
		    	return "editShift";
				}
				else {
					return "error1.html";
				}
	    	
	    }
	    
	    
	    //Update Method
	    @RequestMapping(value="/updateShift/{id}",method=RequestMethod.POST)
	    public String updateEmployee(@Valid Shift shift , BindingResult result,Model model ) {
	    	/*if(result.hasErrors()) {
	    		return "update-shift";
	    	}*/
	    	if(Login.loggedin==true) {
	    		 shiftService.updateShift(shift);
	 	        model.addAttribute("shift", shiftService.getAllShifts());
	 	        return "redirect:/viewShift.html";
				}
				else {
					return "error1.html";
				}
	       
	    }
	    
	    
	    //GET SHIFT BY ID
	    @RequestMapping(value="/shift/{id}",method=RequestMethod.GET)
	    public String getShiftById(@PathVariable Long id,Model model) {
	    	if(Login.loggedin==true) {
	    		model.addAttribute("shift",shiftService.getShiftById(id));
		    	return "updateShift";
				}
				else {
					return "error1.html";
				}
	    	
	    }
	  
	    
	    //Delete Method
	    @RequestMapping(value="/deleteShift/{id}",method=RequestMethod.GET)
	    public String deleteEmployee( @PathVariable long id,Model model) {
	    	if(Login.loggedin==true) {
	    		 shiftService.deleteShift(id);
	 	        model.addAttribute("shifts", shiftService.getAllShifts());
	 	        return "redirect:/viewShift.html";
				}
				else {
					return "error1.html";
				}
	       
	    }
}
