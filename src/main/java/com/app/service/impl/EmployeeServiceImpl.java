package com.app.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Employee;
import com.app.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl{

	@Autowired
	private EmployeeRepo emprepo;

	Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);

	Object target;

	@Async
	public CompletableFuture<List<Employee>> saveEmps(MultipartFile file) throws Exception{
		long start=System.currentTimeMillis();
		List<Employee> emps = parseCSVFile(file);
		logger.info("saving list of empss of size {}", emps.size(), "" + Thread.currentThread().getName());
		emps = emprepo.saveAll(emps);
		long end=System.currentTimeMillis();
		logger.info("Total time {}", (end - start));
		return CompletableFuture.completedFuture(emps);
	}

	@Async
	public CompletableFuture<List<Employee>> findAllEmps(){
		logger.info("get list of emps by "+Thread.currentThread().getName());
		List<Employee> users=emprepo.findAll();
		return CompletableFuture.completedFuture(users);
	}

	private List<Employee > parseCSVFile(final MultipartFile file) throws Exception{

		final List<Employee> emps=new ArrayList<>();
		try {
			try(final BufferedReader br= new BufferedReader(new InputStreamReader(file.getInputStream()))){
				String line ;
				while((line=br.readLine())!=null) {
					final String[] data=line.split(",");
					final Employee emp=new Employee();
					emp.setEmpId(data[0]);
					emp.setFirst_name(data[1]);
					emp.setLast_name(data[2]);
					emp.setEmail(data[3]);
					emp.setGender(data[4]);
					emp.setIp_address(data[5]);
					emps.add(emp);
				}
				return emps;
			}
		}
		catch (Exception e) {
			logger.error("Failed to parse CSV file {}", e);
			throw new Exception("Failed to parse CSV file {}", e);
		}
	}
}
