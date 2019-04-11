package com.biomatiques.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomatiques.model.Employee;
import com.biomatiques.model.Hours_worked_payroll;
import com.biomatiques.model.Payroll;
import com.biomatiques.repository.EmployeeRepository;
import com.biomatiques.repository.HoursWorkedPayrollRepository;
import com.biomatiques.repository.PayrollRepository;

@Service
public class PayrollService {

	@Autowired
	PayrollRepository payrollRepository;
	@Autowired
	HoursWorkedPayrollRepository hoursRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Payroll> getAllPayroll() {
        List<Payroll> payrollList = new ArrayList<>();
        payrollRepository.findAll().forEach(payrollList::add);
       // System.out.println(payrollList.size());
        return payrollList;
    }
	
	 public void generatePayrollHours() {
		hoursRepository.generatePayrollHours();
		hoursRepository.updateHoursCalculated();
		calculateSalary();
		hoursRepository.updatePayrollGenerated();		
	 }
	 
	 public List<Payroll> getPayrollForReport(){
		 List<Payroll> payrollList = new ArrayList<>();
		 payrollList=payrollRepository.getEmployeesForReportGeneration();
		 payrollRepository.updateReportGenerated();
		 return payrollList;
	 }
	 
	 
	 public List<Payroll> getRemainingPayroll(){
		 List<Payroll> payrollList = new ArrayList<>();
		 payrollList=payrollRepository.getRemainingPayroll();
		 return payrollList;
	 }
	 
	 public List<Payroll> getGeneratedPayroll(){
		 List<Payroll> payrollList = new ArrayList<>();
		 payrollList=payrollRepository.getGeneratedPayroll();
		 return payrollList;
	 }
	 
	 public List<Hours_worked_payroll> getHoursWorked() {
		 List<Hours_worked_payroll> hoursWorked = new ArrayList<>();
		 hoursWorked = hoursRepository.getHoursWorked();
		 List<Hours_worked_payroll>temp = hoursWorked.stream()
		         .collect(Collectors.groupingBy(Hours_worked_payroll::getEmp_id))
		         .entrySet().stream()
		         .map(e -> e.getValue().stream()
		             .reduce((f1,f2) -> new Hours_worked_payroll(f1.getEmp_id(),f1.getNo_of_hours() + f2.getNo_of_hours())))
		             .map(f -> f.get())
		             .collect(Collectors.toList());
		 return temp;
	 }
	 
	 
	 
	 
	 public void calculateSalary() {
		 List<Hours_worked_payroll> hoursWorkedList = new ArrayList<>();
		 hoursWorkedList = hoursRepository.getEmployeesForPayrollGeneration();
		 List<Hours_worked_payroll>temp = hoursWorkedList.stream()
									         .collect(Collectors.groupingBy(Hours_worked_payroll::getEmp_id))
									         .entrySet().stream()
									         .map(e -> e.getValue().stream()
									             .reduce((f1,f2) -> new Hours_worked_payroll(f1.getEmp_id(),f1.getNo_of_hours() + f2.getNo_of_hours())))
									             .map(f -> f.get())
									             .collect(Collectors.toList());
		 
		 for(int i=0;i<temp.size();i++) {			 
				 long id  = temp.get(i).getEmp_id();
				 float hours = temp.get(i).getNo_of_hours();
				 Employee employee = employeeRepository.findById(id);
				 float salary = employee.getSalary();
				 salary = salary*hours;
				 Payroll payroll = new Payroll();
				 payroll.setEmployeeId(id);
				 payroll.setHours(hours);
				 payroll.setSalary(salary);
				 payroll.setReport_generated(false);
				 payrollRepository.save(payroll);			 
		 }	
	 }
}
		 		
