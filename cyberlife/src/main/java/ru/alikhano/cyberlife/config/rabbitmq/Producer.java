package ru.alikhano.cyberlife.config.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	@Autowired
	private final RabbitTemplate rabbitTemplate;
	
    public Producer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
    
    /*public List<ProductJson> sendTopProducts(List<ProductJson> list) {
    	List<ProductJson> topJson = new ArrayList<>();
    	for (ProductJson product : list) {
    		topJson.add(product);
    	
    	}
    	
    	return topJson;
    }*/
	
	

}
