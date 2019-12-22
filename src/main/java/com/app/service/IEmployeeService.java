package com.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Employee;

public interface IEmployeeService {

	ResponseEntity<Employee> saveEmps(MultipartFile file);

}
