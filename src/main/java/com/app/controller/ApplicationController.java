package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class ApplicationController {
	
	//======Zookeeper and Kafka server startup steps======
	//bin/zookeeper-server-start.sh config/zookeeper.properties
	//bin/kafka-server-start.sh config/server.properties
	
	//======creating kafka-topic from terminal========
	
	//bin/kafka-topics.sh --create --topics niharika --partitions 3 --replication-factor 1 --zookeeper localhost:2181
	
	//=====to list kafka topics=======
	//bin/kafka-topics.sh --list --zookeeper localhost:2181
	
	//bin/kafka-topics.sh --describe niharika --zookeeper localhost:2181
	
	//====kafka producer from console========
	//bin/kafka-console-producer.sh --broker-list localhost:9092 --topic niharika
	
	//====kafka connsumer from console=======
	//bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic niharika --from-begining
	@RequestMapping("/process")
	public String process() {
		return "processing from admin control";
	}
}
