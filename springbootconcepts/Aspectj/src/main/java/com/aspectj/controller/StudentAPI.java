 package com.aspectj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aspectj.exception.ErrorResponse;
import com.aspectj.exception.StudentAlreadyExistsException;
import com.aspectj.model.ResponseObject;
import com.aspectj.model.Student;
import com.aspectj.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentAPI {
	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<ResponseObject> addStudent(@RequestBody Student student) throws StudentAlreadyExistsException {
		
			ResponseObject responseObject = new ResponseObject();
			studentService.addStudent(student);
			responseObject.setStatusCode("201");
			responseObject.setMessage("Student profile added");
			responseObject.setUrl("/add-student");
			responseObject.setResponse(student);
			return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
		

	}

	@GetMapping("/get/{studentId}")

		public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {

		Student student = null;

		if (studentId != null) {
			student = studentService.getStudentById(studentId);
			if (student == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<Student>> getAllStudent() {
		HttpHeaders header = new HttpHeaders();
		header.add("description", "List of all student");
		header.add("type", "Student object");
		List<Student> studentList = studentService.getAllStudents();
		if (studentList == null) {
			return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(studentList);
		}
	}
	
//	@GetMapping("/get-all")
//	public ResponseEntity<List<Student>> getAllStudent() {
//		List<Student> studentList = studentService.getAllStudents();
//		if (studentList == null) {
//			return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
//		} else {
//			return new ResponseEntity<>(studentList,HttpStatus.OK);
//		}
//	}

	@PutMapping("/{studentId}/update")
	public ResponseEntity<ResponseObject> updateStudentAccount(@PathVariable Long studentId,
			@RequestBody Student student) {
		ResponseObject responseObject = new ResponseObject();
		if (studentId == null && student == null) {
			responseObject.setMessage("enter valid data");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
		} else {
			studentService.updateStudentAccount(studentId, student);
			responseObject.setStatusCode("200");
			responseObject.setMessage("Profile Updated");
			return ResponseEntity.status(HttpStatus.OK).body(responseObject);
		}

	}

	@DeleteMapping("/{studentId}/delete-student")
	public ResponseEntity<ResponseObject> deleteStudent(@PathVariable Long studentId) {
		ResponseObject responseObject = new ResponseObject();
		if (studentId != null) {
			Student student = studentService.getStudentById(studentId);
			if (student == null) {
				responseObject.setStatusCode("404");
				responseObject.setResponse("not found");
				responseObject.setMessage("id is not present");
				responseObject.setUrl("student/delete-student");
				return new ResponseEntity<>(responseObject, HttpStatus.NOT_FOUND);
			} else {
				studentService.deleteStudent(studentId);
				responseObject.setStatusCode("200");
				responseObject.setResponse("deleted");
				responseObject.setMessage("deleted");
				responseObject.setUrl("student/delete-student");
				return new ResponseEntity<>(responseObject, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(value = StudentAlreadyExistsException.class )
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleStudentAlreadyExistsException(StudentAlreadyExistsException e) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
	}

}
