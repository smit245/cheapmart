package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

}

