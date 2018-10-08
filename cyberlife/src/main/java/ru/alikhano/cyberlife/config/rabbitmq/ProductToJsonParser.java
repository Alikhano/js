package ru.alikhano.cyberlife.config.rabbitmq;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ProductToJsonParser {
	
	public String toJson() {
		String result = "";
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			result = objMapper.writeValueAsString(this);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
		return result;
	}

}
