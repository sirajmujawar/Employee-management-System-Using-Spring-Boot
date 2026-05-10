package com.qsp.Employee_management_System.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.Employee_management_System.entitylayer.Employee;
import com.qsp.Employee_management_System.responsestructure.ResponseStructure;
import com.qsp.Employee_management_System.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//design api to save the record
	@PostMapping("/employees")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployeeController(@RequestBody Employee emp) {
    ResponseStructure<Employee> rs=new ResponseStructure<>();
    Employee employee=employeeService.saveEmployeeService(emp); 
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Data is successfully inserted...");
        rs.setData(employee);

     return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.CREATED);
	
		
	}
	
	//design api to fetch record based on id
	@GetMapping("/employees/{empid}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeBasedIdController(@PathVariable int empid) {
		
		Employee emp=employeeService.getEmployeeByIdService(empid);
		if(emp!=null) {
			ResponseStructure<Employee>rs=new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("data found succesfully");
		    rs.setData(emp);
		
		return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.OK);
	}else {
		ResponseStructure<Employee>rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("given id is not found");
		rs.setData(emp);
		
		return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.NOT_FOUND);
	}
		
 }
		
		
		
		//design a api to delete record by id
				@DeleteMapping("/employees/{empid}")
				public ResponseEntity<ResponseStructure<Boolean>> deleteEmployeByIdController(@PathVariable int empid){
					
					boolean b=employeeService.deleteEmployeeByIdService(empid);
					if(b) {
						ResponseStructure<Boolean>rs=new ResponseStructure<>();
						rs.setStatusCode(HttpStatus.OK.value());
						rs.setMessage("data deleted succesfully");
					    rs.setData(b);
					    
					    return new ResponseEntity<ResponseStructure<Boolean>>(rs,HttpStatus.OK);
						
					}else {
						ResponseStructure<Boolean>rs=new ResponseStructure<>();
						rs.setStatusCode(HttpStatus.NOT_FOUND.value());
						rs.setMessage("given id is not found");
						rs.setData(b);
						
						return new ResponseEntity<ResponseStructure<Boolean>>(rs, HttpStatus.NOT_FOUND);
						
					}
					
		
			}
				
				
				//design a end point/api to update record
				@PutMapping("/employees")
				public ResponseEntity<ResponseStructure<Employee>> updateEmployeeRecordcontroller(@RequestBody Employee emp) {
					Employee employee=employeeService.updateEmployeeservice(emp);
					
					 ResponseStructure<Employee> rs=new ResponseStructure<>();
					        rs.setStatusCode(HttpStatus.CREATED.value());
					        rs.setMessage("Data is updated successfully ...");
					        rs.setData(employee);

					     return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.CREATED);
	

	}
				
	
				//design api to save all employee record
				@PostMapping("/employees/all")
				public ResponseEntity<ResponseStructure<List<Employee>>> saveListEmployeeController(@RequestBody List<Employee> emp) {
					//save the data into database
				List<Employee>employee=employeeService.saveListEmployeeservice(emp);
			    ResponseStructure<List<Employee>> rs=new ResponseStructure<>();
			        rs.setStatusCode(HttpStatus.CREATED.value());
			        rs.setMessage("Data is successfully saved");
			        rs.setData(employee);

			     return new ResponseEntity<ResponseStructure<List<Employee>>>(rs,HttpStatus.CREATED);
				
   }
				
				
				
				//design endpoint to perform pagination and sorting
				@GetMapping("/employees")
				public Page<Employee>getEmployeeusingPaginationandSortingService(@RequestParam int page, @RequestParam int size){
					return employeeService.getEmployeeusingPaginationandSortingService(page, size);
				}

				
				
				
				
			
				  //design a end point to fetch data based on patterns
				@GetMapping("/employees/location/{location}")
				public List<Employee> getEmployeeByAddressController(@PathVariable String location) {
				    return employeeService.getEmployeeByAddressService(location);
				}
				
				
        }



        
      

