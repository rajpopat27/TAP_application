package com.biomatiques.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomatiques.model.LeaveManagement;
import com.biomatiques.model.ShiftSwap;
import com.biomatiques.repository.LeaveManagementRepository;

@Service
public class LeaveManagementService {

	
	@Autowired
	LeaveManagementRepository leaveManagementRepository;
	
	public void addLeave(LeaveManagement leaveManagement) throws ParseException {
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(leaveManagement.getDate1());
		Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(leaveManagement.getDate2());
		if (!(date1.compareTo(date2) > 0))
			if (date1.compareTo(new Date())>=0) {
				leaveManagementRepository.save(leaveManagement);
			}
		
	}

	public List<LeaveManagement> getAllLeave() {
		List<LeaveManagement> leaveList = new ArrayList<>();
        leaveManagementRepository.findAll().forEach(leaveList::add);
        return leaveList;
	}
	public LeaveManagement getLeaveById(Long id) {
		return leaveManagementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));		
	}
	public List<LeaveManagement> getLeaveByEmpId(long empId) {
		List<LeaveManagement> leaveList = new ArrayList<>();
		leaveManagementRepository.findLeaveByEmpId(empId).forEach(leaveList::add);
		return leaveList;
	}
	
	 public void updateLeave(LeaveManagement leaveManagement) {
	    	leaveManagementRepository.save(leaveManagement);
	    }
	    public void deleteLeave(long id) {
	     /*leaveManagement.setId(leaveManagementRepository.findId(leaveManagement.getEmpId(), leaveManagement.getDate1(),leaveManagement.getDate2()));
    	 leaveManagementRepository.delete(leaveManagement);*/
	    	leaveManagementRepository.deleteById(id);
	    }
	    
    public boolean addLeaveAttendance(long empId) throws ParseException {
    	LeaveManagement temp = new LeaveManagement();
    	Date date1;
    	Date date2;
    	Date today = new Date();
    	List<LeaveManagement> leaveList = new ArrayList<>();
		leaveManagementRepository.findLeaveByEmpId(empId).forEach(leaveList::add);
		for(int i=0;i<leaveList.size();i++) {
			temp = leaveList.get(i);
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(temp.getDate1());
			date2 = new SimpleDateFormat("dd/MM/yyyy").parse(temp.getDate2());
			if((date1.compareTo(today)<=0 )&&(date2.compareTo(today)>=0)) {
				return true;
			}
		}
		return false;		
    }
    
    
}