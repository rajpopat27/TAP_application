package com.biomatiques.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biomatiques.model.Login;
import com.biomatiques.model.Payroll;
import com.biomatiques.model.PayrollReport;
import com.biomatiques.services.PayrollService;

@Controller
public class PayrollController {

	@Autowired
	PayrollService payrollService;
	
	@RequestMapping(value="/payroll.html",method=RequestMethod.GET,headers="Accept=application/json")
	public String payrollHome() {
		if(Login.loggedin==true) {
			return "payroll.html";	
			}
			else {
				return "error1.html";
			}
		
	}
	
	@RequestMapping(value="/viewPayroll.html",method=RequestMethod.GET,headers="Accept=application/json")
	public String viewPayroll(Model model) {		
		if(Login.loggedin==true) {
			payrollService.generatePayrollHours();
			model.addAttribute("payroll", payrollService.getRemainingPayroll());
			return "viewPayroll.html";
			}
			else {
				return "error1.html";
			}
	}
	
	@RequestMapping(value="/viewGeneratedPayroll.html",method=RequestMethod.GET,headers="Accept=application/json")
	public String viewGeneratedPayroll(Model model) {
		if(Login.loggedin==true) {
			payrollService.generatePayrollHours();
			model.addAttribute("payroll", payrollService.getGeneratedPayroll());
			return "viewGeneratedPayroll.html";
			}
			else {
				return "error1.html";
			}
		
	}
	
	@RequestMapping(value = "/payrollPdfReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> payrollReport() throws IOException {
		
        List<Payroll> payroll = (List<Payroll>) payrollService.getPayrollForReport();

        ByteArrayInputStream bis = PayrollReport.payrollReport(payroll);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=payrollReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@RequestMapping(value="/viewHoursWorked.html",method=RequestMethod.GET,headers="Accept=application/json")
	public String viewHoursWorked(Model model) {
		if(Login.loggedin==true) {
			model.addAttribute("hoursWorked",payrollService.getHoursWorked());
			return "viewHoursWorked.html";
			}
			else {
				return "error1.html";
			}
		
	}
}
