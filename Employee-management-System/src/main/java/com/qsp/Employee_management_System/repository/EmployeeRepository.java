package com.qsp.Employee_management_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.Employee_management_System.entitylayer.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	
	List<Employee>findByLocationContainingIgnoringCase(String address);

}
