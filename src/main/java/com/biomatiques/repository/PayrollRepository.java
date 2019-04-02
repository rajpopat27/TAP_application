package com.biomatiques.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.biomatiques.model.Hours_worked_payroll;
import com.biomatiques.model.Payroll;


@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Long>{
	@Modifying
	@Transactional
	@Query(value="update payroll set report_generated = 1 WHERE report_generated=0",nativeQuery=true)
	public void updateReportGenerated();
	
	@Query(value = "Select * from payroll where report_generated=0 ",nativeQuery=true)
	public List<Payroll> getEmployeesForReportGeneration();
}
