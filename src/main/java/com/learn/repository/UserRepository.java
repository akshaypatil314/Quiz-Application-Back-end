package com.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{
	
	public List<Users> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	public Users getUserByUsername(String username);

}
