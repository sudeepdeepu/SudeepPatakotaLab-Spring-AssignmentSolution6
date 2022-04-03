package com.greatLearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		List<Student> theStudents = studentService.findAll();
		theModel.addAttribute("Students",theStudents);
		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student",theStudent);
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,Model theModel) {
		Student theStudent = studentService.findById(theId);
		theModel.addAttribute("Student",theStudent);
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,@RequestParam("name") String name,
			@RequestParam("department") String department,@RequestParam("country") String country) {
		Student theStudent;
		if(id!=0) {
			theStudent=studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		}else
			theStudent= new Student(name,department,country);
		studentService.save(theStudent);
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		studentService.deleteById(theId);
		return "redirect:/student/list";
	}
}
