package com.biomatiques.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.biomatiques.model.Employee;
import com.biomatiques.model.Iris;
import com.biomatiques.repository.EmployeeRepository;

@Service
public class EmployeeService {
   
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }
   
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    
    public void updateEmployee(Employee employee) {
    	employeeRepository.save(employee);
    }
    public void deleteEmployee(long id) {
    	employeeRepository.deleteById(id);
    }

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));		
	}
	
	public void addIris(Iris iris) {
		Employee employee = employeeRepository.findById(iris.getId());
		 byte[] iris1 = iris.getLeftIris();
		employee.setIrisId(iris1);
		employeeRepository.save(employee);
	}
	
	
	
	//Pagination
	public Page<Employee> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        List<Employee> list;
 
        if (employees.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, employees.size());
            list = employees.subList(startItem, toIndex);
        }
 
        Page<Employee> bookPage
          = new PageImpl<Employee>(list, PageRequest.of(currentPage, pageSize), employees.size());
 
        return bookPage;
    }
	
	
}