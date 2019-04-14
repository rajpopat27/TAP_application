package com.biomatiques.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.biomatiques.model.Employee;
import com.biomatiques.model.Iris;
import com.biomatiques.model.Login;
import com.biomatiques.services.AttendanceService;
import com.biomatiques.services.EmployeeService;
import com.biomatiques.services.ShiftService;

@Controller	
public class EmployeeController {
	@Autowired 
	EmployeeService employeeService;
	@Autowired
	ShiftService shiftService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	AttendanceService attendanceService;
	
	
	@RequestMapping(value= {"/index","/dashboard.html"},method=RequestMethod.GET)
	public String index(Model model) {
		if(Login.loggedin==true) {
			//JDBC Query
			 int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee", Integer.class);
			model.addAttribute("attendance", attendanceService.liveClockIn());
			model.addAttribute("totalEmployee",employeeService.getAllEmployees().stream().count());
			model.addAttribute("totalShift", shiftService.getAllShifts().stream().count());	
			//model.addAttribute("indexLabel", employeeService.get)
			return "dashboard";
		}
		else {
			return "error1.html";
		}
	}
	@RequestMapping(value= {"/employee.html"},method=RequestMethod.GET)
	public String employee(Model model) {
		if(Login.loggedin==true) {
			model.addAttribute("employees", employeeService.getAllEmployees());
			return "employee";
		}
		else {
			return "error1.html";
		}
		
	}
	@RequestMapping(value= {"/employeeForm.html"},method=RequestMethod.GET)
	public String employeeForm(Employee employee) {
		if(Login.loggedin==true) {
			return "employeeForm";
		}
		else {
			return "error1.html";
		}
		
	}
	@RequestMapping(value= {"/viewEmployee.html"},method=RequestMethod.GET)
	public String view(Model model) {
		if(Login.loggedin==true) {
			model.addAttribute("employees", employeeService.getAllEmployees());
			return "viewEmployee.html";
		}
		else {
			return "error1.html";
		}
		
	}

	@RequestMapping(value= {"/report.html"},method=RequestMethod.GET)
	public String reportPage() {
		if(Login.loggedin==true) {
			return "report.html";
		}
		else {
			return "error1.html";
		}
		
	}

	//ADD
	@RequestMapping(value="/addEmployee",method=RequestMethod.POST)
	public String addEmployee (@Valid Employee employee,BindingResult result, Model model) {
		/*if(result.hasErrors()) {
			return "dashboard";
		}*/
		employeeService.addEmployee(employee);
		//model attribute is for the table.html view where each employee from the employees is extracted
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "redirect:/viewEmployee.html";		
	}
	//Editform
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editEmployee(@PathVariable("id") long id, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
		if(Login.loggedin==true) {
			 model.addAttribute("employee",employeeService.getEmployeeById(id));
			    return "editEmployee";	
		}
		else {
			return "error1.html";
		}
	   
	}
	
	//UPDATE
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee,BindingResult result, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
		if(Login.loggedin==true) {
			employeeService.updateEmployee(employee);
		    model.addAttribute("employees",employeeService.getAllEmployees());
		    return "redirect:/employee.html";	
		}
		else {
			return "error1.html";
		}  
	    
	}
	
	//DELETE     
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") long id, Model model) {
		if(Login.loggedin==true) {
			Employee employee = employeeService.getEmployeeById(id);
		    employeeService.deleteEmployee(employee.getId());
		    model.addAttribute("employees", employeeService.getAllEmployees());
		    return "redirect:/viewEmployee.html";	
		}
		else {
			return "error1.html";
		}  
		
	}
	
	// Add Iris Data
	@RequestMapping(value="/iris",method=RequestMethod.POST,headers="Accept=application/json")
	public String addIris(@RequestBody Iris iris) {
		if(Login.loggedin==true) {
			employeeService.addIris(iris);		
			return "redirect:/viewEmployee.html";
		}
		else {
			return "error1.html";
		}  
		
	}
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:" + "https://www.google.com");
	}
	
	
	
	
	
	
	
	
	
}
