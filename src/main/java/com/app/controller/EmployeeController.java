package com.app.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.config.RabbitMQConfig;
import com.app.model.Employee;
import com.app.service.impl.EmployeeServiceImpl;


@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private  EmployeeServiceImpl service;
	
	//@Autowired
	private RabbitMQConfig  config;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/data",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = "application/json")
	public ResponseEntity saveEmps(@RequestParam(value="files")MultipartFile[] files) throws Exception {
		for(MultipartFile file:files) {
			service.saveEmps(file);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@GetMapping(value = "/emps",produces = "application/json")
	public  CompletableFuture<ResponseEntity> findAllEmps(){
		return service.findAllEmps().thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/allemps",produces = "application/json")
	public List<CompletableFuture<List<Employee>>> finadAllEmpsByAsync() {
		CompletableFuture<List<Employee>> emp1=(CompletableFuture<List<Employee>>)service.findAllEmps();
		CompletableFuture<List<Employee>> emp2=(CompletableFuture<List<Employee>>)service.findAllEmps();
		CompletableFuture<List<Employee>> emp3=(CompletableFuture<List<Employee>>)service.findAllEmps();
		List<CompletableFuture<List<Employee>>> result=new ArrayList<>();

		result.add(emp1);result.add(emp2);result.add(emp3);
		//return CompletableFuture.allOf(emp1,emp2,emp3).join();
		return  result;
		//return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping(value="/send")
	public String sendMessage(@RequestParam("msg")String msg) {
		config.produceMessage(msg);
		return "Message sended";
	}
	
	
	
}
