package com.qsp.Employee_management_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import com.qsp.Employee_management_System.entitylayer.Employee;
import com.qsp.Employee_management_System.repository.EmployeeRepository;

@Repository
public class EmployeeDao {
	@Autowired
	private EmployeeRepository employeerepository;
	
	public Employee saveEmployeedao(Employee emp) {
		return employeerepository.save(emp);
	}
	
	
	//design a method find employee by id
	public Employee getEmployeeByIdDao(int empid) {
		Optional<Employee>emp=employeerepository.findById(empid);
		
		if(emp.isPresent()) {
			Employee employee=emp.get();
			return employee;
		}else {
			return null;
		}
	}
	
		
		
		
		//design method to delete employee data based on id
		public boolean deleteEmployeeByIdDao(int empid) {
		if(employeerepository.existsById(empid)) {
			employeerepository.deleteById(empid);
			return true;
		}else {
			
		return false;
		
		}
	}
		
		
		
		
		
		//design method to update the record
		public Employee updateEmployeedao(Employee emp) {
			return employeerepository.save(emp);
		}
		
		
		
		//design method to save the all the employee record
		
		public List<Employee>saveListEmployeeDao(List<Employee>emp){
			return employeerepository.saveAll(emp);
		}
		
		
		//design a method to implement pagination and sorting
		public Page<Employee>getEmployeeusingPaginationandSorting(int page,int size){
			Pageable pageable=PageRequest.of(page, size,Sort.by("name").descending());
			
			return employeerepository.findAll(pageable);
		}
		
		
		
		
		
		//design a method to fetch employee record based on pattern
		public List<Employee>getEmployeeByAddressDao(String location){
			return employeerepository.findByLocationContainingIgnoringCase(location);
		}
	

}
