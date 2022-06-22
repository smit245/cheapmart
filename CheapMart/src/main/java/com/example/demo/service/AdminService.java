package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Admin;
import com.example.demo.model.User;

public interface AdminService {
	
	List<Admin> getAdminByEmail(String email);
	
	

	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("update users u set u.status = :status where u.id = :id") void
	 * updatestatus(@Param(value = "id") long id, @Param(value = "status") long
	 * status);
	 */
}
