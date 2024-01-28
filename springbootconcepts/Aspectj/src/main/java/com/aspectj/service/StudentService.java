package com.aspectj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspectj.exception.NoSuchStudentExistsException;
import com.aspectj.exception.StudentAlreadyExistsException;
import com.aspectj.model.Student;
import com.aspectj.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	

	public Student addStudent(Student student) throws StudentAlreadyExistsException {
		Optional<Student> existingStudent = studentRepository.findById(student.getStudentId());
		if(existingStudent.isPresent()) {
			throw new StudentAlreadyExistsException("This student details is already exist");

		}else {
			return studentRepository.save(student);
		}
	}
	
	

	public Student getStudentById(Long StudentId) throws NoSuchStudentExistsException {
		Optional<Student> student = studentRepository.findById(StudentId);
		if(student.isPresent()) {
			return student.get();
		}else {
			throw new NoSuchStudentExistsException("No Student is present");
		}
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public void deleteStudent(Long studentId) {
//		Optional<Student> student = studentRepository.findById(studentId);
		studentRepository.deleteById(studentId);
	}

	public Student updateStudentAccount(Long studentId, Student student) {
		Optional<Student> studentDetails = studentRepository.findById(studentId);
		if (studentDetails.isPresent()) {
			studentDetails.get().setFirstName(student.getFirstName());
			studentDetails.get().setSecondName(student.getSecondName());
			studentDetails.get().setAge(student.getAge());
			studentDetails.get().setBranch(student.getBranch());
			return studentRepository.save(student);

		}
		return student;

	}
	public Student updateStudentAccount2(Long studentId, Student student) {
		Optional<Student> studentDetails = studentRepository.findById(studentId);
		Student student2 = new Student();
		if (studentDetails.isPresent()) {
			student2 = studentDetails.get();
			student2.setFirstName(student.getFirstName());
			student2.setSecondName(student.getSecondName());
			student2.setAge(student.getAge());
			student2.setBranch(student.getBranch());
			return studentRepository.save(student2);

		}
		return student;

	}

}
