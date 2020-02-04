package ru.alikhano.cyberlife.config.rabbitmq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class ProductToJsonParser {
	
	private static final Logger logger = LogManager.getLogger(ProductToJsonParser.class);
	
	public String toJson() {
		String result = "";
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			result = objMapper.writeValueAsString(this);
		}
		catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
	
		return result;
	}

}
