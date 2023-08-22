package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	@Query("select u from User u where u.username = :username")
	User getUserByUsername(@Param("username") String username);
	
	@Query("select count(u) from User u where u.roles = :roles")
	Integer getCountUsersByRole(@Param("roles")String roles);
	
}
