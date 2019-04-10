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

import com.biomatiques.model.Payroll;
import com.biomatiques.model.PayrollReport;
import com.biomatiques.services.PayrollService;

@Controller
public class PayrollController {

	@Autowired
	PayrollService payrollService;
	
	@RequestMapping(value="/payroll.html",method=RequestMethod.GET,headers="Accept=application/json")
	public String payrollHome() {
		return "payroll.html";
	}
	
	@RequestMapping(value="/viewPayroll.html",method=RequestMethod.GET,headers="Accept=application/json")
	public String generatePayroll(Model model) {
		payrollService.generatePayrollHours();
		model.addAttribute("payroll", payrollService.getAllPayroll());
		return "viewPayroll.html";
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
}
