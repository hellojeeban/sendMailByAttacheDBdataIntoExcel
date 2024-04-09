package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportService {

	@Autowired
	private StudentRepo srepo;
	
	public File genetaredExcel(HttpServletResponse responce) throws IOException {
		List<Student> StdData = srepo.findAll();
		
		HSSFWorkbook  workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Student Data");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("PERCENTAGE");
		
		int dataRowIndex = 1;
		
		for(Student data : StdData) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(data.getId());
			dataRow.createCell(1).setCellValue(data.getName());
			dataRow.createCell(2).setCellValue(data.getPerc());
			
			dataRowIndex++;
		}
		

		// Write workbook to a temporary file
		File tempFile = File.createTempFile("stdinfo", ".xls");
		FileOutputStream fileOut = new FileOutputStream(tempFile);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

		return tempFile;
	}
}
