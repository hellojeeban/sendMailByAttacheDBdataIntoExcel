package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ReportService service;
	
	public void sendEmail(String toEmail, String subject, String body, HttpServletResponse response) throws IOException, MessagingException {
		 MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(body);

	        // Generate and attach the Excel file
	        File file = service.genetaredExcel(response); // Assuming service.genetaredExcel() returns the file
	        helper.addAttachment("stdinfo.xls", file);

		
		response.setContentType("application/octet-strem");
		service.genetaredExcel(response);
		mailSender.send(message);
		
		System.out.println("Mail Send....");
	}
}
