package com.biomatiques.controller;

import java.text.ParseException;
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
import org.springframework.web.servlet.ModelAndView;

import com.biomatiques.model.LeaveManagement;
import com.biomatiques.model.ShiftSwap;
import com.biomatiques.services.LeaveManagementService;

@Controller
public class LeaveManagementController {
	
	@Autowired
	LeaveManagementService leaveManagementService;

	@RequestMapping(value= "/leave.html",method=RequestMethod.GET)
	public String leaveHomePage(Model model) {
		model.addAttribute("leave", leaveManagementService.getAllLeave());
		return "leave";
	}
	
	@RequestMapping(value= "/leaveForm.html",method=RequestMethod.GET)
	public String leaveForm(LeaveManagement leaveManagement) {
		return "leaveForm.html";
	}
	
	@RequestMapping(value="/addLeave",method=RequestMethod.POST,headers="Accept=application/json")
	public String addLeave(@Valid LeaveManagement leaveManagement,BindingResult result,Model model) throws ParseException {
		leaveManagementService.addLeave(leaveManagement);
		return "leave.html";
	}
	
	@RequestMapping(value="/updateLeave/{id}",method=RequestMethod.POST,headers="Accept=application/json")
    public String updateLeave(@PathVariable("id") long id, @Valid LeaveManagement leaveManagement,BindingResult result, Model model ) {
		leaveManagementService.updateLeave(leaveManagement);
		 return "redirect:/leave.html";	
    }
	
	@RequestMapping(value="/editLeave/{id}",method=RequestMethod.GET,headers="Accept=application/json")
    public String updateLeave(@PathVariable("id") long id, Model model ) {
		model.addAttribute("leave", leaveManagementService.getLeaveById(id));
		 return "editLeave";	
    }
    
    //Delete Method
	@RequestMapping(value="/deleteLeave/{id}",method=RequestMethod.DELETE,headers="Accept=application/json")
    public String deleteLeave(@PathVariable("id") long id) {
		leaveManagementService.deleteLeave(id);
		return "redirect:/leave.html";	
    }
	/*@RequestMapping(value="/leaveManagement",method=RequestMethod.GET,headers="Accept=application/json")
	public ModelAndView getAllLeave() {
		ModelAndView model = new ModelAndView("viewAllLeave.html");
		model.addObject("leaves",leaveManagementService.getAllLeave());
		return model;
	}
	
	@RequestMapping(value="/leaveManagement/{empId}",method=RequestMethod.GET,headers="Accept=application/json")
	public List<LeaveManagement> getLeaveByEmpId(@PathVariable long empId) {
		return leaveManagementService.getLeaveByEmpId(empId);
	}
	
	@RequestMapping(value="/leaveManagement",method=RequestMethod.PUT,headers="Accept=application/json")
    public void updateLeave(@RequestBody LeaveManagement leaveManagement ) {
		leaveManagementService.updateLeave(leaveManagement);
    }
    
    //Delete Method
	@RequestMapping(value="/leaveManagement",method=RequestMethod.DELETE,headers="Accept=application/json")
    public void deleteLeave(@RequestBody LeaveManagement leaveManagement) {
		leaveManagementService.deleteLeave(leaveManagement);
    }
	
	@RequestMapping(value="/leaveManagement/{empId}/{date1}/{date2}",method=RequestMethod.GET,headers="Accept=application/json")
	public LeaveManagement getLeaveByEmpIdDate(@PathVariable long empId,@PathVariable String date1,@PathVariable String date2) {
		return leaveManagementService.getLeaveByEmpIdDate(empId, date1, date2);
	}
	
	@RequestMapping(value="/leaveManagementAttendance/{empId}",method=RequestMethod.GET,headers="Accept=application/json")
	public boolean addLeaveAttendance(@PathVariable long empId) throws ParseException {
		if(leaveManagementService.addLeaveAttendance(empId)) {
			return true;
		}
		return false;
	}*/
}