package com.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
	
	private final Logger logger=LoggerFactory.getLogger(RabbitMQConsumer.class);
	@RabbitListener(queues = "${rabbitmq.queue}")
	public void getMessageFromQueue(String msg) {
		System.out.println("#############"+msg+"#############");
		logger.info("========consumed message===========",msg);
	}
}
