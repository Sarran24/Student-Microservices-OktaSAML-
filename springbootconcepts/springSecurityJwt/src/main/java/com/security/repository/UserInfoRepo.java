package com.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.UserInfo;

@Repository 
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByName(String username);
	

}
