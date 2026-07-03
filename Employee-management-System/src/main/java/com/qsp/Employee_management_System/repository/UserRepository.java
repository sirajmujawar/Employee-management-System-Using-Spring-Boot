package com.qsp.Employee_management_System.repository;


import com.qsp.Employee_management_System.entitylayer.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByUsername(String username);

}
