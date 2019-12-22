package com.app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Autowired
	public RabbitTemplate template;
	
	@Value("${rabbitmq.queue}")
	private String queueNqme;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey}")
	private String routingKey;
	

	public void produceMessage(String msg) {
		template.convertAndSend(exchange,routingKey, msg);
	}
	
	@Bean
	Queue createQueue() {
		return new Queue(queueNqme,false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	Binding bind(Queue q,TopicExchange e) {
		return BindingBuilder.bind(q).to(e).with(routingKey);
	}
	

}
