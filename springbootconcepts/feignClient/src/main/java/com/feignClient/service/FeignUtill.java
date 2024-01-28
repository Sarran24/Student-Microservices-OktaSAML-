package com.feignClient.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aspectj.model.ResponseObject;
import com.aspectj.model.Student;

@FeignClient(value = "StudentCient" ,url = "http://localhost:8080/student")

public interface FeignUtill {
	
//	@RequestMapping(method = RequestMethod.GET, value = "/get-all")
//	ResponseEntity<List<Student>> getAllStudent();
	
	@GetMapping("/get/{studentId}")
	ResponseEntity<Student> getStudentById(@PathVariable Long studentId);	
	
	@GetMapping("/get-all")
	ResponseEntity<List<Student>> getAllStudent();	
//	
//	@DeleteMapping("/{studentId}/delete-student")
//	ResponseEntity<ResponseObject> deleteStudent(@PathVariable Long studentId);
}
