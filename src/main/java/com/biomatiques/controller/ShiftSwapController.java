package com.biomatiques.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biomatiques.model.ShiftSwap;
import com.biomatiques.services.ShiftSwapService;

@RestController
public class ShiftSwapController {

	@Autowired
	ShiftSwapService shiftSwapService;
	@RequestMapping(value="/shiftSwap",method=RequestMethod.GET)
	public String shiftSwap(ShiftSwap shiftSwap) {
		return "shiftSwap-home";
	}
	
	
	@RequestMapping(value="/addShiftSwap",method=RequestMethod.POST)
	public String addShiftSwap(@Valid ShiftSwap shiftSwap,BindingResult result,Model model) throws ParseException{
 		shiftSwapService.swapShift(shiftSwap);
 		return "shiftSwap-home";
	}
	
	@RequestMapping(value="/viewShiftSwap",method=RequestMethod.POST)
	public String getShiftSwap(Model model) throws ParseException{
 		model.addAttribute("shiftSwap", shiftSwapService.getAllShiftSwap());
 		return "shiftSwap-home";
	}
	
}


