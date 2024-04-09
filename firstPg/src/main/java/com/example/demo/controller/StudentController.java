package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.ReportService;
import com.example.demo.service.SenderService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class StudentController {
	
	@Autowired
	private StudentRepo srepo;
	
	@Autowired
	private ReportService service;
	
	@Autowired
	private SenderService sendService;
	
	
	@PostMapping("/addStd")
	public void postMethodName(@RequestBody Student s) {
		
		System.out.println(s.getName());
		System.out.println(s.getPerc());
		srepo.save(s);
	}
	
	@GetMapping("/path")
	public String getMethodName() {
		return "Started";
	}
	
	/*
	 * @GetMapping("/excel") public void generateExcelReport(HttpServletResponse
	 * response) throws IOException {
	 * 
	 * response.setContentType("application/octet-strem");
	 * service.genetaredExcel(response); // String headerKey =
	 * "Content-Disposition"; // String headerValue =
	 * "attachment;filename=stdinfo.xls"; // // response.setHeader(headerKey,
	 * headerValue);
	 * 
	 * 
	 * 
	 * }
	 */
	
	@GetMapping("send")
	public void sendMail(HttpServletResponse response) throws MessagingException, IOException {
		sendService.sendEmail("biswaljeebanjyoti999@gmail.com", "Report", "This is th body", response);
	}
	
	
}
