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


import com.biomatiques.model.Employee;
import com.biomatiques.model.Iris;
import com.biomatiques.model.Login;
import com.biomatiques.services.EmployeeService;
import com.biomatiques.services.ShiftService;
import java.util.List;

@Controller	
public class EmployeeController {
	@Autowired 
	EmployeeService employeeService;
	@Autowired
	ShiftService shiftService;
	
	@RequestMapping(value= {"/index","/dashboard.html"},method=RequestMethod.GET)
	public String index(Model model) {
		if(Login.loggedin==true) {
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

	//Pagiantion
	
	/*@RequestMapping(value = "/viewEmployee.html", method = RequestMethod.GET)
    public String listBooks(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);
 
        Page<Employee> employeePage = employeeService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
 
        model.addAttribute("employeePage", employeePage);
 
        int totalPages = employeePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
 
        return "viewEmployee.html";
    }*/
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
	
	
	
	
	
	
	
	
	
	
	
}
