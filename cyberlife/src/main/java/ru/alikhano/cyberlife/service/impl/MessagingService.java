package ru.alikhano.cyberlife.service.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@Service
public class MessagingService {
	
	private static final Logger logger = LogManager.getLogger(MessagingService.class);
	private static final String QUEUENAME = "productsTopQueue";
	
	public void sendUpdateMessage(String updateMessage) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUENAME, false, false, false, null);
		channel.basicPublish("", QUEUENAME, null, updateMessage.getBytes());
		logger.info(" [x] Sent '" + updateMessage + "'");
		
		channel.close();
		connection.close();
	}

}
