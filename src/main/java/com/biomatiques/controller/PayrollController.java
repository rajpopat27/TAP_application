package com.biomatiques.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.biomatiques.services.PayrollService;

@Controller
public class PayrollController {

	@Autowired
	PayrollService payrollService;
	
	
}
