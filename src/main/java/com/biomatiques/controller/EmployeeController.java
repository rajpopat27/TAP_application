package com.biomatiques.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biomatiques.model.Department;
import com.biomatiques.model.Employee;
import com.biomatiques.model.Iris;
import com.biomatiques.model.Login;
import com.biomatiques.repository.DepartmentRepository;
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
	DepartmentRepository departmentRepository;
	
	@Autowired
	AttendanceService attendanceService;
	@RequestMapping(value= {"/index","/dashboard.html"},method=RequestMethod.GET)
	public String index(Model model) {
		if(Login.loggedin==true) {
			model.addAttribute("totalEmployee",employeeService.getAllEmployees().stream().count());
			model.addAttribute("totalShift", shiftService.getAllShifts().stream().count());	
			model.addAttribute("attendance", attendanceService.liveClockIn());
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
	public String employeeForm(Employee employee, Model model) {
		if(Login.loggedin==true) {
			List <Department> departments = new ArrayList<>();
			departmentRepository.findAll().forEach(departments::add);
			model.addAttribute("departments" ,departments);
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
			List <Department> departments = new ArrayList<>();
			departmentRepository.findAll().forEach(departments::add);
			model.addAttribute("departments" ,departments);
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
	
	@RequestMapping(value= {"/report.html"},method=RequestMethod.GET)
	public String reportPage() {
		if(Login.loggedin==true) {
			return "report.html";
		}
		else {
			return "error1.html";
		}
		
	}
	
	//Department Form
	@RequestMapping(value= {"/departmentForm.html"},method=RequestMethod.GET)
	public String departmentForm(Department department) {
		if(Login.loggedin==true) {
			return "departmentForm";
		}
		else {
			return "error1.html";
		}
		
	}
	
	//Add Department
	@RequestMapping(value="/addDepartment",method=RequestMethod.POST)
	public String addDepartment (@Valid Department department ,BindingResult result, Model model) {
		
		List <Department> departmentList = new ArrayList<>();
		departmentRepository.save(department);
		departmentRepository.findAll().forEach(departmentList::add);
		//model attribute is for the table.html view where each employee from the employees is extracted
		model.addAttribute("department", departmentList);
		return "redirect:/department.html";		
	}
	
	//View Department
	@RequestMapping(value= {"/department.html"},method=RequestMethod.GET)
	public String viewDepartment(Department department, Model model) {
		if(Login.loggedin==true) {
			List <Department> departments = new ArrayList<>();
			departmentRepository.findAll().forEach(departments::add);
			model.addAttribute("departments" ,departments);
			return "department.html";
		}
		else {
			return "error1.html";
		}
		
	}
	
	//Edit department form
	@RequestMapping(value="/editDepartment/{id}",method=RequestMethod.GET)
	public String editDepartment(@PathVariable("id") long id, Model model) {
	
		if(Login.loggedin==true) {
			Department department = new Department();
			department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
			model.addAttribute("department", department);
			return "editDepartment";	
		}
		else {
			return "error1.html";
		}
		   
	}
	
	//Update Department
	@RequestMapping(value="/updateDepartment/{id}",method=RequestMethod.POST)
	public String updateDepartment(@PathVariable("id") long id, @Valid Department department,BindingResult result, Model model) {
	   /* if (result.hasErrors()) {
	        employee.setId(id);
	        return "update-employee";
	    }*/
		if(Login.loggedin==true) {
			departmentRepository.save(department);
			//employeeService.updateEmployee(employee);
			List <Department> departments = new ArrayList<>();
			departmentRepository.findAll().forEach(departments::add);
			model.addAttribute("departments" ,departments);
			return "redirect:/department.html";	
		}
		else {
			return "error1.html";
		}  
	    
	}
	
	//DELETE     
	@RequestMapping(value="/deleteDepartment/{id}",method=RequestMethod.GET)
	public String deleteDepartment(@PathVariable("id") long id, Department department, Model model) {
		if(Login.loggedin==true) {
			departmentRepository.deleteById(id);
//			Employee employee = employeeService.getEmployeeById(id);
//			employeeService.deleteEmployee(employee.getId());
			List <Department> departments = new ArrayList<>();
			departmentRepository.findAll().forEach(departments::add);
			model.addAttribute("departments" ,departments);
			return "redirect:/department.html";
		}
		else {
			return "error1.html";
		}  
			
	}
	
	
	
}
