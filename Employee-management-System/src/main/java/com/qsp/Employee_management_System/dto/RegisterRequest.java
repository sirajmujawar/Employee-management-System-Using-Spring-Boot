package com.qsp.Employee_management_System.dto;

import com.qsp.Employee_management_System.entitylayer.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
	
	 @NotBlank
	    private String username;

	    @NotBlank
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String password;

	    private Role role;

}
