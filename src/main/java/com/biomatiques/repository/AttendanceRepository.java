package com.biomatiques.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.biomatiques.model.Attendance;

public interface AttendanceRepository extends CrudRepository <Attendance,Long> {
	
	public List<Attendance> findByEmployeeId(long id);
	
	public List<Attendance> findByEmployeeFirstName(String  employeeFirstName);
	
	//public List<Attendance> findByemployeeLastName(String employeeLastName);*/
	@Query(value="SELECT * FROM attendance a WHERE date(a.attended_date) = :date " ,nativeQuery = true)
	public List<Attendance> findAttendanceByDate(@Param("date") String date);
	
	@Query(value="SELECT * FROM attendance ORDER by id desc limit 5 ",nativeQuery=true)
	public List<Attendance> liveClockIn();

}
