package com.feignClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspectj.model.ResponseObject;
import com.aspectj.model.Student;
import com.feignClient.service.FeignUtill;

@RestController
@RequestMapping("/demo")
public class FeignController {
	
	@Autowired
	private FeignUtill feignUtill;

	@GetMapping("/get/{studentId}")
	ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
		return feignUtill.getStudentById(studentId);
	}

	@GetMapping("/get-all")
	ResponseEntity<List<Student>> getAllStudent(){
		return feignUtill.getAllStudent();
	}

//	@GetMapping("/deleteById/{id}")
//	ResponseEntity<ResponseObject> deleteStudent(@PathVariable Long studentId){
//		return feignUtill.deleteStudent(studentId);
//	}

}
