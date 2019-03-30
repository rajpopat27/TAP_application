package com.biomatiques.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biomatiques.model.ShiftPattern;
import com.biomatiques.services.ShiftPatternService;

@Controller
public class ShiftPatternController {

	@Autowired
    ShiftPatternService shiftPatternService;
    
//	@RequestMapping(method = RequestMethod.GET, value = "/shiftPatternHome")
//    public String getAllShiftPattern(){
//        return "shiftPatternHome";
//    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/shiftPatternForm.html")
    public String shiftPatternForm(ShiftPattern shiftPattern){
        return "shiftPatternForm";
    }
	
	//Add Method
    @RequestMapping(value="/addShiftPattern",method=RequestMethod.POST)
    public String addShiftPattern( @Valid ShiftPattern shiftPattern ,BindingResult result,Model model) {
        if(result.hasErrors()) {
        	return "viewShiftPattern";
        }
        shiftPatternService.addShiftPattern(shiftPattern);
        model.addAttribute("shiftPattern", shiftPatternService.getAllShiftPattern());
        return "redirect:/viewShiftPattern.html";    	
    }
    
  //Edit Form
    @RequestMapping(value="/editShiftPattern/{id}",method=RequestMethod.GET)
    public String editShiftById(@PathVariable Long id,Model model) {
    	model.addAttribute("shiftPattern",shiftPatternService.getShiftPatternById(id));
    	return "editShiftPattern";
    }
    
    //GET Methods one and all employees
    @RequestMapping(method = RequestMethod.GET, value = "/viewShiftPattern.html")
    public String getAllShiftPattern(Model model){
        model.addAttribute("shiftPattern",shiftPatternService.getAllShiftPattern());
        return "viewShiftPattern";
    }
    
    
    @RequestMapping(value="/shiftPattern/{id}",method=RequestMethod.GET)
    public String getShiftPatternById(@PathVariable Long id,Model model) {
    	model.addAttribute("shiftPattern",shiftPatternService.getShiftPatternById(id));
        return "view-shiftPattern";
    }  
    
    
    //Update Method
    @RequestMapping(value="/updateShiftPattern/{id}",method=RequestMethod.POST)
    public String updateShiftPattern(@Valid ShiftPattern shiftPattern ,BindingResult result,Model model ) {
//    	if(result.hasErrors()) {
//        	return "shiftPattern-home";
//        }
    	shiftPatternService.updateShiftPattern(shiftPattern);
    	 model.addAttribute("shiftPattern",shiftPatternService.getAllShiftPattern());
         return "redirect:/viewShiftPattern.html";
    }
    
    //Delete Method
    @RequestMapping(value="/deleteShiftPattern/{id}",method=RequestMethod.GET)
    public String deleteShiftPattern( @PathVariable long id,Model model) {
        shiftPatternService.deleteShiftPattern(id);
        model.addAttribute("shiftPattern",shiftPatternService.getAllShiftPattern());
        return "redirect:/viewShiftPattern.html";
    }
}
