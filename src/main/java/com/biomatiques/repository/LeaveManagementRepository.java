package com.biomatiques.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.biomatiques.model.Attendance;
import com.biomatiques.model.LeaveManagement;

public interface LeaveManagementRepository extends CrudRepository<LeaveManagement, Long>{

	public List<LeaveManagement> findLeaveByEmpId(long empId);
	
	@Query(value="SELECT * FROM leave_management a WHERE a.emp_id=:empId AND a.date1=:date1 AND a.date2=:date2 " ,nativeQuery = true)
	public LeaveManagement findByEmpId(@Param("empId") long empId,@Param("date1") String date1,@Param("date2") String date2);
	
	@Query(value="SELECT leave_id FROM leave_management a WHERE a.emp_id=:empId AND a.date1=:date1 AND a.date2=:date2 " ,nativeQuery = true)
	public long findId(@Param("empId") long empId,@Param("date1") String date1,@Param("date2") String date2);
}