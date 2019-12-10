package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
    public KafkaController(KafkaTemplate<String, User> kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	@RequestMapping("/data")
	public String getdata(@RequestBody User user) {
		kafkaTemplate.send("kafkatopic1", user);
		System.out.println(user.getUserEmail()+ " ********** "+ user.getUserName());
		return "success";
	}
	
	
	@RequestMapping("/consumer")
	public User getConsumerData(){
		return userdata;
	}
	
	User userdata=null;
	
	@KafkaListener(groupId = "group1",topics = "kafkatopic1",containerFactory = "kafkalistener")
	public User getMsgFromTopic(User user){
		userdata=user;
		return userdata;
		
	}
	
}
