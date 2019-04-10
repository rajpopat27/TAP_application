package com.biomatiques.services;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomatiques.controller.AttendanceController;
import com.biomatiques.model.Attendance;
import com.biomatiques.model.Employee;
import com.biomatiques.model.Hours_worked_payroll;
import com.biomatiques.model.Shift;
import com.biomatiques.model.ShiftPattern;
import com.biomatiques.repository.AttendanceRepository;
import com.biomatiques.repository.EmployeeRepository;
import com.biomatiques.repository.HoursWorkedPayrollRepository;
import com.biomatiques.repository.ShiftPatternRepository;
import com.biomatiques.repository.ShiftRepository;

@Service
public class AttendanceService {

	 @Autowired
	    private AttendanceRepository attendanceRepository;
	 @Autowired
	 	private EmployeeRepository employeeRepository;
	 @Autowired
	    private ShiftPatternRepository shiftPatternRepository;
	 @Autowired
	    private ShiftRepository shiftRepository;
	 @Autowired
	 	private HoursWorkedPayrollRepository hoursWorkedRepository;
	 
	 @Autowired
	 	private AttendanceController attendanceController;
	 
	 DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("HH:mm:ss");
     
     String Starttime,Endtime,Currenttime;
     
	 long shiftId;
	 
	 boolean attendanceAdded = false;
	 
	 public List<Attendance> getAllAttendance() {
	        List<Attendance> attendanceList = new ArrayList<>();
	        attendanceRepository.findAll().forEach(attendanceList::add);
	        System.out.println(attendanceList.size());
	        return attendanceList;
	    }
	 
	 public List<Attendance> getAttendanceByEmployeeId(long employeeId) {
	        List<Attendance> attendanceList = new ArrayList<>();
	        attendanceRepository.findByEmployeeId(employeeId).forEach(attendanceList::add);
	        return attendanceList;
	    }
	 
	/* public List<Attendance> getAttendanceByEmployeeLastName(String employeeLastName) {
	        List<Attendance> attendanceList = new ArrayList<>();
	        attendanceRepository.findByemployeeLastName(employeeLastName).forEach(attendanceList::add);
	        return attendanceList;
	    }*/
	 public List<Hours_worked_payroll> getHoursWorked(){
		 List<Hours_worked_payroll> list = new ArrayList<>();
		 hoursWorkedRepository.findAll() .forEach(list::add);
		 return list;
	 }
	 
	 public List<Attendance> getAttendanceByEmployeeFirstName(String firstName) {
	        List<Attendance> attendanceList = new ArrayList<>();
	        attendanceRepository.findByEmployeeFirstName(firstName).forEach(attendanceList::add);
	        return attendanceList;
	    }
	 public boolean addAttendance(String EmpCode) throws ParseException, URISyntaxException {
		    EmpCode = EmpCode.replaceAll("^\"|\"$", "");
		    long id = Long.parseLong(EmpCode);
		 	Employee employee = employeeRepository.findById(id);
		 	ShiftPattern shiftPattern = shiftPatternRepository.findById(id);
		 	String day = LocalDate.now().getDayOfWeek().name().toLowerCase();
		 	switch (day) { 
	        case "monday": 
	            shiftId = shiftPattern.getMonday();
	            break; 
	        case "tuesday": 
	        	shiftId = shiftPattern.getTuesday();
	            break; 
	        case "wednesday": 
	        	shiftId = shiftPattern.getWednesday();
	            break; 
	        case "thursday": 
	        	shiftId = shiftPattern.getThursday();
	            break; 
	        case "friday": 
	        	shiftId = shiftPattern.getFriday();
	            break; 
	        case "saturday": 
	        	shiftId = shiftPattern.getSaturday();
	            break; 
	        case "sunday": 
	        	shiftId = shiftPattern.getSunday();
	            break; 
	        default: 
	        	shiftId = shiftPattern.getMonday();
	            break; 
	        } 
		 	Shift shift = shiftRepository.findById(shiftId);
		 	Starttime = shift.getStartTime().toString();
		 	Endtime = shift.getEndTime().toString();
		 	LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		 	Currenttime=dtf.format(now).toString();
		 	
		 	if(isTimeBetweenTwoTime(Starttime, Endtime, Currenttime)) {
		 		Attendance attendance = new Attendance();
			 	attendance.setEmployeeFirstName(employee.getFirstName());
			 	attendance.setEmployeeLastName(employee.getLastName());
			 	attendance.setEmployeeId(employee.getId());
			 	attendance.setHoursCalculated(false);
		        attendanceRepository.save(attendance); 	
		        attendanceAdded = true;
		 	}
		 	else {
		 		attendanceAdded=false;
		 	}
		 return attendanceAdded;
	    }
	 
	 /*public void addAttendance(byte[] irisId) {
		 	Employee employee = employeeRepository.findByIrisId(irisId);
		 	Attendance attendance = new Attendance();
		 	attendance.setEmployeeFirstName(employee.getFirstname());
		 	attendance.setEmployeeLastName(employee.getLastname());
		 	attendance.setEmployeeId(employee.getId());
	        attendanceRepository.save(attendance);
	    }*/

	public List<Attendance> getAttendanceByDate(String date) {
		List<Attendance> attendanceList = new ArrayList<>();
		attendanceRepository.findAttendanceByDate(date).forEach(attendanceList::add);
		return attendanceList;
	}
	
	public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
            boolean valid = false;
            //Start Time
            java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(inTime);

            //Current Time
            java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(checkTime);

            //End Time
            java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(finTime);

            if (finalTime.compareTo(initialTime) < 0) {
                calendar2.add(Calendar.DATE, 1);
                calendar3.add(Calendar.DATE, 1);
            }

            java.util.Date actualTime = calendar3.getTime();
            if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0) 
                    && actualTime.before(calendar2.getTime())) {
                valid = true;
            }
            return valid;
        } else {
            throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
        }

    }
	
	
}
