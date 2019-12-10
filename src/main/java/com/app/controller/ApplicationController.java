package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class ApplicationController {
	
	//bin/zookeeper-server-start.sh config/zookeeper.properties
	//bin/kafka-server-start.sh config/server.properties
	
	@RequestMapping("/process")
	public String process() {
		return "processing from admin control";
	}
}
