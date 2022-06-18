package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Admin;
import com.example.demo.model.User;

public interface AdminService {
	
	Optional<Admin> getAdminByEmail(String email);
	
	List<User> getAllUserInfo();
	Optional<User> getUserByID(long id);
	void delete(long id);
	User getUserbyId(long id);
	void saveUser(User user);

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
