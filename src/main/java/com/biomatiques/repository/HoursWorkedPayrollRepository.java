package com.biomatiques.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.biomatiques.model.Hours_worked_payroll;

@Repository
public interface HoursWorkedPayrollRepository extends CrudRepository<Hours_worked_payroll, Long>{
	@Modifying
	@Transactional
	@Query(value="insert into hours_worked (emp_id,time_out,time_in,no_of_hours) select  t.employee_id\r\n" + 
			"        ,max(concat(t.attended_date, ' ', t.attended_time)) as time_out\r\n" + 
			"        ,min(concat(t.attended_date, ' ', t.attended_time)) as time_in\r\n" + 
			"        ,time_to_sec(timediff(max(concat(t.attended_date, ' ', t.attended_time)), min(concat(t.attended_date, ' ', t.attended_time)))) / 3600 \r\n" + 
			"            as No_of_hours\r\n" + 
			"from \r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"    floor(@row1 \\:= @row1 + 0.5) as day,\r\n" + 
			"    t.*\r\n" + 
			"    FROM attendance t,\r\n" + 
			"     (SELECT @row1 \\:= 0.5) r1\r\n" + 
			"    order by concat(t.attended_date, ' ', t.attended_time)\r\n" + 
			") t where t.hours_calculated = 0\r\n" + 
			"group by t.day,t.employee_id\r\n" + 
			"",nativeQuery=true)
	public void generatePayrollHours();
	
	@Modifying
	@Transactional
	@Query(value="update attendance set hours_calculated = 1 WHERE hours_calculated=0",nativeQuery=true)
	public void updateHoursCalculated();
	//SELECT emp_id,SUM(no_of_hours) as no_of_hours FROM hours_worked WHERE payroll_generated = 0 GROUP BY emp_id
	@Query(value = "Select * from hours_worked where payroll_generated=0 ",nativeQuery=true)
	public List<Hours_worked_payroll> getEmployeesForPayrollGeneration();
	@Modifying
	@Transactional
	@Query(value="update hours_worked set payroll_generated = 1 WHERE payroll_generated=0",nativeQuery=true)
	public void updatePayrollGenerated();

	@Query(value="Select * from hours_worked where payroll_generated=0",nativeQuery=true)
	public List<Hours_worked_payroll> getHoursWorked();
}
