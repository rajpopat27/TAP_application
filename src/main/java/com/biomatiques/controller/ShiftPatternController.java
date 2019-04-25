package com.biomatiques.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biomatiques.model.Login;
import com.biomatiques.model.ShiftPattern;
import com.biomatiques.services.EmployeeService;
import com.biomatiques.services.ShiftPatternService;
import com.biomatiques.services.ShiftService;

@Controller
public class ShiftPatternController {

	@Autowired
    ShiftPatternService shiftPatternService;
    
	@Autowired
    ShiftService shiftService;
	
	@Autowired
	EmployeeService employeeService;
	
//	@RequestMapping(method = RequestMethod.GET, value = "/shiftPatternHome")
//    public String getAllShiftPattern(){
//        return "shiftPatternHome";
//    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/shiftPatternForm.html")
    public String shiftPatternForm(ShiftPattern shiftPattern, Model model){
		if(Login.loggedin==true) {
			model.addAttribute("employees", employeeService.getAllEmployees());
			model.addAttribute("shifts", shiftService.getAllShifts());
			return "shiftPatternForm";
			}
			else {
				return "error1.html";
			}
       
    }
	
	//Add Method
    @RequestMapping(value="/addShiftPattern",method=RequestMethod.POST)
    public String addShiftPattern( @Valid ShiftPattern shiftPattern ,BindingResult result,Model model) {
    	if(Login.loggedin==true) {
    		 if(result.hasErrors()) {
    	        	return "viewShiftPattern";
    	        }
    	        shiftPatternService.addShiftPattern(shiftPattern);
    	        model.addAttribute("shiftPattern", shiftPatternService.getAllShiftPattern());
    	        return "redirect:/viewShiftPattern.html";  
			}
			else {
				return "error1.html";
			}
         	
    }
    
  //Edit Form
    @RequestMapping(value="/editShiftPattern/{id}",method=RequestMethod.GET)
    public String editShiftById(@PathVariable Long id,Model model) {
    	if(Login.loggedin==true) {
    		model.addAttribute("employees", employeeService.getAllEmployees());
			model.addAttribute("shifts", shiftService.getAllShifts());
    		model.addAttribute("shiftPattern",shiftPatternService.getShiftPatternById(id));
        	return "editShiftPattern";
			}
			else {
				return "error1.html";
			}
    	
    }
    
    //GET Methods one and all employees
    @RequestMapping(method = RequestMethod.GET, value = "/viewShiftPattern.html")
    public String getAllShiftPattern(Model model){
    	if(Login.loggedin==true) {
    		 model.addAttribute("shiftPattern",shiftPatternService.getAllShiftPattern());
    	        return "viewShiftPattern";
			}
			else {
				return "error1.html";
			}
       
    }
    
    
    @RequestMapping(value="/shiftPattern/{id}",method=RequestMethod.GET)
    public String getShiftPatternById(@PathVariable Long id,Model model) {
    	if(Login.loggedin==true) {
    		model.addAttribute("shiftPattern",shiftPatternService.getShiftPatternById(id));
            return "view-shiftPattern";
			}
			else {
				return "error1.html";
			}
    
    }  
    
    
    //Update Method
    @RequestMapping(value="/updateShiftPattern/{id}",method=RequestMethod.POST)
    public String updateShiftPattern(@Valid ShiftPattern shiftPattern ,BindingResult result,Model model ) {
//    	if(result.hasErrors()) {
//        	return "shiftPattern-home";
//        }
    	if(Login.loggedin==true) {
    		shiftPatternService.updateShiftPattern(shiftPattern);
    		model.addAttribute("shiftPattern",shiftPatternService.getAllShiftPattern());
            return "redirect:/viewShiftPattern.html";
			}
			else {
				return "error1.html";
			}
    	
    }
    
    //Delete Method
    @RequestMapping(value="/deleteShiftPattern/{id}",method=RequestMethod.GET)
    public String deleteShiftPattern( @PathVariable long id,Model model) {
    	if(Login.loggedin==true) {
    		 shiftPatternService.deleteShiftPattern(id);
    	        model.addAttribute("shiftPattern",shiftPatternService.getAllShiftPattern());
    	        return "redirect:/viewShiftPattern.html";
			}
			else {
				return "error1.html";
			}
       
    }
}
