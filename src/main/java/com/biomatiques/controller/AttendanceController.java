package com.biomatiques.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.biomatiques.model.Login;
import com.biomatiques.services.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	AttendanceService attendanceService;
	
	@RequestMapping(value="/attendance.html",method=RequestMethod.GET)
	public String attendanceHome(Model model) {
		
		if(Login.loggedin==true) {
			model.addAttribute("attendance",attendanceService.getAllAttendance());
			return "attendance";
			
		}
		else {
			return "error1.html";
		}
	}
	
	@RequestMapping(value="/hoursWorked.html",method=RequestMethod.GET)
	public String viewHoursWorked(Model model) {		
		if(Login.loggedin==true) {
			model.addAttribute("hoursWorked",attendanceService.getHoursWorked());
			return "hoursWorked";			
		}
		else {
			return "error1.html";
		}
	}
	
	//ADD
	
	@RequestMapping(value="/irisAttendance",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Void> addAttendance(@RequestBody String EmpCode) throws ParseException, URISyntaxException {
 		if (Login.loggedin==true) {
 			if(attendanceService.addAttendance(EmpCode)==true) {
 				 return ResponseEntity.created(new URI("done")).build();
 			}
 		}		
		
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
 		
	}
	
	@RequestMapping(value="/iris1Attendance",method=RequestMethod.POST,headers="Accept=application/json")
	public void irisAttendance(@RequestBody byte[] irisId) throws ParseException, URISyntaxException {
 		System.out.println(irisId);
	}
	
	
	/*@RequestMapping(value="/irisAttendance",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Void> irisAttendance((@RequestBody Iris iris) throws ParseException, URISyntaxException {
 		if(attendanceService.addAttendance(irisId)==true) {
			 return ResponseEntity.created(new URI("done")).build();
		}
		else{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	*/
	
	
	/*//INDEX
	@RequestMapping(value="/attendanceForm.html",method=RequestMethod.GET)
	public String attendance() {
		return "attendanceForm";
	}	*/
	
	
	/*//ADD
	@RequestMapping(value="/addAttendance",method=RequestMethod.POST)
	public String addAttendance (@RequestPayload byte[] irisId, Model model) throws ParseException, URISyntaxException {
		if(result.hasErrors()) {
			return "addAttendance";
		}
		attendanceService.addAttendance(irisId);
		//model attribute is for the table.html view where each employee from the employees is extracted
		model.addAttribute("attendance", attendanceService.getAllAttendance());
		return "redirect:/viewAttendance.html";		
	}*/
	
	//READ
	@RequestMapping(value="/viewAttendance.html",method=RequestMethod.GET)
	public ModelAndView getAttendance() {
		if(Login.loggedin==true) {
			ModelAndView model = new ModelAndView("viewAttendance.html");
			model.addObject("attendance",attendanceService.getAllAttendance());
			return model;		
		}
		else {
			 ModelAndView model = new ModelAndView("eror1.html");
			 return model;
		}
		
	}
	
/*	//GET ATTENDANCE BY ID
	@RequestMapping(value="/attendance/{employeeId}",method=RequestMethod.GET)
	public String getAttendanceByEmployeeId(@PathVariable long employeeId,Model model) {
		model.addAttribute("employees", attendanceService.getAttendanceByEmployeeId(employeeId));
		return "viewAttendance.html";
	}
	
	//GET ATTENDANCE BY FIRST NAME
	@RequestMapping(value="/attendance/employee/{firstName}",method=RequestMethod.GET)
	public String getAttendanceByEmployeeFirstName(@PathVariable String firstName,Model model) {
		model.addAttribute("employees", attendanceService.getAttendanceByEmployeeFirstName(firstName));
		return "view-attendance";
	}
	
	//GET ATTENDANCE BY DATE
	@RequestMapping(value="/attendance/attended_date/{date}", method=RequestMethod.GET)
		public String getAttendanceByDate(@PathVariable("date") String date ,Model model){
		model.addAttribute("employees",  attendanceService.getAttendanceByDate(date));
			return "view-attendance";
		}
	*/
	
	 
}
