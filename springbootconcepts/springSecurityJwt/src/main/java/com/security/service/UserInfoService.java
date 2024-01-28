package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.model.UserInfo;
import com.security.repository.UserInfoRepo;

@Service
public class UserInfoService {
	
	@Autowired
	UserInfoRepo userInfoRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserInfo saveUserInfo(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		return userInfoRepo.save(userInfo);
	}
}
