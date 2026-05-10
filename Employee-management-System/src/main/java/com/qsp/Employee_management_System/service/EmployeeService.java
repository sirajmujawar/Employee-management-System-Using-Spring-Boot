package com.qsp.Employee_management_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.Employee_management_System.dao.EmployeeDao;
import com.qsp.Employee_management_System.entitylayer.Employee;
import com.qsp.Employee_management_System.exception.IdValidationException;
import com.qsp.Employee_management_System.responsestructure.ResponseStructure;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeedao;
	
	//call dao layer method to save the data
	public Employee saveEmployeeService(Employee emp) {
		return employeedao.saveEmployeedao(emp);
	}
	

	//call dao layer method to find Employee based on empid
	public Employee getEmployeeByIdService(int empid){
		if(empid>0) {
			return employeedao.getEmployeeByIdDao(empid);
			
		}else {
			throw new IdValidationException("given id is negative");
		}
	}
		
	
		
		//call dao layer method to delete employee based on id
		public boolean deleteEmployeeByIdService(int empid) {
			return employeedao.deleteEmployeeByIdDao(empid);
		
		
		}
		
		
		//call dao layer method to update employee data
		
		public Employee updateEmployeeservice(Employee emp) {
			return employeedao.updateEmployeedao(emp);
		}
		
		
		
		//call dao layer method to save all employee data
			public List<Employee>saveListEmployeeservice(List<Employee>emp){
				return  employeedao.saveListEmployeeDao(emp);
			}
			
			
			
			
		//call dao layer a method to execute pagination and sorting
			public Page<Employee>getEmployeeusingPaginationandSortingService(int page,int size){
				return employeedao.getEmployeeusingPaginationandSorting(page, size);
			}
			
			
			public List<Employee>getEmployeeByAddressService(String location){
				return employeedao.getEmployeeByAddressDao(location);
			}
		
	}



